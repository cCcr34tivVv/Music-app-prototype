from models.role_orm import Role
from base.sql_base import Session


def get_roles():
    session = Session()
    roles = session.query(Role).all()
    return roles

def get_role(id):
    session = Session()
    role = session.query(Role).all()
    for role_element in role:
        if(role_element.id == id):
            return role_element

def get_role_by_name(name):
    session = Session()
    role = session.query(Role).all()
    for role_element in role:
        if(role_element.value == name):
            return role_element

def create_role(value):
    session = Session()
    role = Role(value)
    try:
        session.add(role)
        session.commit()
    except Exception:
        raise Exception("Failed to add role")
    return role