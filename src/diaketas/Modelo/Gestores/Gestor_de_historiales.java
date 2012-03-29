/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Accion;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Gestor_de_historiales implements iGestorHistorial{
    
      Statement instruccion;
      ResultSet tabla;
      ConexionBD con = new ConexionBD();
    
      @Override
    public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Operacion){
  
        /*Nueva acción con Dni de voluntario y beneficiario asociado, junto con fecha actual*/
        Accion ac = new Accion(DNI_Voluntario, DNI, Operacion, new Date());
        
        /*Añadimos la acción en el sistema*/
        diaketas.diaketas.ong.agregarAccion(ac);
    }
}
    