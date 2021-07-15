import time
import myparser
import cv2
import json
import wget
import logging
import service
import utilis
import base64
import myparser
import numpy as np
import threading
from flask import jsonify, request
from face_recon import seetaface, seetaface_mask
from beans import app
import os
from queue import Queue


start = time.time()
try:
    workers = service.find_all_workers()
except:
    app.logger.info('*' * 50)
    app.logger.info('connect database error!')
    app.logger.info('*' * 50)
app.logger.info('get worker cost time {} ms.'.format((time.time() - start) * 1000))

stranger_queue = Queue(50)
configs = myparser.get_super_args()
flag = configs['flag']

if configs['flag'] == 0:
    model = seetaface()
    # threshold = 0.48
    # service.update_seetaface_info(model, workers)
elif configs['flag'] == 1:
    model = seetaface_mask()
    # threshold = 0.48
    # service.update_seetaface_mask_info(model, workers)


@app.route('/video_ai/face_recognition/image', methods=['POST'])
def face_recognition():
    # 数据转码
    try:
        # start = time.time()
        # img_src = request.json.get('img_url')
        # res = session.get(img_src)
        # img_data = cv2.imdecode(np.fromstring(res.content, np.uint8), 1)
        # end = time.time()
        # print("get data:", (end - start) * 1000, ' ms')

        # start = time.time()
        # data = request.json
        # image_b64 = data.get("img_url")
        # image_b64 = image_b64.split(',')[-1]
        # image_decode = base64.b64decode(image_b64)
        # np_data = np.fromstring(image_decode, np.uint8)
        # img_data = cv2.imdecode(np_data, cv2.IMREAD_COLOR)
        # print("get img_data:", (time.time() - start) * 1000, ' ms')

        start = time.time()
        request_data = eval(request.get_data())
        # request_data = request.json
        img_path = request_data.get('img_url')
        save_fname = 'download_com.jpg'
        wget.download(img_path, out=save_fname)
        print('\n')
        img_data = cv2.imread(save_fname)
        os.remove(save_fname)
        app.logger.info('decode takes {} ms.'.format((time.time() - start) * 1000) )
        # print("read img data takes {} ms.".format((time.time() - start) * 1000))
    except:
        response = {"code": 1, "msg": "Failed: Invalid parameter", "id": -1, "score": 0.0}
        return jsonify(response)

    # TODO 判断图片大小是否符合要求

    # 抽取图片特征
    start = time.time()
    feature, code = model.extract_feature(img_data)
    if code == 3:
        response = {"code": code, "msg": "Failed: Cannot detect face", "id": -1, "score": 0.0}
        return jsonify(response)
    elif code == 4:
        response = {"code": code, "msg": "Failed: Cannot extract face feature", "id": -1, "score": 0.0}
        return jsonify(response)
    app.logger.info('extract feature takes {} ms.'.format((time.time() - start) * 1000))
    # print("extract feature:", (time.time() - start) * 1000, ' ms')

    # 人脸比对
    try:
        start = time.time()
        max_score = -1
        best_worker = None
        if len(workers) == 0:
            response = {"code": 5, "msg": "Failed: The database is empty", "id": -1, "score": 0.0}
            return jsonify(response)
        for worker in workers:
            similarity = model.cal_similaritypro(np.array(feature),
                                                 np.array([float(x) for x in worker.feature_value.split(',')]))
            try:
                similarity = float(similarity)
                if similarity > max_score:
                    max_score = similarity
                    best_worker = worker
            except:
                app.logger.info('no face')
                # print('no face')

        if 0.2 < max_score < 0.48:
            app.logger.info('stranger_list length {}'.format(stranger_queue.qsize()))
            # print('stranger_list length: ', stranger_queue.qsize())
            if stranger_queue.qsize() == 0:
                stranger_queue.put(np.array(feature))
                response = {"code": 7, "msg": "new stranger!", "id": 000, "score": 0.0}
                return jsonify(response)
            else:
                stranger_max_score = 0
                for stranger in list(stranger_queue.queue):
                    stranger_similarity = model.cal_similaritypro(np.array(feature), stranger)
                    try:
                        stranger_similarity = float(stranger_similarity)
                        if stranger_similarity > stranger_max_score:
                            stranger_max_score = stranger_similarity
                    except:
                        app.logger.info('no face in img!')
                        # print('no face in img!')
                if stranger_max_score < 0.48:
                    app.logger.info('stranger_max_score {}'.format(stranger_max_score))
                    # print('stranger_max_score: ', stranger_max_score)
                    if stranger_queue.qsize() == 100:
                        stranger_queue.get()
                        stranger_queue.put(np.array(feature))
                    else:
                        stranger_queue.put(np.array(feature))
                    response = {"code": 7, "msg": "new stranger!", "id": 000, "score": stranger_max_score}
                    return jsonify(response)
                if stranger_max_score > 0.48:
                    response = {"code": 8, "msg": "same stranger!", "id": 000, "score": stranger_max_score}
                    return jsonify(response)
        app.logger.info('compare feature takes {} ms.'.format((time.time() - start) * 1000))
        # print("compare time:", (time.time() - start) * 1000, ' ms')
    except:
        response = {"code": 6, "msg": "Failed: Feature comparison failed", "id": -1, "score": 0.0}
        return jsonify(response)
    response = {"code": 0, "msg": "Success: Recognition finish.", "id": best_worker.id, "score": max_score}
    # 返回结果
    return jsonify(response)


@app.route('/video_ai/add_face_feature/image', methods=['POST'])
def add_feature():
    try:
        start = time.time()
        # data = eval(request.get_data())
        data = request.json
        image_b64 = data.get("source_img")
        image_b64 = image_b64.split(',')[-1]
        image_decode = base64.b64decode(image_b64)
        np_data = np.fromstring(image_decode, np.uint8)
        img_data = cv2.imdecode(np_data, cv2.IMREAD_COLOR)
        app.logger.info('read image takes {} ms.'.format((time.time() - start) * 1000) )
        # print("read image:", (time.time() - start) * 1000, ' ms')
        # start = time.time()
        # request_data = eval(request.get_data())
        # img_path = request_data.get('img_url')
        # save_fname = 'download_add.jpg'
        # wget.download(img_path, out=save_fname)
        # img_data = cv2.imread(save_fname)
        # os.remove(save_fname)
        # print("get img_data:", (time.time() - start) * 1000, ' ms')
    except:
        response = {"code": 1, "msg": "Failed: Invalid parameter", "featureData": None}
        return jsonify(response)

    start = time.time()
    feature, code = model.extract_feature(img_data)
    if feature == None:
        response = {"code": 5, "msg": "Failed: featureData id none", "featureData": None}
        return jsonify(response)
    feature = np.array(feature).tolist()
    feature_list = [str(i) for i in feature]
    featureData = ','.join(feature_list)
    app.logger.info('extract feature takes {} ms.'.format((time.time() - start) * 1000) )
    # print("extract feature:", (time.time() - start) * 1000, ' ms')
    if code == 3:
        response = {"code": code, "msg": "Failed: Cannot detect face", "featureData": None}
        return jsonify(response)
    elif code == 4:
        response = {"code": code, "msg": "Failed: Cannot extract face feature", "featureData": None}
        return jsonify(response)
    response = {"code": code, "msg": "Success: extract finish", "featureData": featureData}
    return jsonify(response)


@app.route('/video_ai/change_list/image', methods=['GET'])
def update_database():
    start = time.time()
    lock = threading.Lock()
    lock.acquire()
    global workers
    try:
        workers = service.find_all_workers()
        # print(workers[0])
        app.logger.info('update DB takes {} ms.'.format((time.time() - start) * 1000) )
        # print("update DB time:", (time.time() - start) * 1000, ' ms')
    except:
        response = {"code": 1, "msg": "Fail: update DB failed", "status": False}
        return jsonify(response)
    response = {"code": 0, "msg": "Success: update DB finish", "status": True}
    lock.release()
    return jsonify(response)


@app.route('/')
def index():
    return ('ok')


if __name__ == '__main__':
    app.config['JSON_AS_ASCII'] = False
    app.run(host='0.0.0.0', port=18050)

if __name__ != '__main__':
    gunicorn_logger = logging.getLogger('gunicorn.error')
    app.logger.handlers = gunicorn_logger.handlers
    app.logger.setLevel(gunicorn_logger.level)
