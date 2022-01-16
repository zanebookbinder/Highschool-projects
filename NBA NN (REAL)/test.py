from keras.models import load_model, model_from_json
import pandas as pd

def loadmodel():
    model = load_model('finalmodel.h5')
    return model

def get_model(model, pdinputs):
    result = model.predict(pdinputs)
    return ('Your question has been clustered into cluster number: ', result)