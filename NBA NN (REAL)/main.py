import test
from flask import Flask, request, render_template
from keras.models import load_model, model_from_json
import pandas as pd

app = Flask(__name__)


@app.route('/')
def index():
    return render_template("main.html")

@app.route('/', methods=['POST'])
def my_form_post():
    model = test.loadmodel()

    inputs = []
    for num in range(13):
        inputs.append(request.form['text' + str(num)])

    pdinputs = pd.DataFrame(inputs)

    text = test.get_model(model, pdinputs)

    return text

if __name__ == "__main__":
    app.run(debug=True, threaded=False)
