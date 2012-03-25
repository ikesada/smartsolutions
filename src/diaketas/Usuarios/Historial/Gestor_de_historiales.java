/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Historial;
import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
import java.util.Date;

/**
 *
 * @author kesada
 */
public class Gestor_de_historiales {
    
    
    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
  
        /*Nueva acción con Dni de voluntario y beneficiario asociado, junto con fecha actual*/
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        
        /*Se guarda la accion en el sistema*/
        ONG.agregarAccion(ac);
    }
}
