/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.ONG;

/**
 *
 * @author kesada
 */
public class Gestores {
    /**
     * 
     */
    public static ONG ong;
    /**
     * 
     */
    public static Gestor_de_beneficiarios gestorBeneficiarios;
    /**
     * 
     */
    public static Gestor_de_historiales gestorHistoriales;
    /**
     * 
     */
    public static Gestor_de_voluntarios gestorVoluntarios;
    /**
     * 
     */
    public static Gestor_de_donantes gestorDonantes;
    
    /**
     * 
     */
    public Gestores() {
        /*Creamos los gestores*/
        ong = new ONG();
        gestorBeneficiarios = new Gestor_de_beneficiarios();
        gestorHistoriales = new Gestor_de_historiales();
        gestorVoluntarios = new Gestor_de_voluntarios();
        gestorDonantes = new Gestor_de_donantes();
    } 
}
