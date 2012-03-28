/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

//antes tenia este: import com.mysql.jdbc.ResultSet;

//sustituido por estos dos:
import java.sql.ResultSet;
//import com.mysql.jdbc.ResultSet;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Accion;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Voluntario;
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

    
    static Statement instruccion;
    static ResultSet rs;
    static ConexionBD con = new ConexionBD();
    
    
    
      
    
    static public boolean comprobarExistenciaVoluntario(String DNI){

        Voluntario v = ONG.buscarVoluntario(DNI);
        
        
        if(v!=null)
        {
            if( v.Activo==1 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        
    }
    
    
    //////////////////////////////////////////////////////////////////
    
    
    

    
    
    
    static public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
       
        boolean confirma=false;
        
        
        
        boolean existe = Gestor_de_voluntarios.comprobarExistenciaVoluntario( (String)voluntarioDNI );
        
        if(existe)
        {
            boolean existe1 = Gestor_de_voluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                
                //el constructor Date() nos devuelve la fecha actual    
                confirma = Gestor_de_voluntarios.crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                Gestor_de_historiales.RegistrarOperacion(voluntarioDNI, DNI, "alta voluntario");
                
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

        
        boolean confirma=true;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        
        
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, null, email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        ONG.agregarNuevoVoluntario(v);
       
        return confirma;
    
    
    }   
    
            

    
       
    
    static public boolean bajaVoluntario( String DNI, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        boolean existe = Gestor_de_voluntarios.comprobarExistenciaVoluntario( voluntarioDNI );
        
        if(existe)
        {
            
            boolean existe1 = Gestor_de_voluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(existe1)
            {
                
                Gestor_de_voluntarios.eliminarVoluntario(DNI);
                
                Gestor_de_historiales.RegistrarOperacion(voluntarioDNI, DNI, "baja voluntario");
               
            }
            
        }
        
        return confirma;
    
    }
    
    
    
    public static boolean eliminarVoluntario(String DNI)
    {
        boolean exito = false;
        //Obtenemos el voluntario
        Voluntario v = ONG.buscarVoluntario(DNI);  

        //Desactivamos al usuario
        //v.desactivarUsuario(new Date());
        exito = v.desactivarVoluntario(v, new Date());   
        
        return exito;
    }
    
    
    
    
    
    
    static public boolean modificarVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        
        
        boolean existe = Gestor_de_voluntarios.comprobarExistenciaVoluntario(DNI);
        
        if(existe)
        {
            boolean existe1 = Gestor_de_voluntarios.comprobarExistenciaVoluntario(voluntarioDNI);
            
            if(existe1)
            {
                
            
                confirma = Gestor_de_voluntarios.modificarDatosVoluntario(nombre, apellidos, DNI, telf, dir, poblacion, email, nacionalidad, fechaNac, codPost, obs);
            
                Gestor_de_historiales.RegistrarOperacion(voluntarioDNI, DNI, "modificacion voluntario");
            }
        }
        
        
        return confirma;
            
    }
    
    
    
    static public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs )
    {
        
        Voluntario v = ONG.buscarVoluntario(DNI);
        
        boolean exito = v.introducirDatosVoluntario( DNI, nombre, apellidos, fechaNac, poblacion, email, telf, nacionalidad, dir, codPost, obs);
        
        return exito;
       
    }
    
    static public Voluntario consultarDatosVoluntario( String DNI )
    {
        
        Voluntario v = ONG.buscarVoluntario(DNI);        
        
        if(v!=null)
        {
            if( v.Activo==1 )
            {
                return v;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return v;
        }
    }
    
    
}
