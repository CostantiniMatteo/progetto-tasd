from flask import Flask, render_template, request, abort, make_response, redirect, url_for
import requests
import json
import jwt
from base64 import b64decode

app = Flask(__name__, static_url_path='')

BASE_URL = "http://192.168.99.100:8080"



@app.route('/', methods = ['GET'])
def index():
    return render_template('index.html')



@app.route('/dashboard', methods = ['GET'])
def dashboard():
    # j = validate(request)
    # if j:
    #     return render_template('dashboard.html', username=j['sub'])
    # else:
    #     abort(401)

    return render_template('dashboard.html', name_page="Dashboard")


@app.route('/login', methods = ['GET', 'POST'])
def login():
    if request.method == 'POST':
        data = request.form.to_dict(flat=True)
        print(data)
        r = requests.post(BASE_URL + "/token/generate-token", json=data)

        if r.status_code == 200:
            token = r.json()['result']['token']
            print(token)

            resp = make_response(redirect(url_for('dashboard')))
            resp.set_cookie('bearer', token)

            return resp
        else:
            abort(r.status_code)


    return render_template('login.html')


@app.route('/signup/jobcenter', methods = ['GET', 'POST'])
def signup_jobcenter():

    if request.method == 'POST':
        r_json = request.form.to_dict(flat=True)
        r_json["role"] = "JOB_CENTER"
        print(r_json)
        print(requests.post(BASE_URL + "/signup", json=r_json))

    return render_template('signup_jobcenter.html')


@app.route('/signup/seeker', methods = ['GET', 'POST'])
def signup_seeker():

    if request.method == 'POST':
        r_json = request.form.to_dict(flat=True)
        r_json["role"] = "SEEKER"
        r_json["skills"] = request.form.getlist("skill")
        del r_json["skill"]
        print(r_json)
        print(requests.post(BASE_URL + "/signup", json=r_json))

    return render_template('signup_seeker.html')



def validate(request):

    try:
        token = request.cookies.get('bearer')
        j = jwt.decode(token, b64decode("JwtSecretKey"), algorithms=['HS256'])
    except:
        j = {}

    return j


if __name__ == '__main__':
    app.run(debug=True)
