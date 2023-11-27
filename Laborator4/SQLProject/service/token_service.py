from fastapi import FastAPI, status, HTTPException
from jose import JWTError, jwt
from pydantic import BaseModel
from datetime import datetime, timedelta
import uuid
 
# replace it with your 32 bit secret key
SECRET_KEY = "uVOmY0+t91QwZQCmbsNjhSyVhlZ5GxIY/6wWe9Gb6rokqstqhu40ju4beUpT+UHx"
 
# encryption algorithm
ALGORITHM = "HS256"

URL_ADRESS = "http://127.0.0.1:8000"
 
# Pydantic Model that will be used in the
# token endpoint for the response
class Token(BaseModel):
    access_token: str
    token_type: str
 
# Initialise the app
app = FastAPI()

# this function will create the token
# for particular data
def create_access_token(user_id, role_id):
    data = {
        "iss" : URL_ADRESS,
        "sub" : user_id,
        # expire time of the token
        "expire" : datetime.utcnow() + timedelta(minutes=15),
        "jti" : uuid.uuid1(),
        "role" : role_id
    }
    encoded_jwt = jwt.encode(data, SECRET_KEY, algorithm=ALGORITHM)

    # return the generated token
    return encoded_jwt

# the endpoint to get the token
@app.get("/get_token")
async def get_token():
   
    # data to be signed using token
    data = {
        'info': 'secret information',
        'from': 'GFG'
    }
    token = create_access_token(data=data)
    return {'token': token}

# the endpoint to verify the token
@app.post("/verify_token")
async def verify_token(token: str):
    try:
        # try to decode the token, it will
        # raise error if the token is not correct
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        return payload
    except JWTError:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Could not validate credentials",
        )