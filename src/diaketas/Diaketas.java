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
     
        
        UI ui = new UI();
        ui.setVisible(true);
        
    }
}
