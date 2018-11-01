/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Luigi
 */
public class conexion {

    public static Connection getConnection() {
        String url, UserName, password;
        url = "jdbc:mysql://localhost/chat";
        UserName = "root";
        password = "n0m3l0";
        //si son tus credenciales? credenciales?? bueno tu usuario y tu password ahh sii
        Connection con = null;
        System.out.println("we");
        try {
            System.out.println("htf");
            Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("asd");
            con = DriverManager.getConnection(url, UserName, password);
            System.out.println("Si se conecto a BD");
        } catch (Exception e) {
            System.out.println("Solo jugo contigo again 7-7");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return con;
    }

    public static int registrarUsuario(String nombre) {
        int generatedKey = 0;
        try {

            Connection con = getConnection();
            String q;
            q = "INSERT INTO usuario (nombre) values ('" + nombre + "')";
            System.out.println(q);
            PreparedStatement ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return generatedKey;
    }

}

//me mando al cuando le quise cambiar el nombre xdxd si vi 
//bueno nos vamos a pasar el paradigma orientado a objetos un poquito por los huevos xdxd a armando le emput
