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
    /**
     * 
     */
    public static ONG ong;
    

    /**
     * Funcion principal de sistema
     * @param args
     */
    public static void main(String args[]){
        
       ong = new ONG();
    


        /*Iniciamos la interfaz*/
        UI ui = new UI();
        ui.setVisible(true);
    }
}
