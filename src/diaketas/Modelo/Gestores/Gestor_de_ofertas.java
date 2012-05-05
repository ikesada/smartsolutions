/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Beneficiario;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Oferta;
import java.util.ArrayList;

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

    /**
     * Funcion que obtiene la lista de ofertas que satisfacen unos criterios de búsqueda basados
     * en el código, concepto y población de las mismas.
     * @return Devuelve la lista de ofertas que cumple los criterios.
     */
    
    @Override
    public ArrayList<Oferta> filtrarOfertas(int codigo, String concepto, String poblacion) {
        return diaketas.diaketas.ong.obtenerOfertas(codigo, concepto, poblacion);
    }

    /**
     * Funcion que indica al gestor la oferta que ha sido seleccionada por el voluntario.
     */
    
    @Override
    public void seleccionarOferta(int codOferta) {
        O = diaketas.diaketas.ong.buscarOferta(codOferta);
    }
    
    /**
     * Funcion que obtiene las ofertas asociadas a un determinado beneficiario
     * @return Devuelve la lista de las ofertas relacionadas con un beneficiario concreto
     */

    @Override
    public ArrayList<Oferta> obtenerListaOfertas(String dniBeneficiario, boolean[] existeBeneficiario) {
        ArrayList<Oferta> ofertas_asociadas = null;
        boolean existe;
        
        existe = diaketas.diaketas.ong.gestorBeneficiarios.comprobarExistenciaBeneficiario(dniBeneficiario);
        
        existeBeneficiario[0] = existe;
        
        if(existe) {
            System.out.println("Existe");
            Beneficiario beneficiario = diaketas.diaketas.ong.buscarBeneficiario(dniBeneficiario);
            ofertas_asociadas = beneficiario.obtenerOfertas();
        }
        
        return ofertas_asociadas;
    }

    @Override
    public boolean comprobarVoluntario(String dniVoluntario) {
        return diaketas.diaketas.ong.gestorVoluntarios.comprobarExistenciaVoluntario(dniVoluntario);
    }
    
}
