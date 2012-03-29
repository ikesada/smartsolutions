/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Voluntario;
import java.util.Date;

/**
 *
 * @author Cesar
 */
public interface iGestorVoluntarios {
        
        
    /**
     * @brief Funcion que se encarga de añadir a un Voluntario en el sistema
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
    public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI );
    
    
    
    
    /**
     * @brief Funcion que se encarga de dar de baja a un voluntario en el sistema
     * @param DNI Dni del voluntario a eliminar
     * @param voluntarioDNI Dni del voluntario que realiza la accion
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    public boolean bajaVoluntario( String DNI, String voluntarioDNI );
    
    
    /**
     * @brief Funcion que devuelve el voluntario asociado a ese DNI
     * @param DNI Dni del voluntario a consultar
     * @return Voluntario asociado a ese dni, si el voluntario esta desactivado devuelve null
     */
    public Voluntario consultarVoluntario( String DNI );
    
    
    
    /**
     * @brief Funcion que se encarga de añadir al voluntario en la BBDD
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
    public boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs);
    
    
    
    /**
     * @brief Funcion que se encarga de poner a inactivo un voluntario en el sistema
     * @param DNI Dni del voluntario a desactivar
     * @return Devuelve True o False si se ha realizado la operacion con exito o no
     */
    public boolean eliminarVoluntario(String DNI);

    
    
    
    /**
     * @brief Funcion que se encarga de actualizar los datos asociados a un Voluntario en la BD
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
    public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs );
  
    
    
    /**
     * @brief Funcion que devuelve el Voluntario asociado a ese DNI
     * @param DNI
     * @return Devuelve el Voluntario asociado a ese dni, sin comprobar si esta desactivado o no
     */
    public Voluntario obtenerDatosVoluntario( String DNI );
  
    
    
    /**
     * @brief Funcion que nos dice si el usuario existe o no
     * @param NIF_CIF
     * @return Devuelve True si se ha encontrado al voluntario en el sistema
     */
    public Boolean introducirDNIVoluntario(String NIF_CIF);
    
    
}
