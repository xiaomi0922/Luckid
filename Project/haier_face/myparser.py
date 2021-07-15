import json


def get_super_args():
    with open('config.json', 'r', encoding='utf8') as fp:
        config = json.load(fp)
        config['SQLALCHEMY_DATABASE_URI'] = config['DB_URI'].format(
            username=config['USERNAME'],
            password=config['PASSWORD'],
            host=config['HOST'],
            port=config['PORT'],
            db=config['DATABASE'])
        return config


def get_config():
    with open('config.json', 'r', encoding='utf8') as fp:
        config = json.load(fp)

        data = json.dumps({
            'host': config['HOST'],
            'port': config['PORT'],
            'database': config['DATABASE'],
            'username': config['USERNAME'],
            'password': config['PASSWORD'],
            'SQLALCHEMY_TRACK_MODIFICATIONS': config['SQLALCHEMY_TRACK_MODIFICATIONS'],
            'SQLALCHEMY_ECHO': config['SQLALCHEMY_ECHO'],
            'face_freq': config['FACE_FREQ'],
            'body_freq': config['BODY_FREQ'],
            'no_body_print_time': config['NO_BODY_PRINT_TIME'],
            'no_face_stop_time': config['NO_FACE_STOP_TIME'],
            'face_rate': config['FACE_RATE'],
            'face_confidence': config['FACE_CONFIDENCE'],
            'body_confidence': config['BODY_CONFIDENCE'],
            'face_cap_path': config['FACE_CAP_PATH'],
            'body_cap_path': config['BODY_CAP_PATH'],
            'save_img_freq': config['SAVE_IMG_FREQ'],
        })
        return data


def set_config(configs):
    with open('config.json', 'w', encoding='utf8') as fw:
        json.dump(configs, fw, ensure_ascii=False)
