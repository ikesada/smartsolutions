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
    
    public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol);

    public Boolean introducirDniDonante(String NIF_CIF);
        
    public Donante confimarConsulta();
    
    public void confirmarFinAlta();
    
    public void confirmarFinBaja();

    public void confirmarFinModificacion();
}
