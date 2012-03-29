/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidarCampos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alex
 */
public class ValidarCampos {
     
    /**
     * Funcion que comprueba si un string es un numero
     * @param numero Cadena a comprobar
     * @return Devuelve True o False si es un numero real o no
     */
    static public boolean isInteger(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     * Funcion que comprueba si un string es un real
     * @param numero Cadena a comprobar
     * @return Devuelve True o False si es un numero real o no
     */
    static public boolean isDouble(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     * Funcion que comprueba si un string es un email valido
     * @param email Email a comprobar
     * @return Devuelve True o False si es un email valido o no
     */
    static public boolean isEmail(String email){
        
        Pattern pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        Matcher mat = pat.matcher(email);
        if (mat.find()) {
            return true;
        }else{
            return false;
        }
    }
}
