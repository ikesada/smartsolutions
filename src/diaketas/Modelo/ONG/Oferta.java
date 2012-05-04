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
 * @author Cesar
 */
public class Oferta {
    
    
    /**
     * Codigo asociado a la oferta
     */
    public int cod_oferta;
    
    /**
     * Tipo de oferta
     */
    public String concepto;
    
    /**
     * Fecha en que se emitio la oferta
     */
    public Date fecha;

    /**
     * Indica si la oferta sigue en pie o no
     */
    public int activo;
    
    /**
     * Lugar en el que se ofrece el puesto de trabajo
     */
    public String poblacion;
    
    /**
     * Total de vacantes disponibles para una oferta
     */
    public int numero_vacantes;
    
    /**
     * Breve resumen de los aspectos principales de la oferta
     */
    public String descripcion;
    
    /**
     * Estudios o experiencia laboral mínimos requeridos
     */
    public String requisitos_minimos;
    
    /**
     * Especifica la clase de contrato ofrecido
     */
    public String tipo_contrato;
    
    /**
     * Número de horas diarias de trabajo del puesto ofrecido
     */
    public int jornada_laboral;
    
    /**
     * Cantidad a cobrar (mensual) por el puesto ofrecido
     */
    public Double salario;
    
    /**
     * Notas adicionales sobre la oferta
     */
    public String observaciones;
    
    
    
    /**
     * DNI del donante que ha facilitado la oferta
     */
    public String NIF_CIF_Donante;
    
    
    
    ConexionBD con = new ConexionBD();
    
    
    
    
    /*------------------OPERACIONES------------------*/
    
    public Oferta obtenerDatosOferta()
    {
        return this;
    }
    
    
    //devuelve true si todo ha ido bien (sin errores)
    public Boolean modificar(Oferta nuevosDatosOferta)
    {
        
        int cod_oferta_antiguo = cod_oferta;    //me servira para buscar la tupla correspondiente
        
        cod_oferta = nuevosDatosOferta.cod_oferta;
        concepto = nuevosDatosOferta.concepto;
        fecha = nuevosDatosOferta.fecha;
        activo = nuevosDatosOferta.activo;
        poblacion = nuevosDatosOferta.poblacion;
        numero_vacantes = nuevosDatosOferta.numero_vacantes;
        descripcion = nuevosDatosOferta.descripcion;
        requisitos_minimos = nuevosDatosOferta.requisitos_minimos;
        tipo_contrato = nuevosDatosOferta.tipo_contrato;
        jornada_laboral = nuevosDatosOferta.jornada_laboral;
        salario = nuevosDatosOferta.salario;
        observaciones = nuevosDatosOferta.observaciones;
        NIF_CIF_Donante = nuevosDatosOferta.NIF_CIF_Donante;
        
        
        //modifico los datos asociados en la BD:
        Boolean exito=true;
        
        con.conectarBD();

        //transformo los tipo Date pasados
        java.sql.Timestamp fechaOferta = new java.sql.Timestamp( fecha.getTime() );
        
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            //Actualizo la tabla de Oferta
            instruccion.executeUpdate("Update Oferta SET "
                    + "Cod_Oferta = \"" + cod_oferta + "\", "
                    + "Concepto = \"" + concepto + "\", "                    
                    + "Fecha = \"" + fechaOferta + "\", "                    
                    + "Activo = \"" + activo + "\", "                    
                    + "Poblacion = \"" + poblacion + "\", "
                    + "Numero_Vacantes = \"" + numero_vacantes + "\", "
                    + "Descripcion = \"" + descripcion + "\""
                    + "Requisitos_Minimos = \"" + requisitos_minimos + "\""
                    + "Tipo_Contrato = \"" + tipo_contrato + "\""
                    + "Jornada_Laboral = \"" + jornada_laboral + "\""
                    + "Salario = \"" + salario + "\""
                    + "Observaciones = \"" + observaciones + "\""
                    + "NIF_CIF_Donante = \"" + NIF_CIF_Donante + "\""
                    + " WHERE Cod_Oferta = \""+cod_oferta_antiguo+"\"");
         
            
         }
         //Captura de errores
         catch(SQLException e)
         { 
             exito=false;
             System.out.println(e); 
         }
         catch(Exception e)
         { 
             exito=false;
             System.out.println(e);
         }
         //Desconexión de la BD
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return exito;
        
        
    }
    
    
    
    
}
