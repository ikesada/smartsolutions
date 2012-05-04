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
    
    Movimiento movimientoSeleccionado;
    String dniV;
    
    
    @Override
    public Movimiento consultarDatosMovimiento(){
        return movimientoSeleccionado;
    }
    
    @Override
    public boolean confirmarMovimiento(String dniv){
        
        if(ONG.gestorVoluntarios.comprobarExistenciaVoluntario(dniV)){
            movimientoSeleccionado.confirmar(dniV);
            return true;
        }    
        return false;
    }
    
    
}
