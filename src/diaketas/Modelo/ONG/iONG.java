/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.Date;

/**
 *
 * @author kesada
 */
public interface iONG {
    
    public Beneficiario buscarBeneficiario(String DNI);
    
    public Donante buscarDonante(String DNI);
    
    public Voluntario buscarVoluntario(String DNI);
    
    public void agregarAccion(Accion ac);

    public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario);
    
    public void agregarNuevoDonante(Donante nuevoDonante);
    
    public void agregarNuevoVoluntario(Voluntario nuevoVoluntario);
    
    public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac);
}
