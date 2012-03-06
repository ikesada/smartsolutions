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
    public String url = "jdbc:mysql://sql09.freemysql.net:3306/"+bd;
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
        
        
        
        try {
            //Crear objeto Statement para realizar queries a la base de datos
            Statement instruccion = conn.createStatement();
            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet tabla = instruccion.executeQuery("SELECT cod , nombre FROM datos");
            System.out.println("Codigo\tNombre");
            while(tabla.next())
            System.out.println(tabla.getInt(1)+"\t"+tabla.getString(2));
            }
            catch(SQLException e){ System.out.println(e); }
            catch(Exception e){ System.out.println(e); }
    }
}