from models.user_orm import User
from base.sql_base import Session


def get_users():
    session = Session()
    users = session.query(User).all()
    return users

def get_user(id):
    session=Session()
    user = session.query(User).all()
    for user_element in user:
        if(user_element.id == id):
            return user_element

def create_user(username, password):
    session = Session()
    user = User(username, password)
    try:
        session.add(user)
        session.commit()
    except Exception:
        raise Exception("Failed to add user")
    return user

def change_pass(id, password):
    session=Session()
    user = session.query(User).all()
    for user_element in user:
        if(user_element.id == id):
            try:
                setattr(user_element, 'password', password)
                session.commit()
            except Exception:
                raise Exception("Failed to change password")
            return user_element

def delete_user(id):
    session=Session()
    user = session.query(User).all()
    for user_element in user:
        if(user_element.id == id):
            try:
                session.delete(user_element)
                session.commit()
            except Exception:
                raise Exception("Failed to delte user")

def login(username, password):
    session=Session()
    user = session.query(User).all()
    for user_element in user:
        if(user_element.username == username and user_element.password == password):
            return user_element
        
    return None