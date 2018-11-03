
import java.io.IOException;
import java.util.ArrayList;
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

@ApplicationScoped
public class Sesiones {
    
    private final Set<Session> sesiones = new HashSet<>();
    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private conexion bd = new conexion();
    private Usuario usuario;
    
    public void addSession(Session session) {
        sesiones.add(session);
    }
    
    public void removeSession(Session sesion) {
        sesiones.remove(sesion);
    }
    
    public void sendToAllConnectedSessions(JsonObject mensaje) {
         for (Session sesion : sesiones) {
            sendToSession(sesion, mensaje);
        }
    }
    
    public void sendToSession(Session sesion, JsonObject message) {
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
    
    
    public void addUser(String id_sesion, int id){
        usuario = new Usuario();
        usuario.setId_session(id_sesion);
        usuario.setId(id);
        usuarios.add(usuario);
    }
    
    public void updateUser(String id_sesion, String id_socket){
        for(Usuario usuario: usuarios){
            if(usuario.getId_session() == id_sesion){
                usuario.setId_socket(id_socket);
                System.out.println(usuario.getId_session() + "holaa");
            }
        }
    }
    
    public String getIdSocket(int id){
        Usuario usuario_return = null;
        String socket = "";
        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                socket = usuario.getId_socket();
            }
        }
        return socket;
    }
    
    public JsonObject createMessage(String message, String from){
        JsonProvider provider = JsonProvider.provider();
        JsonObject jsonmessage = provider.createObjectBuilder()
                .add("message", message)
                .add("from", from)
                .build();
        return jsonmessage;
    }
    
    
    
   
}
