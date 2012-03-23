/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
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
                confirma = crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                registrarOperacion(DNI, voluntarioDNI, "alta voluntario", new Date());  //le paso la fecha actual
            }
            if(existe1)
            {
                confirma=false; //si ya hay un voluntario con ese mismo DNI
            }
        }
        
        return confirma;
                
    }
    
    
    
    
    public static boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs)
    {

        
        boolean confirma;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, new Date(0,0,0), email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        confirma = Gestor_de_voluntarios.añadirVoluntario(v);
       
        return confirma;
    
    
    }   
    
            
            
    static public void registrarOperacion(String DNI, String voluntarioDNI, String tipoOperacion, Date fecha)
    {
    
        
        
    }
    
    
    
    public static boolean añadirVoluntario(Voluntario v)
    {
        
        boolean exito=true;
        //realizo dos insert, uno en la tabla Voluntario y otro en la tabla Usuario 
        
        
        //primero nos conectamos a la BD
        con.conectarBD();
        
        try {
        instruccion = (Statement) con.conexion().createStatement();

        //inserto tupla en la tabla Voluntario
        instruccion.executeUpdate("INSERT INTO Voluntario( NIF_CIF, Nacionalidad, Domicilio, Codigo_Postal, Fecha_Inicio, Observaciones  ) VALUES (\""
                                    + v.NIF_CIF + "\",\"" + v.Nacionalidad + "\",\"" + v.Domicilio+"\",\""+v.Codigo_Postal+ "\",\"" +v.Fecha_Inicio+ "\",\"" +v.Observaciones+"\")");
        }
        /*Captura de errores*/
        catch(SQLException e){ 
            System.out.println(e); 
            exito=false;
        }
        catch(Exception e){ 
            System.out.println(e);
            exito=false;
        }

        
        ////////////////////
        
        //ahora hago el otro insert, esta vez en la tabla Usuario
        try {
        instruccion = (Statement) con.conexion().createStatement();

        //inserto tupla en la tabla Usuario
        instruccion.executeUpdate("INSERT INTO Usuario( NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Fecha_Desactivacion, Email, Telefono  ) VALUES (\""
                                    + v.NIF_CIF + "\",\"" + v.Nombre + "\",\"" + v.Apellidos+"\",\"" +v.FechaNac+ "\",\"" +v.Localidad+ "\",\"" +v.Activo+ "\",\"" +v.FechaDesac+ "\",\"" +v.Email+ "\",\"" +v.Telefono+"\")");
        }
        /*Captura de errores*/
        catch(SQLException e){ 
            System.out.println(e); 
            exito=false;
        }
        catch(Exception e){ 
            System.out.println(e);
            exito=false;
        }
        
        
        
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
        
     
        return exito;
            
    }
}
