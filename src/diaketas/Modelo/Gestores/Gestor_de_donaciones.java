/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Movimiento;
import diaketas.Modelo.ONG.ONG;
import java.util.Date;

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
        }
        
        // Guardamos en el gestor los datosMovimiento
        datosMovimiento.Tipo_Movimiento = tipo;
        datosMovimiento.cuantia = cuantia;
        datosMovimiento.involucrado = involucrado;
        datosMovimiento.descripcion = descripcion;
        
        // Guardamos en el gestor el voluntario que lo registra
        dniV = voluntario;

        return 0;
    }
    
    /**
     * Procede a registrar el movimiento
     */
    @Override
    public void finRegistrarMovimiento(){
        System.out.print("Hola soy finRegistrarMovimiento");
        confirmarRegistro();
    }
    
    /**
     * Procede a confirmar el registro del movimiento
     */
    public void confirmarRegistro(){
        Date fecha = new Date();
        Movimiento dm = datosMovimiento;
        Movimiento m = new Movimiento(dm.Tipo_Movimiento,dm.cuantia,dm.involucrado,dm.descripcion,fecha);
        
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
        movimientoSeleccionado.modificar(datosMovimiento.Tipo_Movimiento, datosMovimiento.cuantia, datosMovimiento.descripcion, datosMovimiento.involucrado);
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
