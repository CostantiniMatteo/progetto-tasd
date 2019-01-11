FROM jazzdd/alpine-flask

COPY requirements.txt /app/requirements.txt

RUN pip install -r requirements.txt

COPY . /webapp

EXPOSE 80

WORKDIR /webapp

CMD ["python", "application.py"]
