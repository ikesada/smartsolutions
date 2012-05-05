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
    
    public Oferta(int cod_oferta, String concepto, Date fecha, int activo, String poblacion, 
            int numero_vacantes, String descripcion, String requisitos_minimos, String tipo_contrato
            ,int jornada_laboral,Double salario,String observaciones, String NIF_CIF_Donante) {
        this.cod_oferta =cod_oferta;
        this.concepto = concepto;
        this.fecha = fecha;
        this.activo = activo;
        this.poblacion = poblacion;
        this.numero_vacantes = numero_vacantes;
        this.descripcion = descripcion;
        this.requisitos_minimos = requisitos_minimos;
        this.tipo_contrato = tipo_contrato;
        this.jornada_laboral = jornada_laboral;
        this.salario = salario;
        this.observaciones = observaciones;
        this.NIF_CIF_Donante = NIF_CIF_Donante;
    }
    
    public Oferta obtenerDatosOferta()
    {
        return this;
    }
    
    
    //devuelve true si todo ha ido bien (sin errores)
    public Boolean modificar(Integer Cod_Oferta, String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones)
    {
        
        
        
        /*
    public int cod_oferta;
    public String concepto;
    public Date fecha;
    public int activo;
    public String poblacion;
    public int numero_vacantes;
    public String descripcion;
    public String requisitos_minimos;
    public String tipo_contrato;
    public int jornada_laboral;
    public Double salario;
    public String observaciones;
    public String NIF_CIF_Donante;
        */
        
        
        int cod_oferta_antiguo = cod_oferta;    //me servira para buscar la tupla correspondiente
        
        cod_oferta = Cod_Oferta;
        concepto = Concepto;
        fecha = FechaOf;
        activo = Activada;
        poblacion = Poblacion;
        numero_vacantes = Num_vacantes;
        descripcion = Descripcion;
        requisitos_minimos = Req_minimos;
        tipo_contrato = Tipo_contrato;
        jornada_laboral = Jornada_laboral;
        salario = Salario;
        observaciones = Observaciones;
        NIF_CIF_Donante = DNI_donante;
        
        
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
                    + "Descripcion = \"" + descripcion + "\", "
                    + "Requisitos_Minimos = \"" + requisitos_minimos + "\", "
                    + "Tipo_Contrato = \"" + tipo_contrato + "\", "
                    + "Jornada_Laboral = \"" + jornada_laboral + "\", "
                    + "Salario = \"" + salario + "\", "
                    + "Observaciones = \"" + observaciones + "\", "
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
         //DesconexiÃ³n de la BD
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
