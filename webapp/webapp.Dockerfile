FROM jazzdd/alpine-flask

COPY requirements.txt /app/requirements.txt

RUN pip install -r requirements.txt

COPY . /app

EXPOSE 80

WORKDIR /app

CMD ["python", "application.py"]
