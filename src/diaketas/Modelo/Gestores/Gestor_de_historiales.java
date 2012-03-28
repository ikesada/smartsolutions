/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Accion;
import diaketas.Modelo.ONG.ONG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kesada
 */
public class Gestor_de_historiales {
    
      Statement instruccion;
      ResultSet tabla;
      ConexionBD con = new ConexionBD();
    
    public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
  
        /*Nueva acción con Dni de voluntario y beneficiario asociado, junto con fecha actual*/
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        
        /*Añadimos la acción en el sistema*/
        diaketas.diaketas.ong.agregarAccion(ac);
    }
}
    