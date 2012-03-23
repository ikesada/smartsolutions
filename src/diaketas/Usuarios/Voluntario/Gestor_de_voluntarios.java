/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.Accion;
import diaketas.Usuarios.Email;
import diaketas.Usuarios.ONG;
import diaketas.Usuarios.Telefono;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */


public class Gestor_de_voluntarios {
   
    
    
    //////////////////////////////////////////////////////////////////////////////////////
                            /*IMPORTADO DE LA CLASE ONG*/
    
    
    static Statement instruccion;
    static ResultSet tabla;
    static ConexionBD con = new ConexionBD();
    
    
    
    
    static Voluntario buscarVoluntario(String DNI){
        Voluntario v = null;
        try {
            instruccion = (Statement) con.conexion().createStatement();
            tabla = (ResultSet) instruccion.executeQuery("SELECT COUNT(v.NIF_CIF) FROM Usuario u, Voluntario v"
                    + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+DNI+"\"");
            if(tabla.next()){
                //Crear Voluntario
                
            }
                
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }        
        
        return v;
    }
    
    
    static public boolean comprobarExistenciaVoluntario(String DNI){
        Voluntario vol = buscarVoluntario(DNI);
        return (vol != null);
    }
    
    
    
    
    
    public static void agregarAccion(Accion ac){
        
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha = new java.sql.Timestamp(ac.Fecha.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            instruccion.executeUpdate("INSERT INTO Accion(Nombre, Fecha,"
                                        + "NIF_CIF_Voluntario, NIF_CIF_Usuario) VALUES (\""
                                        + ac.tipo + "\",\"" + fecha + "\",\"" + ac.DNI_Voluntario+"\",\""+ac.DNI_Usuario+"\")");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    //////////////////////////////////////////////////////////////////
    
    
    

    public static boolean añadirVoluntario()
    {
        
        //realizo un insert en la tabla Voluntario de la BD
        
        return true;
            
    }
    
    
    static public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
        
        boolean confirma=false;
        
        boolean existe = comprobarExistenciaVoluntario(voluntarioDNI);
        
        if(existe)
        {
            boolean existe1 = comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                //el constructor Date() nos devuelve la fecha actual    
                confirma = Voluntario.crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                registrarOperacion(DNI, voluntarioDNI, "alta voluntario", new Date());  //le paso la fecha actual
            }
            if(existe1)
            {
                confirma=false; //si ya hay un voluntario con ese mismo DNI
            }
        }
        
        return confirma;
                
    }
    
    
    
    
            
            
    static public void registrarOperacion(String DNI, String voluntarioDNI, String tipoOperacion, Date fecha)
    {
    
        
        
    }
}
