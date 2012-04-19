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
/**
 * 
 * @author Alex
 */
public class ConexionBD{
    /**
     * Nombre de la BBDD sobre la que se trabaja
     */
    public String bd = "diaketas";
    /**
     * Usuario de la BBDD
     */
    public String login = "smartsolutions";
    /**
     * Contrasenya de la BBDD
     */
    public String password = "solutions";
    /**
     * URL en la que se encuentra la BBDD
     */
    public String url = "jdbc:mysql://sql09.freemysql.net:3306/"+bd;
    //public String url = "jdbc:mysql://192.168.1.1:3306/"+bd;
    //public String url = "jdbc:mysql://localhost:3306/"+bd;
    
    private static Connection conn = null;
    
    /**
     *  Funcion conecta con la BBDD
     */
    public void conectarBD() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(url, login, password);
        }
        catch(SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     *  Desconecta de la BBDD
     * @throws SQLException
     */
    public void desconectarBD() throws SQLException{
        conn.close();
    }
    
    /**
     *  Realiza la convexion
     * @return Devuelve la conexion a la BBDD
     */
    public Connection conexion()
    {       
        return(conn);
    }
    
    /**
     *  Comprueba si hay conexion con la BBDD o no
     * @return Devuelve True o False si la conexion existe
     */
    public boolean hayConexionBD(){
       if(conn!=null)
          return true;
       else
          return false;        
    }
}
