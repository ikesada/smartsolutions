/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Movimiento;

/**
 *
 * @author Miguel Á. Quesada
 * @author Jesús Carabaño Bravo
 * @author Fran Dios Buitrago
 * @author Alejandro Portillo Guerrero
 */
public interface iGestorDonaciones {
    
    /**
    * Consulta los datos del movimiento seleccionado
    * @return Un objeto con los datos asociados al movimiento seleccionado
    */
    public Movimiento consultarDatosMovimiento();
    
    /**
     * Confirma un movimiento bajo el dni del voluntario siempre que sea valido
     * @param dniV Dni del volntario que va a realizar la confirmación
     * @return Un booleano indicando si el dni del voluntario es válido
     */
    public boolean confirmarMovimiento(String dniv);
    
    /**
     * Realiza una modificación sobre el movimiento seleccionado
     */
     public void confirmarModificacion ();
}
