/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.Date;

/**
 *
 * @author Alex
 */
public class Accion {
    /**
     *  Dni del Voluntario que realiza la accion
     */
    private String DNI_Voluntario;
    /**
     *  Dni del usuario sobre el cual se realiza la accion
     */
    private String DNI_Usuario;
    /**
     *  Fecha en la que se realiza la accion
     */
    private Date Fecha;
    /**
     *  Cadena que indica el tipo de accion
     */
    private String tipo;
    
    
// -------------------------------------- CONSULTORES ------------------------------------------ //

    public String obtenerDNIUsuario() {
        return DNI_Usuario;
    }

    public String obtenerDNIVoluntario() {
        return DNI_Voluntario;
    }

    public Date obtenerFecha() {
        return Fecha;
    }

    public String obtenerTipo() {
        return tipo;
    }


// -------------------------------------- MODIFICADORES ---------------------------------------- //

    public void modificarDNIUsuario(String DNI_Usuario) {
        this.DNI_Usuario = DNI_Usuario;
    }

    public void modificarDNIVoluntario(String DNI_Voluntario) {
        this.DNI_Voluntario = DNI_Voluntario;
    }

    public void modificarFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void modificarTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    /**
     * 
     * @param DNI_Voluntario Dni del Voluntario que realiza la accion
     * @param DNI_Usuario Dni del usuario sobre el cual se realiza la accion
     * @param tipo Cadena que indica el tipo de accion
     * @param Fecha Fecha en la que se realiza la accion
     */
    public Accion(String DNI_Voluntario, String DNI_Usuario, String tipo, Date Fecha) {
        this.DNI_Voluntario = DNI_Voluntario;
        this.DNI_Usuario = DNI_Usuario;
        this.Fecha = Fecha;
        this.tipo = tipo;
    }
}
