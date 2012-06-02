/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Movimiento;
import diaketas.Modelo.ONG.ONG;
import java.util.Date;
import java.util.ArrayList;

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
     * DNI del responsable (voluntario) que realiza la operacion
     */
    private String dniV;
    
    /**
     * Introduce los datos de un movimiento en el sistema
     */
    @Override
    public int introducirMovimiento(String tipo, double cuantia, String involucrado, String descripcion, String voluntario) {
        if (("Donacion Efectiva".equals(tipo) || "Donacion Bancaria".equals(tipo) || "Donacion Material".equals(tipo)) 
            && !ONG.gestorDonantes.comprobarDniDonante(involucrado)) {
            return 1;
        } else if (("Ayuda Efectiva".equals(tipo) || "Ayuda Bancaria".equals(tipo) || "Ayuda Material".equals(tipo))
            && !ONG.gestorBeneficiarios.comprobarExistenciaBeneficiario(involucrado)) {
            return 2;
        } else if (!ONG.gestorVoluntarios.comprobarExistenciaVoluntario(voluntario)) {
            return 3;
        } else if ("Gasto".equals(tipo) && !"ONG".equals(involucrado))
            return 4;
        
        // Guardamos en el gestor los datosMovimiento
        datosMovimiento = new Movimiento();
        datosMovimiento.modificarTipoMovimiento(tipo);
        datosMovimiento.modificarCuantia(cuantia);
        datosMovimiento.modificarInvolucrado(involucrado);
        datosMovimiento.modificarDescripcion(descripcion);
        
        // Guardamos en el gestor el voluntario que lo registra
        dniV = voluntario;

        return 0;
    }
    
    /**
     * Procede a registrar el movimiento
     */
    @Override
    public void finRegistrarMovimiento(){
        confirmarRegistro();
    }
    
    /**
     * Procede a confirmar el registro del movimiento
     */
    public void confirmarRegistro(){
        Date fecha = new Date();
        Movimiento dm = datosMovimiento;
        Movimiento m = new Movimiento(dm.obtenerTipoMovimiento(),dm.obtenerCuantia(),dm.obtenerInvolucrado(),dm.obtenerDescripcion(),fecha,dniV);
        diaketas.diaketas.ong.agregarMovimiento(m);
    }
    
    /**
     * Consulta los datos del movimiento seleccionado
     * @return Un objeto con los datos asociados al movimiento seleccionado
     */
    @Override
    public Movimiento consultarDatosMovimiento(){
        return movimientoSeleccionado.obtenerDatosMovimiento();
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
        movimientoSeleccionado.modificar(datosMovimiento.obtenerTipoMovimiento(), datosMovimiento.obtenerCuantia(), datosMovimiento.obtenerDescripcion(), datosMovimiento.obtenerInvolucrado());
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
    
    
     /**
     * Funcion que obtiene la lista de movimientos que satisfacen el filtro indicado por los
     * parámetros
     * @return Devuelve la lista de movimientos que cumplen los criterios.
     */
    
    @Override
    public ArrayList<Movimiento> filtrarMovimientos(int operadorCantidad, String cantidad1, String cantidad2, int operadorFecha, String fecha1, String fecha2, String voluntario, String involucrado, int confirmado, int tipo, String tagDescripcion) {
        return diaketas.diaketas.ong.obtenerMovimientos(operadorCantidad, cantidad1, cantidad2, operadorFecha, fecha1, fecha2, voluntario, involucrado, confirmado, tipo, tagDescripcion);
    }
    
       /**
     * Funcion que obtiene la lista de movimientos que satisfacen el filtro indicado por los
     * parámetros
     * @return Devuelve la lista de movimientos que cumplen los criterios.
     */
    
    //@Override
    public ArrayList<Movimiento> filtrarMovimientosDonante(int operadorCantidad, String cantidad1, String cantidad2, int operadorFecha, String fecha1, String fecha2, int tipo) {
        return diaketas.diaketas.ong.obtenerMovimientosDonante(operadorCantidad, cantidad1, cantidad2, operadorFecha, fecha1, fecha2, tipo);
    }  

    /**
     * Funcion que indica al gestor el movimiento que ha sido seleccionado por el voluntario.
     */
    
    @Override
    public void seleccionarMovimiento(int codMovimiento) {
        movimientoSeleccionado = diaketas.diaketas.ong.buscarMovimiento(codMovimiento);
    }
    
    
    
    @Override
    public void finEliminarMovimiento(){
        confirmarEliminacion(movimientoSeleccionado.obtenerCodMovimiento());
    }
    
    @Override
    public void confirmarEliminacion(int cod_Movimiento){
        movimientoSeleccionado.eliminar(cod_Movimiento);
    }

    @Override
    public ArrayList<Movimiento> filtrarMovimientosM(int operadorCantidad, String cantidad1, String cantidad2, int operadorFecha, String fecha1, String fecha2, String involucrado, int tipo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
