
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge C
 */
//quien te va a porbar tu proyecto? COVA PAOLA KARLA mm deja pienso algo para que no puedan petarlo vaaa o bueno no tan facil xdxd
//Este archivo es para ver si alguien esta conectado o no xdxd
//Lo que hay que hacer es ver quien esta conectado y ver si se le manda en tiempo real a esa persona el mensaje o se guarda en la db para mas tarde ok? ok
@ApplicationScoped
public class Sesiones {
    
    private final Set<Session> sesiones = new HashSet<>();
    
    public void addSession(Session session) {
        sesiones.add(session);
    }
    
    public void removeSession(Session sesion) {
        sesiones.remove(sesion);
    }
    
    private void sendToAllConnectedSessions(String mensaje) {
         for (Session sesion : sesiones) {
            sendToSession(sesion, mensaje);
        }
    }
    
    private void sendToSession(Session sesion, String message) {
        try {
            sesion.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sesiones.remove(sesion);
            Logger.getLogger(Sesiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Session getSession(String id_socket){
        Session sesion_return = null;
        for(Session sesion: sesiones){
            if(id_socket == sesion.getId()){
                sesion_return = sesion;
            }
        }
        return sesion_return;
    }
    
    
    
    /*Falta lo mas complicado por asi decirlo xd que es enlazar el id de la persona con el socket xdxd tj dme gustaria poder hacerlo de a soldado :( explica xdxd
    Pues ve aqui lo que haces es tener las sesiones pero no sabemos a quien le corresponde cada sesion si me entiendes? 2/2
    entonces tenemos que idear una manera de poder saber quien es quien weee 
    */
    
}
//el hashset pa qye sirve sirve para ver quien esta conectado en el chat vamos a hacer una lista de quien esta conectado va? va
