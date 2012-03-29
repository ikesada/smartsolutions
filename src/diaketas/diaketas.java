/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import diaketas.Modelo.Gestores.Gestor_de_beneficiarios;
import diaketas.Modelo.Gestores.Gestor_de_donantes;
import diaketas.Modelo.Gestores.Gestor_de_historiales;
import diaketas.Modelo.Gestores.Gestor_de_voluntarios;
import diaketas.Modelo.ONG.ONG;
import diaketas.UI.UI;
import javax.swing.UIManager;

/**
 *
 * @author kesada
 */
public class diaketas {
    public static ONG ong;
    public static Gestor_de_beneficiarios gestorBeneficiarios;
    public static Gestor_de_historiales gestorHistoriales;
    public static Gestor_de_voluntarios gestorVoluntarios;
    public static Gestor_de_donantes gestorDonantes;
    

    public static void main(String args[]){
        
        /*Look & Feel*/        try{
           UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
        } catch (Exception e) {
        }
     
        /*Creamos los gestores*/
        ong = new ONG();
        gestorBeneficiarios = new Gestor_de_beneficiarios();
        gestorHistoriales = new Gestor_de_historiales();
        gestorVoluntarios = new Gestor_de_voluntarios();
        gestorDonantes = new Gestor_de_donantes();


        /*Iniciamos la interfaz*/
        UI ui = new UI();
        ui.setVisible(true);
    }
}
