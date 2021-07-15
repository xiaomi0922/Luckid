import cv2
import time
from beans import *
import numpy as np


def find_all_workers():
    return Workers.query.all()


def update_seetaface_info(model, workers):
    for worker_info in workers:
        print(worker_info.name)
        feature = get_seetaface(model, worker_info.m_face)
        update_seetaface_feature_db(worker_info.uid, feature)


def update_seetaface_mask_info(model, workers):
    for worker_info in workers:
        print(worker_info.name)
        feature = get_seetaface_mask(model, worker_info.m_face)
        update_seetaface_mask_feature_db(worker_info.uid, feature)


def update_arcface_info(model, workers):
    for worker_info in workers:
        print(worker_info.name)
        feature = get_arcface(model, worker_info.m_face)
        update_arcface_feature_db(worker_info.uid, feature)


def get_seetaface(seeta_model, img_path):
    img_data = cv2.imread(img_path)
    face_mask, feature = seeta_model.extract_feature(img_data)
    return np.array(feature).tostring()


def get_seetaface_mask(seeta_model_mask, img_path):
    img_data = cv2.imread(img_path)
    feature = seeta_model_mask.extract_feature(img_data)
    return np.array(feature).tostring()


def get_arcface(arcface_model, img_path):
    img_data = cv2.imread(img_path)
    height, weight = img_data.shape[:2]
    img_data = cv2.resize(img_data, (weight // 4 * 4, height // 4 * 4), interpolation=cv2.INTER_AREA)
    feature = arcface_model.extract_feature(img_data)
    return np.array(feature).tostring()


def update_seetaface_feature_db(uid, feature):
    worker = Workers.query.filter(Workers.uid == uid).first()
    worker.l_features = feature
    db.session.commit()


def update_seetaface_mask_feature_db(uid, feature):
    worker = Workers.query.filter(Workers.uid == uid).first()
    worker.mask_features = feature
    db.session.commit()


def update_arcface_feature_db(uid, feature):
    worker = Workers.query.filter(Workers.uid == uid).first()
    worker.m_features = feature
    db.session.commit()


def add_workers(department_id, eigenvalue, end_time, height, id_card_num, name, nation, photo, property, sex, start_time, tag, update_time):
    worker = Workers(department_id, eigenvalue, end_time, height, id_card_num, name, nation, photo, property, sex, start_time, tag, update_time)
    db.session.add(worker)
    db.session.commit()


def find_workers_by_id(uid):
    return Workers.query.filter_by(uid=uid).first()


def del_worker(uid):
    try:
        worker = find_workers_by_id(uid)
        db.session.delete(worker)
        db.session.commit()
        return True
    except Exception as e:
        print(e)
        db.session.rollback()
        return False
