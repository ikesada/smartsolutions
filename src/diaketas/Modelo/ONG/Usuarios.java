/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public abstract class Usuarios {
    
    /**
     * DNI del usuario en cuestion
     */
    public String NIF_CIF;
    /**
     * Nombre del usuario
     */
    public String Nombre;
    /**
     * Apellidos del Usuario
     */
    public String Apellidos;
    /**
     * Fecha de nacimiento del Usuario
     */
    public Date FechaNac;
    /**
     * Localidad del Usuario
     */
    public String Localidad;
    /**
     * Muestra si el usuario esta activo en el sistema o se dio de baja
     */
    public int Activo;
    /**
     * Fecha de desactivacion del Usuario
     */
    public Date FechaDesac;
    /**
     * Email del Usuario
     */
    public String Email;
    /**
     * Telefono del Usuario
     */
    public int Telefono;
    
}
