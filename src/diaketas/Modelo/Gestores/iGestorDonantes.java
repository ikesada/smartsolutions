/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Donante;
import java.util.Date;

/**
 *
 * @author Alex
 */
public interface iGestorDonantes {
    
    /**
     * 
     * @param NIF_CIF
     * @param Nombre
     * @param Apellidos
     * @param FechaNac
     * @param Localidad
     * @param Email
     * @param Telefono
     * @param Tipo_Donante
     * @param Fecha_Inscripcion
     * @param Observaciones
     * @param Periodicidad_Donaciones
     * @param Cuantia_Donaciones
     * @param Tipo_Periodicidad
     * @param NIF_Vol
     * @return
     */
    public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol);

    /**
     * 
     * @param NIF_CIF
     * @return
     */
    public Boolean introducirDniDonante(String NIF_CIF);
        
    /**
     * 
     * @return
     */
    public Donante confimarConsulta();
    
    /**
     * 
     */
    public void confirmarFinAlta();
    
    /**
     * 
     */
    public void confirmarFinBaja();

    /**
     * 
     */
    public void confirmarFinModificacion();
}
