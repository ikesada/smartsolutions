/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.ONG;
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
    
    //devuelve true si ha habido algun error
    public Boolean introducirOferta(Oferta nuevosDatosOferta, String dniV)
    {
        
        Boolean error=true;
        
        Boolean encontradoUsuario = false;
        
        encontradoUsuario = ONG.gestorDonantes.comprobarDniDonante( nuevosDatosOferta.NIF_CIF_Donante );
        
        if( encontradoUsuario )
        {
            encontradoUsuario = ONG.gestorVoluntarios.comprobarExistenciaVoluntario( dniV );
        }
        
        if( encontradoUsuario ) //si ha encontrado a los dos usuarios, hago la modificacion sobre la oferta
        {
            error = confirmarModificacion(nuevosDatosOferta);   //devuelve true si hay algun error
        }
        
        return error;
    
    }
    
    //devuelve true si hay algun error
    public Boolean confirmarModificacion(Oferta nuevosDatosOferta)
    {
        
        Boolean hay_error;
        
        //la operacion "modificar" devuelve true si todo ha ido bien
        Boolean exito = O.modificar(nuevosDatosOferta);
        
        if( exito )
        {
            hay_error=false;
        }
        else
        {
            hay_error=true;
        }
        
        return hay_error;
    }
    
}
