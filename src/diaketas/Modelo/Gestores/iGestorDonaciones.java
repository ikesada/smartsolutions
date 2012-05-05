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
     * Introduce los datos de un movimiento en el sistema
     * @param tipo Tipo de movimiento. 0=D.Efectiva, 1=D.Bancaria, 2=D.Material
     * 3=A.Efectiva, 4=A.Bancaria, 5=A.Material, 6=Gasto
     * @param cuantia Cantidad de dinero en el movimiento
     * @param invol Dni del involucrado
     * @param desc Descripción del movimiento
     * @param dniV Dni del voluntario que registra el movimiento
     * @return Error. 1=Invol.donante erroneo, 2=Invol.beficiarion erroneo
     */
    public int introducirMovimiento(int tipo, double cuantia, String invol, String desc, String dniV);
    
    /**
     * Procede a registrar el movimiento
     */
    public void finRegistrarMovimiento();
    
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
     
     
    /**
     * Comprueba que el DNI del Voluntario es valido
     * @param dniVoluntario DNI del voluntario responsable
     * @return Un booleano indicando si el dni del voluntario es valido o no
     */    
    public boolean comprobarVoluntario(String dniVoluntario);
    
    /**
     * Procede a eliminar el movimiento del sistema
     */
    public void finEliminarMovimiento();
}
