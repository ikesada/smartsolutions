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
    public String Nombre;
    
    
    /**
     *  Fecha en la que se realiza la accion
     */
    public Date Fecha;
    
    
    /**
     *  Voluntario que ha realizado la accion
     */
    public Voluntario responsable;
    
    
    /**
     *  Oferta sobre la que se ha realizado la operacion
     */
    
    public Oferta oferta;
    
    
    public AccionOferta(String Accion, Date Fecha) {
        this.Nombre = Accion;
        this.Fecha = Fecha;
    }
    
    
}
