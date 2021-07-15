FROM ubuntu:18.04
ENV LANG C.UTF-8
RUN apt-get update
RUN apt install -y python-pip
RUN apt install -y python3-pip
RUN update-alternatives --install /usr/bin/python python /usr/bin/python2.7 1
RUN update-alternatives --install /usr/bin/python python /usr/bin/python3.6 2

RUN pip install --upgrade pip==20.02 -i https://mirrors.aliyun.com/pypi/simple
RUN pip install --upgrade setuptools -i https://pypi.mirrors.ustc.edu.cn/simple/
RUN apt-get install -y vim

# set working directory
ADD . /app
WORKDIR /app
EXPOSE 18050

# install required libraries
RUN pip install -r requirements.txt -i https://mirrors.aliyun.com/pypi/simple
RUN apt install -y libgl1-mesa-glx
RUN apt-get install -y libglib2.0-0
RUN apt-get install -y libgomp1

# set environment path
RUN echo /app/seetaface/lib/ubuntu  > /etc/ld.so.conf.d/seetaface6.conf && \
  ldconfig

CMD gunicorn -c gunicorn_conf.py app:app
