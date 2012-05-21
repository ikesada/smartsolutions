/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.Date;

/**
 *
 * @author Cesar
 */
public class AccionOferta {
    
    
    /**
     *  Cadena que indica el tipo de accion
     */
    private String Nombre;
    
    
    /**
     *  Fecha en la que se realiza la accion
     */
    private Date Fecha;
    
    
    /**
     *  Voluntario que ha realizado la accion
     */
    private Voluntario responsable;
    
    
    /**
     *  Oferta sobre la que se ha realizado la operacion
     */
    
    private Oferta oferta;
    
    
    // ------------------------------- CONSULTORES --------------------------------------- //

    public Date obtenerFecha() {
        return Fecha;
    }

    public String obtenerNombre() {
        return Nombre;
    }

    public Oferta obtenerOferta() {
        return oferta;
    }

    public Voluntario obtenerResponsable() {
        return responsable;
    }
    
    // ------------------------------- MODIFICADORES ------------------------------------- //

    
    public void modificarFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void modificarNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void modificarOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public void modificarResponsable(Voluntario responsable) {
        this.responsable = responsable;
    }
    
    
    /**
     *  Constructor de la clase AccionOferta
     * @author Miguel Jiménez López
     * @param Accion Contiene el nombre de la acción realizada
     * @param Fecha Indica la fecha en la que se realizó la acción
     */
    public AccionOferta(String Accion, Date Fecha) {
        this.Nombre = Accion;
        this.Fecha = Fecha;
    }
    
    
}
