/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luigi
 */
public class conexion {

    Connection con;

    public static Connection getConnection() {
        String url, UserName, password;
        url = "jdbc:mysql://localhost/chat";
        UserName = "root";
        password = "n0m3l0";
        Connection con = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection(url, UserName, password);
            System.out.println("Si se conecto a BD");
        } catch (Exception e) {
            System.out.println("Solo jugo contigo again 7-7");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return con;
    }

    public int registrarUsuario(String nombre) {
        int generatedKey = 0;
        try {

            con = getConnection();
            String q;
            q = "INSERT INTO usuario (nombre) values ('" + nombre + "')";
            PreparedStatement ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return generatedKey;
    }

    public int getUserId(String name) {

        int id = 0;
        try {
            con = getConnection();

            String query = "Select * FROM usuario WHERE nombre = '" + name + "'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String getName(int id) {
        String name_return = "";
        try {
            con = getConnection();

            String query = "Select * FROM usuario WHERE id = '" + id + "'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name_return = rs.getString("nombre");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name_return;
    }
    
    public void addChat(int id1, int id2){
        try{
            con = getConnection();
            String query = "INSERT INTO chat (id_usuario1, id_usuario2) SELECT * FROM (SELECT '" + id1 + "', '" + id2 + "') AS temporal WHERE NOT EXISTS ( SELECT * FROM chat WHERE (id_usuario1 = '" + id1 + "' AND id_usuario2 = '" + id2 + "') OR (id_usuario1 = '" + id2 + "' AND id_usuario2 = '" + id1 + "')) LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getChat(int id1, int id2){
        int id_return = 0;
        try {
            con = getConnection();

            String query = "SELECT id_chat FROM chat WHERE ((id_usuario1 = '" + id1 + "' AND id_usuario2 = '" + id2 + "') OR (id_usuario1 = '" + id2 + "' AND id_usuario2 = '" + id1 + "')) LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_return = rs.getInt("id_chat");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_return;
    }
    
    public void addMessage(int id1, int id2, String mensaje){
       try {

            con = getConnection();
            String query = "a";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();  
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
           
}

