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
public class ConexionBD{
    public String bd = "diaketas";
    public String login = "smartsolutions";
    public String password = "solutions";
    //public String url = "jdbc:mysql://sql09.freemysql.net:3306/"+bd;
    public String url = "jdbc:mysql://localhost:3306/"+bd;
    private static Connection conn = null;
    
    public void conectarBD() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null)
            {
                System.out.println("Conexión a base de datos "+url+" ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public void desconectarBD() throws SQLException{
        conn.close();
    }
    
    public Connection conexion()
    {       
        return(conn);
    }
    
    public boolean hayConexionBD(){
       if(conn!=null)
          return true;
       else
          return false;        
    }
}