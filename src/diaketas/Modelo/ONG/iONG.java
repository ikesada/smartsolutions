/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.ArrayList;

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
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Donantes del sistema
     * @return Devuelve un listado con todos los Donantes del sistema
     */
    public ArrayList<Donante> buscarDonantes();
    
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Beneficiarios del sistema
     * @return Devuelve un listado con todos los Beneficiarios del sistema
     */
    public ArrayList<Beneficiario> buscarBeneficiarios();
    
    /**
     * Funcion que devuelve los datos asociados a una Oferta que tiene como código
     * el entero pasado como argumento del metodo.
     * @param codOferta codigo identificativo de la oferta buscada
     * @return Devuelve la oferta concreta
     */
    
    public Oferta buscarOferta(int codOferta);
    
    /**
     * 
     * @param codigo codigo identificativo de la oferta
     * @param concepto  representa la categoria de la oferta
     * @param poblacion representa a la ciudad donde se desarrollara la oferta
     * @return Devuelve la lista de ofertas que casan con los criterios de busqueda
     * establecidos por los parametros de entrada al metodo.
     */
    
    public ArrayList<Oferta> obtenerOfertas(int codigo,String concepto,String poblacion);
    
    
    /**
     * Funcion que devuelve los datos asociados a un Movimiento cuyo código es igual
     * al parámetro recibido.
     * @param codMovimiento codigo identificativo del movimiento buscado
     * @return Devuelve un movimiento concreto
    */   
    public Movimiento buscarMovimiento(int codMovimiento);
    
    
    /**
     * @return Devuelve la lista de ofertas que casan con los criterios de busqueda
    */
    public ArrayList<Movimiento> obtenerMovimientos(int operadorCantidad,String cantidad1, String cantidad2, int operadorFecha, String fecha1, String fecha2, String voluntario, String involucrado, int confirmado, int tipo, String tagDescripcion);

    
    
    
}
