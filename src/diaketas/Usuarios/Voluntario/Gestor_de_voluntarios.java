/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;

//antes tenia este: import com.mysql.jdbc.ResultSet;

//sustituido por estos dos:
import java.sql.ResultSet;
//import com.mysql.jdbc.ResultSet;

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

    
    static Statement instruccion;
    static ResultSet rs;
    static ConexionBD con = new ConexionBD();
    
    
    
      
    
    static public boolean comprobarExistenciaVoluntario(String DNI){

        Voluntario vol = ONG.buscarVoluntario(DNI);
        
        
        return (vol != null);
    }
    
    
    //////////////////////////////////////////////////////////////////
    
    
    

    
    
    
    static public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
       
        boolean confirma=false;
        
        System.out.println("Voy a ver si existe el voluntario con dni:"+voluntarioDNI);
        
        boolean existe = Gestor_de_voluntarios.comprobarExistenciaVoluntario( (String)voluntarioDNI );
        System.out.println("Exite:"+existe);
        if(existe)
        {
            boolean existe1 = comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                
                //el constructor Date() nos devuelve la fecha actual    
                confirma = Gestor_de_voluntarios.crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                Gestor_de_voluntarios.RegistrarOperacion(voluntarioDNI, DNI, "alta voluntario");
                
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
        
        
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, new Date(0,0,0), email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        ONG.agregarNuevoVoluntario(v);
       //Gestor_de_voluntarios.añadirVoluntario(v);
        return confirma;
    
    
    }   
    
            
            
    
    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
  
        //Se crea una nueva acción con Dni de voluntario y voluntario asociado, junto con fecha actual
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        
        //Se guarda la accion en el sistema
        ONG.agregarAccion(ac);
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
                
                Gestor_de_voluntarios.RegistrarOperacion(voluntarioDNI, DNI, "baja voluntario");
               
            }
            
        }
        
        return confirma;
    
    }
    
    
    
    public static void eliminarVoluntario(String DNI)
    {
        
        //Obtenemos el voluntario
        Voluntario v = ONG.buscarVoluntario(DNI);  

        //Desactivamos al usuario
        v.desactivarUsuario(new Date());
                 
    }
    
    
    
    
    static public boolean modificarVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        
        
        boolean existe = ONG.comprobarExistenciaVoluntario(DNI);
        
        if(existe)
        {
            boolean existe1 = ONG.comprobarExistenciaVoluntario(voluntarioDNI);
            
            if(existe1)
            {
                
            
                confirma = Gestor_de_voluntarios.modificarDatosVoluntario(nombre, apellidos, DNI, telf, dir, poblacion, email, nacionalidad, fechaNac, codPost, obs);
            
                Gestor_de_voluntarios.RegistrarOperacion(voluntarioDNI, DNI, "modificacion voluntario");
            }
        }
        
        
        return confirma;
            
    }
    
    
    
    static public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs )
    {
        
        Voluntario v = ONG.buscarVoluntario(DNI);
        
        boolean exito = v.cambiarDatosVoluntario( DNI, nombre, apellidos, fechaNac, poblacion, email, telf, nacionalidad, dir, codPost, obs);
        
        return exito;
       
    }
    
    
}
