/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Oferta;
import java.util.ArrayList;

/**
 *
 * @author Cesar
 */


public interface iGestorOfertas {

    /**
     * 
     * @param codigo codigo identificativo de la oferta
     * @param concepto  representa la categoria de la oferta
     * @param poblacion representa a la ciudad donde se desarrollara la oferta
     * @return Devuelve la lista de ofertas que casan con los criterios de busqueda
     * establecidos por los parametros de entrada al metodo.
    */
    
    public ArrayList<Oferta> filtrarOfertas(int codigo,String concepto,String poblacion);
    
    /**
     * Funcion que indica al gestor la oferta que ha sido seleccionada por el voluntario.
     * @param codOferta codigo identificativo de la oferta seleccionada
     */
    
    public void seleccionarOferta(int codOferta);
    
    /**
     * 
     * @param dniBeneficiario DNI del beneficiario del que se desean consultar las ofertas
     * @param existeBeneficiario Permite informar si el beneficiario existe o no despues de 
     * resalizar la coprobación
     * @return Devuelve la lista de ofertas asociada al beneficiario si este existe
     */
    
    public ArrayList<Oferta> obtenerListaOfertas(String dniBeneficiario, boolean[] existeBeneficiario);
    
    /**
     * 
     * @param dniVoluntario DNI del voluntario responsable
     * @return Comprueba si el voluntario esta autorizado para realizar la operación.
     */
    
    public boolean comprobarVoluntario(String dniVoluntario);
    
    /**
     * Finaliza la operación de asociar un beneficiario a una determinada oferta.
     */
    
    public void finAsociar();
    
    /**
     * Función que registra una operación realizada relacionada con la gestion de la bolsa de trabajo
     * asi como el responsable de la misma (voluntario)
     * @param NIF_CIF DNI del responsable de la acción
     * @param cod_oferta Identificador de la oferta de trabajo a la que se refiere la acción
     * @param accion Acción realizada sobre la oferta por parte del responsable
     */
    
    public void registrarOperacionOfertas(String NIF_CIF, int cod_oferta, String accion);
    
}
