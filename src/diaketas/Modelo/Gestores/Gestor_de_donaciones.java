/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Movimiento;
import diaketas.Modelo.ONG.ONG;

/**
 *
 * @author Miguel Á. Quesada
 * @author Jesús Carabaño Bravo
 * @author Fran Dios Buitrago
 * @author Alejandro Portillo Guerrero
 */
public class Gestor_de_donaciones implements iGestorDonaciones {
    
    /**
     * Movimiento que ha sido seleccionado durante el proceso de Listar Movimiento
     */
    Movimiento movimientoSeleccionado;
    
    /**
     * Datos introducidos del movimiento
     */
    Movimiento datosMovimiento;
    
    
    /**
     * Consulta los datos del movimiento seleccionado
     * @return Un objeto con los datos asociados al movimiento seleccionado
     */
    @Override
    public Movimiento consultarDatosMovimiento(){
        return movimientoSeleccionado;
    }
    
    /**
     * Confirma un movimiento bajo el dni del voluntario siempre que sea valido
     * @param dniV Dni del volntario que va a realizar la confirmación
     * @return Un booleano indicando si el dni del voluntario es válido
     */
    @Override
    public boolean confirmarMovimiento(String dniV){
        
        if(ONG.gestorVoluntarios.comprobarExistenciaVoluntario(dniV)){
            confirmarConfirmacion(dniV);
            return true;
        }    
        return false;
    }

    /**
    * Realiza una modificación sobre el movimiento seleccionado
    */
    @Override
    public void confirmarModificacion (){
        movimientoSeleccionado.modificar(datosMovimiento.cuantia, datosMovimiento.descripcion);
    }
    

    /**
     * Realiza la confirmación definitiva sobre el movimiento seleccionado
     * @param dniV Dni del voluntario que realiza la confirmacion
     */
    private void confirmarConfirmacion (String dniV){
        movimientoSeleccionado.confirmar(dniV);
    }
}
