import time

import numpy as np
import base64
import cv2
from PIL import Image


# import tensorflow as tf
# import mxnet as mx


def cosin_metric(x1, x2):
    return np.dot(x1, x2) / (np.linalg.norm(x1, axis=1) * np.linalg.norm(x2))


def cal_cos_sim(source_feature, target_feature):
    sim = cosin_metric(target_feature[:, np.newaxis].T, source_feature)
    return sim


def get_score(aim_feautre, workers, flag):
    result_score, result_best_work = [], []
    for aim_fea in aim_feautre:
        max_score = 0
        best_worker = None
        for worker_info in workers:
            if flag == 0:
                target_fea = np.frombuffer(worker_info.seetaface_features, dtype=np.float32)
            elif flag == 1:
                target_fea = np.frombuffer(worker_info.mask_features, dtype=np.float32)
            elif flag == 2:
                target_fea = np.frombuffer(worker_info.arcface_features, dtype=np.int32)
            rank = cal_cos_sim(aim_fea, target_fea)  # 0代表没有，0-1代表正确
            try:
                # print('{} score --> {}'.format(worker_info.name, float(rank)))
                rank = float(rank)
                if rank > max_score:
                    max_score = rank
                    best_worker = worker_info
            except:
                print('no face')
        result_score.append(max_score)
        result_best_work.append(best_worker)
    return result_score, result_best_work


def read_image_opencv(filePath):
    img_data = cv2.imread(filePath)
    return img_data


def read_image_from_base64(filePath):
    with open(filePath, 'r') as f:
        image_code = f.read()
    start = time.time()
    img_data = base64_cv2(image_code)
    end = time.time()
    print("encode the image cost: ", end - start)
    return img_data


def read_image_PIL(filePath):
    img_data = Image.open(filePath)
    width, height = img_data.size
    img_data = np.asarray(img_data.resize((width // 4 * 4, height // 4 * 4)))
    return img_data


# def read_image_TF(filePath):
#     img = tf.gfile.FastGFile(filePath, 'rb').read()
#     img_data = tf.image.decode_jpeg(img)
#     sess = tf.Session()
#     return img_data.eval(session=sess)


# def read_image_MX(filePath):
#     img_data = mx.image.imdecode(open(filePath, 'rb').read())
#     return img_data.asnumpy()


def cv2_base64(image):
    base64_str = cv2.imencode('.jpg', image)[1].tostring()
    base64_str = base64.b64encode(base64_str)
    return base64_str


def base64_cv2(base64_str):
    imgString = base64.b64decode(base64_str)
    nparr = np.fromstring(imgString, np.uint8)
    start = time.time()
    image = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    end = time.time()
    print("numpy to image cost:", end - start)
    return image


class Stack(object):
    """
    堆栈类
    """

    def __init__(self):
        """
        初始化
        """
        self.__stack = []

    def __len__(self):
        """
        返回堆栈长度
        """
        return len(self.__stack)

    def push(self, value):
        """
        入栈
        """
        self.__stack.insert(0, value)

    def pop(self):
        """
        出栈
        """
        return self.__stack.pop(0)

    def peek(self):
        """
        返回堆栈顶部元素
        """
        return self.__stack[0]

    def is_empty(self):
        """
        检测堆栈是否为空
        """
        return self.__stack == []

    def travel(self):
        """
        遍历堆栈
        """
        for val in self.__stack:
            print(val)


if __name__ == '__main__':
    st = Stack()
    print(st.is_empty())
    st.push('a')
    st.push('b')
    st.push('c')
    st.push('d')
    print(st.pop())
    print(st.is_empty())
    print(st.peek())
    print(len(st))
    st.travel()
