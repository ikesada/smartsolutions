/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Oferta;
import java.util.ArrayList;
import java.util.Date;

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
    
    
    
    /**
     * 
     * Funcion que devuelve el objeto Oferta con el que trabaja el Gestor
     * @return Devuelve el objeto Oferta
     */ 
    public Oferta consultarDatosOferta();
    
    
    
    /**
     * 
     * Funcion que registra una accion de modificacion en el historial
     */ 
    public void finModificarOferta();
    
    
    /**
     * Funcion que modifica los datos asociados a la Oferta
     * @param Cod_Oferta Nuevo codigo a asociar a la oferta
     * @param Concepto Nuevo concepto a asociar a la oferta
     * @param FechaOf Nueva fecha a asociar a la oferta
     * @param Activada Nuevo valor de activacion a asociar a la oferta
     * @param Poblacion Nueva poblacion a asociar a la oferta 
     * @param Num_vacantes Nuevo numero de plazas vacantes a asociar a la oferta
     * @param Descripcion Nueva descripcion a asociar a la oferta
     * @param Req_minimos Nuevos requisitos minimos a asociar a la oferta
     * @param Tipo_contrato Nuevo tipo de contrato a asociar a la oferta
     * @param Jornada_laboral Nueva jornada laboral a asociar a la oferta
     * @param Salario Nuevo salario a asociar a la oferta
     * @param DNI_donante Nuevo dni de Donante a asociar a la oferta
     * @param Observaciones Nuevas observaciones a asociar a la oferta
     * @param dniV DNI del Voluntario que realiza la modificacion
     * @return Devuelve true si ha habido algun error al realizar la operacion
     */             
    public Boolean introducirOferta( Integer Cod_Oferta, String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones, String dniV );

    
    
    
    
    /**
     * Funcion que confirma la modificacion de los datos asociados a la Oferta
     * @param Cod_Oferta Nuevo codigo a asociar a la oferta
     * @param Concepto Nuevo concepto a asociar a la oferta
     * @param FechaOf Nueva fecha a asociar a la oferta
     * @param Activada Nuevo valor de activacion a asociar a la oferta
     * @param Poblacion Nueva poblacion a asociar a la oferta 
     * @param Num_vacantes Nuevo numero de plazas vacantes a asociar a la oferta
     * @param Descripcion Nueva descripcion a asociar a la oferta
     * @param Req_minimos Nuevos requisitos minimos a asociar a la oferta
     * @param Tipo_contrato Nuevo tipo de contrato a asociar a la oferta
     * @param Jornada_laboral Nueva jornada laboral a asociar a la oferta
     * @param Salario Nuevo salario a asociar a la oferta
     * @param DNI_donante Nuevo dni de Donante a asociar a la oferta
     * @param Observaciones Nuevas observaciones a asociar a la oferta
     * @return Devuelve true si ha habido algun error al realizar la operacion
     */     
    public Boolean confirmarModificacion(Integer Cod_Oferta, String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones);
    
    
}
