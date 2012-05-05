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
     * Introduce los datos de un movimiento en el sistema
     */
    @Override
    public int introducirMovimiento(int tipo, double cuantia, String involucrado, String descripcion, String voluntario) {
        if ((tipo == 0 || tipo == 1 || tipo == 2) && !ONG.gestorDonantes.comprobarDniDonante(involucrado)) {
            return 1;
        } else if ((tipo == 3 || tipo == 4 || tipo == 5) && !ONG.gestorBeneficiarios.comprobarExistenciaBeneficiario(involucrado)) {
            return 2;
        }
        
        // Guardamos los datosMovimiento
        //datosMovimiento.Tipo_Movimiento = tipo;
        datosMovimiento.cuantia = cuantia;
        
        
        return 0;
    }
    
    /**
     * Procede a registrar el movimiento
     */
    @Override
    public void finRegistrarMovimiento(){
        
    }
    
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
    
   /**
     * Comprueba que el DNI del Voluntario es valido
     * @param dniVoluntario DNI del voluntario responsable
     * @return Un booleano indicando si el dni del voluntario es valido o no
     */ 
    @Override
    public boolean comprobarVoluntario(String dniVoluntario) {
        return ONG.gestorVoluntarios.comprobarExistenciaVoluntario(dniVoluntario);
    }
    
    @Override
    public void finEliminarMovimiento(){
        confirmarEliminacion(movimientoSeleccionado.Cod_Movimiento);
    }
    
    public void confirmarEliminacion(int Cod_Movimiento){
        
        
    }

}
