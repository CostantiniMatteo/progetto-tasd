from flask import Flask, render_template
app = Flask(__name__, static_url_path='')

@app.route('/')
def home():
	user = {'first_name' : "schifo", 'last_name' : "rossi"}
	return render_template('home.html', user=user)


@app.route('/login')
def login():
	return render_template('login.html')





if __name__ == '__main__':
    app.run(debug=True)