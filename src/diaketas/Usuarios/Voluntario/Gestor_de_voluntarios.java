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
import diaketas.Usuarios.Historial.Gestor_de_historiales;
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
        
        
        
        boolean existe = Gestor_de_voluntarios.comprobarExistenciaVoluntario( (String)voluntarioDNI );
        
        if(existe)
        {
            boolean existe1 = comprobarExistenciaVoluntario(DNI);
            
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
    
            
            
    /*
    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
  
        //Se crea una nueva acción con Dni de voluntario y voluntario asociado, junto con fecha actual
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        
        //Se guarda la accion en el sistema
        //ONG.agregarAccion(ac);
        Gestor_de_historiales.RegistrarOperacion(DNI_Voluntario, DNI, Tipo);
    }
    */
    
       
    
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
    
    
    
    public static void eliminarVoluntario(String DNI)
    {
        
        //Obtenemos el voluntario
        Voluntario v = ONG.buscarVoluntario(DNI);  

        //Desactivamos al usuario
        //v.desactivarUsuario(new Date());
        Gestor_de_voluntarios.desactivarVoluntario(v, new Date());         
    }
    
    
    public static void desactivarVoluntario(Voluntario v, Date fecha_desactivacion)
    {
        
        

        /*Modificamos los datos del objeto*/
        v.Activo = 0;
        v.FechaDesac = fecha_desactivacion;
        
        //Convertimos Date para trabajar
        java.sql.Timestamp fecha_Desac = new java.sql.Timestamp(v.FechaDesac.getTime());

        ConexionBD con = new ConexionBD();
        con.conectarBD();
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /* Desactivamos el usuario y actualizamos fecha de Baja*/
            instruccion.executeUpdate("UPDATE Usuario SET Activo = " + v.Activo + ", Fecha_Desactivacion = \""
                    +fecha_Desac+"\" WHERE NIF_CIF = \"" + v.NIF_CIF + "\"");
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
    
    
    
    static public void modificarVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs, String voluntarioDNI )
    {
        
//        boolean confirma=false;
        
        
        
        boolean existe = ONG.comprobarExistenciaVoluntario(DNI);
        
        if(existe)
        {
            boolean existe1 = ONG.comprobarExistenciaVoluntario(voluntarioDNI);
            
            if(existe1)
            {
                
            
                /*confirma = */Gestor_de_voluntarios.modificarDatosVoluntario(nombre, apellidos, DNI, telf, dir, poblacion, email, nacionalidad, fechaNac, codPost, obs);
            
                Gestor_de_historiales.RegistrarOperacion(voluntarioDNI, DNI, "modificacion voluntario");
            }
        }
        
        
//        return confirma;
            
    }
    
    
    
    static public void modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs )
    {
        
        Voluntario v = ONG.buscarVoluntario(DNI);
        
        /*boolean exito = */v.cambiarDatosVoluntario( DNI, nombre, apellidos, fechaNac, poblacion, email, telf, nacionalidad, dir, codPost, obs);
        
//        return exito;
       
    }
    
    
}
