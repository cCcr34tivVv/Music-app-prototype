from service.role_service import RoleSoap
from service.user_role_service import UserRoleSoap
from service.user_service import UserSoap
from spyne import Application
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication


application = Application([UserSoap, RoleSoap, UserRoleSoap], 'services.user.soap',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11())
                          

wsgi_application = WsgiApplication(application)


if __name__ == '__main__':
    import logging
    #print(get_user(1).username)
    from wsgiref.simple_server import make_server

    logging.basicConfig(level=logging.INFO)
    logging.getLogger('spyne.protocol.xml').setLevel(logging.INFO)

    logging.info("listening to http://127.0.0.1:8000")
    logging.info("wsdl is at: http://127.0.0.1:8000/?wsdl")

    server = make_server('127.0.0.1', 8000, wsgi_application)
    server.serve_forever()

        