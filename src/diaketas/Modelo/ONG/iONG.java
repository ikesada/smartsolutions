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
    
    /*
     * Devuelve el beneficiario asociado a ese DNI
     */
    public Beneficiario buscarBeneficiario(String DNI);
    
    /*
     * Devuelve el donante asociado a ese DNI
     */
    public Donante buscarDonante(String DNI);
    
    /*
     * Devuelve el voluntario asociado a ese DNI
     */
    public Voluntario buscarVoluntario(String DNI);
    
    /*
     * Añade una nueva acción al sistema
     */
    public void agregarAccion(Accion ac);

    /*
     * Agrega un nuevo beneficiario al sistema
     */
    public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario);
    
    /*
     * Agrega un nuevo Donante al sistema
     */
    public void agregarNuevoDonante(Donante nuevoDonante);
    
    /*
     * Agrega un nuevo voluntario al sistema
     */
    public void agregarNuevoVoluntario(Voluntario nuevoVoluntario);
    
    /*
     * Busca un familiar en el sistema con el nombre indicado y la fecha de nacimiento
     */
    public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac);
}
