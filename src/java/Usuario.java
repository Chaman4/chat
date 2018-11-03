/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doria
 */
public class Usuario {
    private String id_session;
    private String id_socket;
    private int id;
    
    public Usuario(){}
    
    public Usuario(String id_sesion, String id_socket){
        this.id_session = id_sesion;
        this.id_socket = id_socket;
    }

    public String getId_session() {
        return id_session;
    }

    public void setId_session(String id_session) {
        this.id_session = id_session;
    }

    public String getId_socket() {
        return id_socket;
    }

    public void setId_socket(String id_socket) {
        this.id_socket = id_socket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
