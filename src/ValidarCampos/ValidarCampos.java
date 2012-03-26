/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidarCampos;

/**
 *
 * @author Alex
 */
public class ValidarCampos {
     
    static public boolean isInteger(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    static public boolean isDouble(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
}
