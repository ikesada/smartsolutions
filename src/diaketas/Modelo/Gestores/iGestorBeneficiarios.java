/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Beneficiario;
import java.util.ArrayList;

/**
 *
 * @author kesada
 */
public interface iGestorBeneficiarios {
    
    public void confirmarAltaBeneficiario();
    
    public void confirmarBajaBeneficiario();
    
    public void confirmarEliminacion();
    
    public void confirmarInsercion();
    
    public void confirmarModificacionBeneficiario();
    
    public Beneficiario consultarBeneficiario(String dniBeneficiario);
    
    /*
     * Devuelve un ArrayList, donde el primer objeto es de tipo familiar y
     * el segundo objeto es de tipo Parentesco
     */
    public ArrayList consultarFamiliar(String Nombre_Apellidos);

    
}
