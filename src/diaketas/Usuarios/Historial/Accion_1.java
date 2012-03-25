/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Historial;

import java.util.Date;

/**
 *
 * @author kesada
 */
public class Accion_1 {
    public String DNI_Voluntario;
    public String DNI_Usuario;
    public Date Fecha;
    public String tipo;

    public Accion_1(String DNI_Voluntario, String DNI_Usuario, String tipo, Date Fecha) {
        this.DNI_Voluntario = DNI_Voluntario;
        this.DNI_Usuario = DNI_Usuario;
        this.Fecha = Fecha;
        this.tipo = tipo;
    }
}
