/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

/**
 *
 * @author kesada
 */
import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String url = "jdbc:mysql://bioinfo2.ugr.es:3306/"+bd;
    
    private static Connection conn = null;
    
    /**
     *  Funcion que comprueba si existe conexión a la BBDD.
     *  En caso negativo lanza de nuevo la conexión.
     */
    public void conectarBD() {
       try {
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT 1 FROM Dual");
        if (rs.next()) {
        }
        }catch (SQLException e) {
            System.out.println("Conexion cerrada...Abriendo");
            realizarConexionBD();
        }
    }

    public void realizarConexionBD(){
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
    public void realizarDesconexionBD() throws SQLException{
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
