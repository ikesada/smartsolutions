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
    /**
     * 
     * @param DNI
     * @return
     */
    public Beneficiario buscarBeneficiario(String DNI);
    
    /*
     * Devuelve el donante asociado a ese DNI
     */
    /**
     * 
     * @param DNI
     * @return
     */
    public Donante buscarDonante(String DNI);
    
    /*
     * Devuelve el voluntario asociado a ese DNI
     */
    /**
     * 
     * @param DNI
     * @return
     */
    public Voluntario buscarVoluntario(String DNI);
    
    /*
     * Añade una nueva acción al sistema
     */
    /**
     * 
     * @param ac
     */
    public void agregarAccion(Accion ac);

    /*
     * Agrega un nuevo beneficiario al sistema
     */
    /**
     * 
     * @param nuevoBeneficiario
     */
    public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario);
    
    /*
     * Agrega un nuevo Donante al sistema
     */
    /**
     * 
     * @param nuevoDonante
     */
    public void agregarNuevoDonante(Donante nuevoDonante);
    
    /*
     * Agrega un nuevo voluntario al sistema
     */
    /**
     * 
     * @param nuevoVoluntario
     */
    public void agregarNuevoVoluntario(Voluntario nuevoVoluntario);
    
    /*
     * Busca un familiar en el sistema con el nombre indicado y la fecha de nacimiento
     */
    /**
     * 
     * @param Nombre_Apellidos
     * @param Fecha_Nac
     * @return
     */
    public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac);
}
