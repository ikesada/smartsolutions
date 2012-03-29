/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

import diaketas.Modelo.ONG.ONG;
import diaketas.UI.UI;

/**
 *
 * @author kesada
 */
public class diaketas {
    public static ONG ong;
    

    public static void main(String args[]){
        
        /*Look & Feel*/
/*        try{
           UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
        } catch (Exception e) {
        }
*/      ong = new ONG();
    


        /*Iniciamos la interfaz*/
        UI ui = new UI();
        ui.setVisible(true);
    }
}
