from spyne import ServiceBase, String, Integer, rpc
from repositories.role_repository import create_role, get_role, get_role_by_name, get_roles 

class RoleSoap(ServiceBase):
    @rpc(Integer, _returns = String)
    def get_role(ctx, id):
        return get_role(id).value

    @rpc()
    def display_roles_info(ctx):
        for role in get_roles():
            print(f"{role.id} - {role.value}")

    @rpc(String)
    def get_role_by_name(ctx, name):
       return get_role_by_name(name)

    @rpc(String, _returns = String)
    def create_role(ctx, value):
        try:
            create_role(value)
            return("Role added")
        except:
            return("Error")