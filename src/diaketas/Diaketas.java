/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import java.sql.*;
import diaketas.UI.UI;
import javax.swing.UIManager;

public class Diaketas {
    public static void main(String args[]){
        /*Look & Feel*/
        try{
           UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
        } catch (Exception e) {
        }
        /*Conexión BBDD*/
       ConexionBD con = new ConexionBD();
       con.conectarBD();
       
       /*EJEMPLO CONEXION
        Statement instruccion;
        ResultSet tabla;
        try {
            //Crear objeto Statement para realizar queries a la base de datos
            instruccion = con.conexion().createStatement();
            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            tabla = instruccion.executeQuery("SELECT cod , nombre FROM datos");
            System.out.println("Codigo\tNombre");
            while(tabla.next())
                System.out.println(tabla.getInt(1)+"\t"+tabla.getString(2));
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        */
       
      
        UI ui = new UI();
        ui.setVisible(true);
        
    }
}
