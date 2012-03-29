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
        
        
    /*
     * Funcion que se encarga de registrar a un Voluntario en la BD, para ello primero se comprueba si el voluntario con 
     * NIF_CIF "voluntarioDNI" esta registrado en el sistema, despues se comprueba si el nuevo voluntario que voy a 
     * introducir con NIF_CIF "DNI" ya existe, si no existe se llama a la funcion crearVoluntario y se registra la accion  
     */
    public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI );
    
    
    
    /*
     * Funcion que se encarga de dar de baja a un voluntario en el sistema, para ello primero se comprueba si el 
     * voluntario con NIF_CIF "voluntarioDNI" esta registrado en el sistema, despues se comprueba si el voluntario a eliminar
     * con NIF_CIF "DNI" existe, si existe se llama a la funcion eliminarVoluntario y se registra la accion
     */
    public boolean bajaVoluntario( String DNI, String voluntarioDNI );
    
    
    /*
     * Funcion que devuelve el voluntario asociado a ese DNI, si el voluntario esta desactivado devolvera un null
     */
    public Voluntario consultarVoluntario( String DNI );
    
    
    /*
     * Funcion que se encarga de añadir al voluntario en el sistema, introduciendo una nueva tupla en la tabla Usuario 
     * y otra tupla en la tabla Voluntario
     */
    public boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs);
    
    
    /*
     * Funcion que se encarga de poner a inactivo un voluntario en el sistema, para ello lo que hace es modificar
     * los campos "Activo" y la fecha de desactivacion del voluntario pasado
     */
    public boolean eliminarVoluntario(String DNI);

    
    
    /*
     * Funcion que se encarga de actualizar los datos asociados a un Voluntario en la BD, para ello realiza dos 
     * actualizaciones, una en la tabla Usuario y otra en la tabla Voluntario
     */
    public boolean modificarDatosVoluntario( String nombre, String apellidos, String DNI, int telf, String dir, String poblacion, String email, String nacionalidad, Date fechaNac, int codPost, String obs );
  
    
    /*
     * Funcion que devuelve el Voluntario asociado a ese DNI, sin tener en cuenta si el voluntario esta activado o no
     */
    public Voluntario obtenerDatosVoluntario( String DNI );
  
    
    /*
     * Funcion que nos dice si el usuario existe o no
     */
    public Boolean introducirDNIVoluntario(String NIF_CIF);
    
    
}
