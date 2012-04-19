/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;


import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Voluntario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Cesar
 */


public class Gestor_de_voluntarios implements iGestorVoluntarios {

    
    Statement instruccion;
    ResultSet rs;
    ConexionBD con = new ConexionBD();
    
    
    
      
    
    /**
     * Funcion que nos dice si el usuario existe o no
     * @param NIF_CIF
     * @return Devuelve True si se ha encontrado al voluntario en el sistema
     */
    @Override
    public Boolean introducirDNIVoluntario(String NIF_CIF) {

        return ONG.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_CIF);
    }
    
    
    
    /**
     * Funcion que comprueba si existe un voluntario en el sistema
     * @param DNI Dni del voluntario a buscar
     * @return Devuelve true si se encuentra al voluntario
     */
    public boolean comprobarExistenciaVoluntario(String DNI){

        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);
              
        if(v!=null)
        {
            if( v.Activo==1 )
               return true;
            
            else
               return false;
        }
        else
        {
            return false;
        }
        
    }
    
    
    
    
    /**
     * Funcion que se encarga de añadir a un Voluntario en el sistema
     * @param DNI Dni del voluntario
     * @param nombre Nombre del voluntario
     * @param apellidos Apellidos del voluntario
     * @param fechaNacim Fecha de nacimiento del voluntario
     * @param ciudad Ciudad del voluntario
     * @param email Email del voluntario
     * @param telf Telefono del voluntario
     * @param nacionalidad Nacionalidad del voluntario
     * @param direccion Direccion del voluntario
     * @param codPost Codigo postal de la ciudad del voluntario
     * @param obs Observaciones relacionadas con el nuevo voluntario
     * @param voluntarioDNI Dni del voluntario que realiza el alta en el sistema
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    @Override
    public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
       
        boolean confirma=false;
        
        
        
        boolean existe = ONG.gestorVoluntarios.comprobarExistenciaVoluntario( (String)voluntarioDNI );
        
        if(existe)
        {
            boolean existe1 = ONG.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                
                //el constructor Date() nos devuelve la fecha actual    
                confirma = ONG.gestorVoluntarios.crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                ONG.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "Alta Voluntario");
                
            }
            if(existe1)
            {
                confirma=false; //si ya hay un voluntario con ese mismo DNI
            }
        }
        
        return confirma;
               
    }
    
    
    
    
    /**
     * Funcion que se encarga de añadir al voluntario en la BBDD
     * @param DNI Dni del voluntario
     * @param nombre Nombre del voluntario
     * @param apellidos Apellidos del voluntario
     * @param fechaNacim Fecha de nacimiento del voluntario
     * @param ciudad Ciudad del voluntario
     * @param email Email del voluntario
     * @param telf Telefono del voluntario
     * @param nacionalidad Nacionalidad del voluntario
     * @param direccion Direccion del voluntario
     * @param codPost Codigo postal de la ciudad del voluntario
     * @param obs Observaciones relacionadas con el voluntario
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    @Override
    public boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs)
    {

        
        boolean confirma=true;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        
        
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, null, email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        confirma = diaketas.diaketas.ong.agregarNuevoVoluntario(v);
       
        return confirma;
    
    
    }   
    
            

    
       
    
    /**
     * Funcion que se encarga de dar de baja a un voluntario en el sistema
     * @param DNI Dni del voluntario a eliminar
     * @param voluntarioDNI Dni del voluntario que realiza la accion
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    @Override
    public boolean bajaVoluntario( String DNI, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        boolean existe = ONG.gestorVoluntarios.comprobarExistenciaVoluntario( voluntarioDNI );
        
        if(existe)
        {
            
            boolean existe1 = ONG.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
            
            if(existe1)
            {
                
                confirma = ONG.gestorVoluntarios.eliminarVoluntario(DNI);
                
                ONG.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "Baja Voluntario");
               
            }
            
        }
        
        return confirma;
    
    }
    
    
    
    /**
     * Funcion que se encarga de poner a inactivo un voluntario en el sistema
     * @param DNI Dni del voluntario a desactivar
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    @Override
    public  boolean eliminarVoluntario(String DNI)
    {
        boolean exito = false;
        //Obtenemos el voluntario
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);  

        //Desactivamos al usuario
        exito = v.desactivarVoluntario(v, new Date());   
        
        return exito;
    }
    
    
    
    
    
    
    /**
     * Funcion que se encarga de actualizar los datos asociados a un Voluntario
     * @param nombre Nombre del voluntario
     * @param apellidos Apellidos del voluntario
     * @param DNI Dni del voluntario
     * @param telf Telefono del voluntario
     * @param dir Direccion del voluntario
     * @param poblacion Poblacion del voluntario
     * @param email Email del voluntario
     * @param nacionalidad Nacionalidad del voluntario
     * @param fechaNac Fecha de nacimiento del voluntario
     * @param codPost Codigo postal de la ciudad del voluntario
     * @param obs Observaciones relacionadas con el voluntario
     * @param voluntarioDNI Dni del voluntario que realiza la accion
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */

    public boolean modificarVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        
        
        boolean existe = ONG.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
        
        if(existe)
        {
            boolean existe1 = ONG.gestorVoluntarios.comprobarExistenciaVoluntario(voluntarioDNI);
            
            if(existe1)
            {
                
            
                confirma = ONG.gestorVoluntarios.modificarDatosVoluntario(nombre, apellidos, DNI, telf, dir, poblacion, email, nacionalidad, fechaNac, codPost, obs);
            
                ONG.gestorHistoriales.RegistrarOperacion(voluntarioDNI, DNI, "Modificacion Voluntario");
            }
        }
        
        
        return confirma;
            
    }
    
    
    
    /**
     * Funcion que se encarga de actualizar los datos asociados a un Voluntario en la BD
     * @param nombre Nombre del voluntario
     * @param apellidos Apellidos del voluntario
     * @param DNI Dni del voluntario
     * @param telf Telefono del voluntario
     * @param dir Direccion del voluntario
     * @param poblacion Poblacion del voluntario
     * @param email Email del voluntario
     * @param nacionalidad Nacionalidad del voluntario
     * @param fechaNac Fecha de nacimiento del voluntario
     * @param codPost Codigo postal de la ciudad del voluntario
     * @param obs Observaciones relacionadas con el voluntario
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    @Override
    public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs )
    {
        
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);
        
        boolean exito = v.introducirDatosVoluntario( DNI, nombre, apellidos, fechaNac, poblacion, email, telf, nacionalidad, dir, codPost, obs);
        
        return exito;
       
    }
    
    
    
    
    
    /**
     * Funcion que devuelve el Voluntario asociado a ese DNI
     * @param DNI
     * @return Devuelve el Voluntario asociado a ese dni
     */
    @Override
    public Voluntario consultarVoluntario( String DNI )
    {
        
        Voluntario v = null;
        
        //si el voluntario esta desactivado, devolvera existe=false
        boolean existe = ONG.gestorVoluntarios.comprobarExistenciaVoluntario(DNI);
        
        
        if(existe)
        {
            v = ONG.gestorVoluntarios.obtenerDatosVoluntario(DNI);
        }
        
        
        return v;
        
        
    }
    
    
    
    
    /**
     * Funcion que devuelve el Voluntario asociado a ese DNI
     * @param DNI
     * @return Devuelve el Voluntario asociado a ese dni, sin comprobar si esta desactivado o no
     */
    @Override
    public Voluntario obtenerDatosVoluntario( String DNI )
    {
        
        Voluntario v = diaketas.diaketas.ong.buscarVoluntario(DNI);        
        
        return v;
    }

    
    /**
     * Funcion que devuelve un listado con todos los Voluntarios del sistema
     * @return Devuelve un listado con todos los Voluntarios del sistema
     */
    public static ArrayList<Voluntario> obtenerVoluntarios()
    {
        
        ArrayList<Voluntario> usuarios = new ArrayList<Voluntario>();
        
        usuarios = diaketas.diaketas.ong.buscarVoluntarios();
        
        return usuarios;
        
    }
    
    
}
