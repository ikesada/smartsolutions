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
     * @param DNI_Voluntario
     * @param DNI
     * @param operacion
     */
    public void RegistrarOperacion(String DNI_Voluntario, String DNI, String operacion);
    
    /*public void consultarHistorialVoluntario(String DNI_Voluntario);
    
    public void consultarHistorialUsuario(String DNI_Usuario);
    
    public void generarListaUsuario(String TipoUsuario);*/
    
}
