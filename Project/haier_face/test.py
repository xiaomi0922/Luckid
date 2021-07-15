import time
import cv2
import service
import utilis
import myparser
import numpy as np
from flask import jsonify, request
from face_recon import seetaface, seetaface_mask
from beans import app
import os


import base64
import requests
import json

url1 = 'http://127.0.0.1:18050/video_ai/face_recognition/image'
# url2 = 'http://127.0.0.1:18050/video_ai/add_face_feature/image'
# url3 = 'http://127.0.0.1:18050/video_ai/change_list/image'

data = {'img_url': 'http://10.9.69.134:20141/images-download/05151115EAA75367760.jpg'}

r1 = requests.post(url1, data=json.dumps(data))
# r2 = requests.post(url2, data=json.dumps(data))
# r3 = requests.get(url3)
print(r1.text)
# print(r2.text)
# print(r3.text)


# 测试网络是否连通，是否可下载图片
# import wget
# wget.download("http://10.9.69.134:20141/images-download/05151115EAA75367760.jpg")
