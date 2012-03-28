/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

/**
 *
 * @author kesada
 */
public class Parentesco {
    public int Cod_Familiar;
    public String Parentesc;
    public String DNI_Beneficiario;
    
    /*---------------------------Constructores---------------------------------*/
    public Parentesco (int Cod_Familiar, String DNI_Beneficiario, String Parentesc){
        this.Cod_Familiar = Cod_Familiar;
        this.DNI_Beneficiario = DNI_Beneficiario;
        this.Parentesc = Parentesc;
    }
    
}
