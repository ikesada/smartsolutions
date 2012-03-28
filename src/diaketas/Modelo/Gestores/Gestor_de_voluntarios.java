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

    
    Statement instruccion;
    ResultSet rs;
    ConexionBD con = new ConexionBD();
    
    
    
      
    
    public boolean comprobarExistenciaVoluntario(String DNI){

        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);
        
        
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
    
    
    

    
    
    
    public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
       
        boolean confirma=false;
        
        
        
        boolean existe = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario( (String)voluntarioDNI );
        
        if(existe)
        {
            boolean existe1 = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                
                //el constructor Date() nos devuelve la fecha actual    
                confirma = diaketas.diaketas.gestorVoluntarios.crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                diaketas.diaketas.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "alta voluntario");
                
            }
            if(existe1)
            {
                confirma=false; //si ya hay un voluntario con ese mismo DNI
            }
        }
        
        return confirma;
               
    }
    
    
    
    
    public boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs)
    {

        
        boolean confirma=true;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        
        
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, null, email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        diaketas.diaketas.ong.agregarNuevoVoluntario(v);
       
        return confirma;
    
    
    }   
    
            

    
       
    
    public boolean bajaVoluntario( String DNI, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        boolean existe = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario( voluntarioDNI );
        
        if(existe)
        {
            
            boolean existe1 = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(existe1)
            {
                
                diaketas.diaketas.gestorVoluntarios.eliminarVoluntario(DNI);
                
                diaketas.diaketas.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "baja voluntario");
               
            }
            
        }
        
        return confirma;
    
    }
    
    
    
    public  boolean eliminarVoluntario(String DNI)
    {
        boolean exito = false;
        //Obtenemos el voluntario
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);  

        //Desactivamos al usuario
        //v.desactivarUsuario(new Date());
        exito = v.desactivarVoluntario(v, new Date());   
        
        return exito;
    }
    
    
    
    
    
    
     public boolean modificarVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        
        
        boolean existe = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
        
        if(existe)
        {
            boolean existe1 = diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(voluntarioDNI);
            
            if(existe1)
            {
                
            
                confirma = diaketas.diaketas.gestorVoluntarios.modificarDatosVoluntario(nombre, apellidos, DNI, telf, dir, poblacion, email, nacionalidad, fechaNac, codPost, obs);
            
                diaketas.diaketas.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "modificacion voluntario");
            }
        }
        
        
        return confirma;
            
    }
    
    
    
     public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs )
    {
        
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);
        
        boolean exito = v.introducirDatosVoluntario( DNI, nombre, apellidos, fechaNac, poblacion, email, telf, nacionalidad, dir, codPost, obs);
        
        return exito;
       
    }
    
     public Voluntario consultarDatosVoluntario( String DNI )
    {
        
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);        
        
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
