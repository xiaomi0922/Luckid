from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import myparser
from sqlalchemy.databases import mysql

configs = myparser.get_super_args()

db = SQLAlchemy()


def create_app():
    app = Flask(__name__)
    db.init_app(app)
    return app


app = create_app()
app.config['SQLALCHEMY_DATABASE_URI'] = configs['SQLALCHEMY_DATABASE_URI']
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = configs['SQLALCHEMY_TRACK_MODIFICATIONS'] is 1
app.config['SQLALCHEMY_ECHO'] = configs['SQLALCHEMY_ECHO'] is 1
db = SQLAlchemy(app)


class Check(db.Model):
    __tablename__ = 'check'
    id = db.Column(db.Integer, primary_key=True)
    uid = db.Column(db.Integer, db.ForeignKey('workers.uid'))
    time = db.Column(db.DateTime)
    rank = db.Column(db.Float)

    def __init__(self, uid, time, rank):
        self.uid = uid
        self.time = time
        self.rank = rank

    def __repr__(self):
        return '<check {} {} {} {}>'.format(self.id, self.uid, self.time, self.rank)


class TimeLineCap(db.Model):
    __tablename__ = 'timeline_cap'
    id = db.Column(db.Integer, primary_key=True)
    time = db.Column(db.DateTime)
    img = db.Column(db.String(1000))

    def __init__(self, time, img):
        self.time = time
        self.img = img

    def __repr__(self):
        return '<timeline cap {} {} {}>'.format(self.id, self.time, self.img)


class Workers(db.Model):
    __tablename__ = 't_ai_face'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    department_id = db.Column(db.String(255))
    end_time = db.Column(db.BigInteger())
    feature_value = db.Column(db.String(10000))
    height = db.Column(db.String(10))
    id_card_num = db.Column(db.String(50))
    name = db.Column(db.String(55))
    nation = db.Column(db.String(10))
    photo = db.Column(mysql.MSMediumText)
    property = db.Column(db.String(20))
    sex = db.Column(db.String(2))
    start_time = db.Column(db.BigInteger())
    tag = db.Column(db.String(55))
    update_time = db.Column(db.BigInteger())

    def __init__(self, department_id, end_time, feature_value, height, id_card_num, name, nation, photo, property, sex, start_time, tag, update_time):
        self.department_id = department_id
        self.end_time = end_time
        self.feature_value = feature_value
        self.height = height
        self.id_card_num = id_card_num
        self.name = name
        self.nation = nation
        self.photo = photo
        self.property = property
        self.sex = sex
        self.start_time = start_time
        self.tag = tag
        self.update_time = update_time

    def __repr__(self):
        return '<Worker %r>' % self.name
