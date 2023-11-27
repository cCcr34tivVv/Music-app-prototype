from spyne import Iterable, ServiceBase, String, Integer, rpc
from models.role_orm import Role
from repositories.role_repository import get_role_by_name
from repositories.user_repository import change_pass, create_user, get_user, get_users, delete_user, login
from service.token_service import create_access_token

class UserSoap(ServiceBase):
    @rpc(Integer,_returns=String)
    def user_info(ctx, id):
        return get_user(id).username

    @rpc(_returns=Iterable(String))
    def users_info(ctx):
        return [user.username for user in get_users()]

    @rpc()
    def display_users_info(ctx):
        for user in get_users():
            print(f"{user.id} - {user.username} - {user.password} - ", [role.value for role in user.roles])

    @rpc(String, String, _returns = String)
    def create_user(ctx, username, password):
        try:
            role : Role = get_role_by_name("client")
            create_user(username, password, role)
            return "User added"
        except:
            return "Error"

    @rpc(Integer, String, _returns = String)
    def change_pass(ctx, id, password):
        try:
            change_pass(id, password)
            return "Password changed"
        except:
            return("Error")

    @rpc(Integer, _returns = String)
    def delete_user(ctx, id):
        try:
            delete_user(id)
            return("User deleted")
        except:
            return("Error")

    @rpc(String, String, _returns = String)
    def login(ctx, username, password):
        user = login(username, password)
        if(user == None):
            return "FORBDIDEN"

        return "ok"
