
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doria
 */


@ApplicationScoped    
@ServerEndpoint("/actions")
public class SocketServer {
    conexion db = new conexion();
    @Inject
    private Sesiones sesiones;

    
    @OnOpen
    public void open(Session sesion){
        System.out.println("Conectado");
        sesiones.addSession(sesion);
    }
    
    @OnClose
    public void close(Session session) {
        sesiones.removeSession(session);
    }
    
    @OnMessage
    public void message(String message, Session session){
        try (JsonReader reader = Json.createReader(new StringReader(message))){
            JsonObject jsonMessage = reader.readObject();
            
            if(true == jsonMessage.getBoolean("identification")){
                sesiones.updateUser(jsonMessage.getString("id_session"), session.getId());
            } else {
                //int receptor = bd.getUserId(jsonMessage.getString("to"));
                int id_from = jsonMessage.getInt("from");
                sesiones.sendToAllConnectedSessions(/*sesiones.getSession(sesiones.getIdSocket(receptor)),*/ sesiones.createMessage(jsonMessage.getString("mensaje"), db.getName(id_from)));
                
            }
        }
    }
    
    @OnError
        public void onError(Throwable error) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, error);
    }
}
