FROM jazzdd/alpine-flask:python3

COPY . /app

WORKDIR /app

RUN pip install -r requirements.txt

EXPOSE 80
