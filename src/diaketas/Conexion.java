/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

/**
 *
 * @author kesada
 */
import java.sql.*;
public class Conexion{
    public String bd = "diaketas";
    public String login = "smartsolutions";
    public String password = "solutions";
    public String url = "jdbc:mysql://http://sql09.freemysql.net:3306/"+bd;
    public void conectar() {
        Connection conn = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null)
            {
                System.out.println("Conexión a base de datos "+url+" ... Ok");
                conn.close();
            }
        }
        catch(SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}