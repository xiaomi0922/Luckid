from seetaface.api import *


class seetaface:
    def __init__(self):
        self.init_mask = FACE_DETECT | FACERECOGNITION | LANDMARKER5 | LANDMARKER_MASK
        self.seetaFace = SeetaFace(self.init_mask)


    def extract_feature(self, image):
        try:
            detect_result = self.seetaFace.Detect(image)
            if detect_result.size == 0:
                print("get face num: 0")
        except:
            print('Failed: Cannot detect face')
            return None, 3
        try:
            area_list = []
            for i in range(detect_result.size):
                area = detect_result.data[i].pos.width * detect_result.data[i].pos.height
                area_list.append(area)
            face_index = area_list.index(max(area_list))
            face = detect_result.data[face_index].pos
            points = self.seetaFace.mark5(image, face)
            feature = self.seetaFace.Extract(image, points)
        except:
            print("extract feature failed")
            return None, 4
        return feature, 0


    def get_feature(self, face_data):
        face_mask, feature = self.extract_feature(face_data)
        if 1 in face_mask:
            threshold = 0.48
        else:
            threshold = 0.62
        return feature, threshold


    def multi_person_extract_feature(self, img_data):
        feature_list = []
        detect_result = self.seetaFace.Detect(img_data)
        for i in range(detect_result.size):
            face = detect_result.data[i].pos
            points = self.seetaFace.mark5(img_data, face)
            _, face_mask = self.seetaFace.markMask(img_data, face)
            feature = self.seetaFace.Extract(img_data, points)
            feature_list.append(feature)
        return feature_list


class seetaface_mask:
    def __init__(self):
        self.init_mask = FACE_DETECT | MASK_FACERECOGNITION | LANDMARKER_MASK
        self.seetaFace = SeetaFace_Mask(self.init_mask)

    def extract_feature(self, image):
        try:
            detect_result = self.seetaFace.Detect(image)
            if detect_result.size == 0:
                print("get face num: 0")
        except:
            print('Failed: Cannot detect face')
            return None, 3
        try:
            area_list = []
            for i in range(detect_result.size):
                area = detect_result.data[i].pos.width * detect_result.data[i].pos.height
                area_list.append(area)
            face_index = area_list.index(max(area_list))
            face = detect_result.data[face_index].pos
            points, _ = self.seetaFace.markMask(image, face)
            feature = self.seetaFace.MaskExtract(image, points)
        except:
            print("extract feature failed")
            return None, 4
        return feature, 0


    def cal_similarity(self, face_feature1, face_feature2):
        similar = self.seetaFace.MaskCalculateSimilarity(face_feature1, face_feature2)
        return similar


    def get_feature(self, face_data):
        feature = self.extract_feature(face_data)
        threshold = 0.48
        return feature, threshold


    def multi_person_extract_feature(self, img_data):
        feature_list = []
        detect_result = self.seetaFace.Detect(img_data)
        for i in range(detect_result.size):
            face = detect_result.data[i].pos
            points, _ = self.seetaFace.markMask(img_data, face)
            feature = self.seetaFace.MaskExtract(img_data, points)
            feature_list.append(feature)
        return feature_list

    def cal_similaritypro(self, face_feature1, face_feature2):
        similar = self.seetaFace.compare_feature_np(face_feature1, face_feature2)
        return similar