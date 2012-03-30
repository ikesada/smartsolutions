/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

/**
 *
 * @author Alex
 */
public interface iGestorHistorial {

    /**
     *
     *  Funcion que registra las acciones realizadas por los voluntarios
     *
     * @param DNI_Voluntario DNI del voluntario que realizo la operacion
     * @param DNI DNI del usuario sobre el cual ser realizo la operacion
     * @param Operacion Definicion de la operacion realizada
     */
    public void RegistrarOperacion(String DNI_Voluntario, String DNI, String operacion);
    /*
     * public void consultarHistorialVoluntario(String DNI_Voluntario);
     *
     * public void consultarHistorialUsuario(String DNI_Usuario);
     *
     * public void generarListaUsuario(String TipoUsuario);
     */
}
