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
     * @brief Funcion que introduce en el sistema los datos para manejarlos mas tarde
     * @param NIF_CIF Dni del Donante
     * @param Nombre Nombre del Donante
     * @param Apellidos Apellidos del Donante
     * @param FechaNac Fecha de Nacimiento del Donante
     * @param Localidad Localidad del Donante
     * @param Email Email del Donante 
     * @param Telefono Telefono del Donante 
     * @param Tipo_Donante Tipo de Donante
     * @param Fecha_Inscripcion Fecha de Inscripcion del Donante
     * @param Observaciones Observaciones sobre el Donante
     * @param Periodicidad_Donaciones Periodicidad en la que se haran las donaciones
     * @param Cuantia_Donaciones Cuantia de las donaciones del Donante
     * @param Tipo_Periodicidad Tipo de periodicidad de las donaciones
     * @param NIF_Vol Dni del voluntario que realiza la operacion
     * @return Devuelve True o False si el voluntario que realiza la operacion existe
     */
    public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol);

   /**
     * 
     * @brief Funcion que introduce en el sistema el dni del donante y lo comprueba
     * @param NIF_CIF Dni del Donante a comprobar
     * @return Devuelve True o False dependiende de si el donante existe(y no esta dado de baja) o no
     */
    public Boolean introducirDniDonante(String NIF_CIF);
        
    /**
     * @brief Funcion que prueba que la consulta es valida
     * @return Devuelve los datos del Donante
     */
    public Donante confimarConsulta();
    
    /**
     * @brief Funcion que finaliza la operacion de Alta Donante
     */
    public void confirmarFinAlta();
    
    /**
     * @brief Funcion que finaliza la operacion de Modificacion Donante
     */
    public void confirmarFinBaja();

    /**
     * @brief Funcion que finaliza la operacion de Baja
     */
    public void confirmarFinModificacion();
}
