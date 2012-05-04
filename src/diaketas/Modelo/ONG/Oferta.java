/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import java.util.Date;

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
    
    
    
    
    public Oferta obtenerDatosOferta()
    {
        return this;
    }
    
    
    
    
    
}
