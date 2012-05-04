/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Oferta;

/**
 *
 * @author Cesar
 */
public class Gestor_de_ofertas implements iGestorOfertas{
    
    
    /**
     * Oferta sobre la que trabaja el Gestor
     */
    Oferta O;
    
    
    /**
     * NIF del Voluntario que esta trabajando actualmente sobre el sistema
     */
    String NIF_Voluntario;  
    
    
    
    
    
    
    /*------------------OPERACIONES------------------*/
    
    /**
     * 
     * Funcion que devuelve el objeto Oferta con el que trabaja el Gestor
     * @return Devuelve el objeto Oferta
     */ 
    public Oferta consultarDatosOferta()
    {
        
        Oferta datosOferta = O.obtenerDatosOferta();

        return datosOferta;
    }
    
    
    
    
    public void finModificarOferta()
    {
        //FALTA CREAR ESTA OPERACION 
        //registrarOperacionOfertas( NIF_Voluntario, O.cod_oferta, "Modificacion Oferta" );
    }
    
    
    public Boolean introducirOferta(Oferta nuevosDatosOferta, String dniV)
    {
        
        Boolean error=false;
        
        
        
        return error;
    
    }
}
