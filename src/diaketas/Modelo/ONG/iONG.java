/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kesada
 */
public interface iONG {
    
    /**
     * Devuelve el beneficiario asociado a ese DNI
     * @param DNI DNI que queremos comprobar
     * @return Devuelve los datos del beneficiario
     */
    public Beneficiario buscarBeneficiario(String DNI);
    
    /**
     * Devuelve el donante asociado a ese DNI
     * @param DNI DNI del donante que queremos comprobar
     * @return Devuelve los datos del donante
     */
    public Donante buscarDonante(String DNI);

    /**
     * Devuelve el voluntario asociado a ese DNI
     * @param DNI Dni del voluntario buscado
     * @return Devuelve los datos del Voluntario
     */
    public Voluntario buscarVoluntario(String DNI);

    /**
     * Añade una nueva acción al sistema
     * @param ac Accion que queremos registrar
     */
    public void agregarAccion(Accion ac);
    
    /**
     * Agrega un nuevo beneficiario al sistema
     * @param nuevoBeneficiario Beneficiario a agregar
     */
    public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario);
    
    /**
     * Agrega un nuevo Donante al sistema
     * @param nuevoDonante Donante a agregar
     */
    public void agregarNuevoDonante(Donante nuevoDonante);

    /**
     * Agrega un nuevo voluntario al sistema
     * @param nuevoVoluntario Voluntario a agregar
     * @return Devuelve true si la operacion se ha realizado con exito
     */
    public boolean agregarNuevoVoluntario(Voluntario nuevoVoluntario);

    /**
     * Busca un familiar en el sistema con el nombre indicado y la fecha de nacimiento
     * @param Nombre_Apellidos Nombre y apellidos del familiar a buscar
     * @param Fecha_Nac Fecha de nacimiento del familiar a buscar
     * @return Devuelve el familiar encontrado
     */
    //public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac);
    
    
    /**
     * Funcion que devuelve un listado con todos los Voluntarios del sistema
     * @return Devuelve un listado con todos los Voluntarios del sistema
     */
    public ArrayList<Voluntario> buscarVoluntarios();
    
}
