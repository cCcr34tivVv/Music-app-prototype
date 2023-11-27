from fastapi import HTTPException
from spyne import ServiceBase, rpc, Integer, String
from repositories.user_role_repository import create_user_role, delete_user_role, get_user_role_session

class UserRoleSoap(ServiceBase):
    @rpc(Integer, String, _returns = String)
    def create_user_role(ctx, id, role_name):
        try:
            user, role = get_user_role_session(id, role_name)
            create_user_role(user, role)
        except HTTPException: 
            return "FORBIDDEN"
        return "ok"

    @rpc(Integer, String, _returns = String)
    def delete_user_role(ctx, id, role_name):
        try:
            user, role = get_user_role_session(id, role_name)
            delete_user_role(user, role)
        except HTTPException: 
            return "FORBIDDEN"
        return "ok"