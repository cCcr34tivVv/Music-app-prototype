from base.sql_base import Session
from models.role_orm import Role
from models.user_orm import User

def create_user_role(user, role):
    session = Session()
    user.roles.append(role)
    try:
        user_now = session.merge(user)
        session.add(user_now)
        session.commit()
    except Exception:
        raise Exception("Failed to add relationship")
    return "ok"

def delete_user_role(user, role):
    session = Session()
    list = [] 
    for roles in user.roles:
        if(roles.id != role.id):
            list.append(roles)
    user.roles.clear()
    user.roles = user.roles + list

    try:
        user_now = session.merge(user)
        session.add(user_now)
        session.commit()
    except Exception:
        raise Exception("Failed to delte relationship")
    return "ok"

def get_user_role_session(id, role_name):
    session=Session()
    user = session.query(User).all()
    for user_element in user:
        if(user_element.id == id):
            user_good = user_element

    role = session.query(Role).all()
    for role_element in role:
        if(role_element.value == role_name):
            role_good = role_element

    return user_good, role_good