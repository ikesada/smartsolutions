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
 * @author Miguel Á. Quesada
 * @author Jesús Carabaño Bravo
 * @author Fran Dios Buitrago
 * @author Alejandro Portillo Guerrero
 */
public class Movimiento {
    
    /**
     * Codigo identificativo del movimiento
     */
    private int Cod_Movimiento;
    
    /** 
     * Tipo de movimiento
     */
    private String Tipo_Movimiento;
    
    /**
     * Cuantia del movimiento
     */
    private double cuantia;
    
    /**
     * NIF del Involucrado 
     */
    private String involucrado;
    
    /**
     * NIF del voluntario que crea
     */
    private String voluntario_crea;
    
    /**
     * NIF del voluntario que confirma el movimiento
     */
    private String voluntario_confirma;
    
    /**
     * Descripción del movimiento
     */
    private String descripcion;
    
    /**
     * Fecha de creación del movimiento
     */
    private Date Fecha;
    
    /**
     * Indica si el movimiento está confirmado
     */
    private boolean confirmado;
    
    // ------------------------------- CONSULTORES --------------------------------- //

    public int obtenerCodMovimiento() {
        return Cod_Movimiento;
    }

    public Date obtenerFecha() {
        return Fecha;
    }

    public String obtenerTipoMovimiento() {
        return Tipo_Movimiento;
    }

    public boolean estaConfirmado() {
        return confirmado;
    }

    public double obtenerCuantia() {
        return cuantia;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public String obtenerInvolucrado() {
        return involucrado;
    }

    public String obtenerVoluntarioConfirma() {
        return voluntario_confirma;
    }

    public String obtenerVoluntarioCrea() {
        return voluntario_crea;
    }
    
    // ------------------------------- MODIFICADORES ------------------------------- //
    

    public void modificarCodMovimiento(int Cod_Movimiento) {
        this.Cod_Movimiento = Cod_Movimiento;
    }

    public void modificarFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void modificarTipoMovimiento(String Tipo_Movimiento) {
        this.Tipo_Movimiento = Tipo_Movimiento;
    }

    public void modificarConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public void modificarCuantia(double cuantia) {
        this.cuantia = cuantia;
    }

    public void modificarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void modificarInvolucrado(String involucrado) {
        this.involucrado = involucrado;
    }

    public void modificarVoluntarioConfirma(String voluntario_confirma) {
        this.voluntario_confirma = voluntario_confirma;
    }

    public void modificarVoluntarioCrea(String voluntario_crea) {
        this.voluntario_crea = voluntario_crea;
    }
    
    
    
    
    /**
     * Constructor por defecto
     */
    public Movimiento(){
    }
    
    
    /* METODOS */
    
    public Movimiento(int Cod_Movimiento, String Tipo_Movimiento, double cuantia, String descripcion, Date Fecha, boolean confirmado,
                    String involucrado, String voluntario_crea, String voluntario_confirma) {
        this.Cod_Movimiento =Cod_Movimiento;
        this.Tipo_Movimiento = Tipo_Movimiento;
        this.cuantia = cuantia;
        this.descripcion = descripcion;
        this.Fecha = Fecha;
        this.confirmado = confirmado;
        this.involucrado = involucrado;
        this.voluntario_crea = voluntario_crea;
        this.voluntario_confirma = voluntario_confirma;
    }    
    
    /**
     * Constructor de Movimiento 
     */
    public Movimiento(String tipo,double cuantia, String involucrado, String descripcion, Date fecha, String voluntario_crea){
        this.Tipo_Movimiento = tipo;
        this.cuantia = cuantia;
        this.involucrado = involucrado;
        this.descripcion = descripcion;
        this.Fecha = fecha;
        this.voluntario_crea = voluntario_crea;
    }

    /**
     * Obtiene los datos de un movimiento
     * @return Devuelve el objeto movimiento
     */
    public Movimiento obtenerDatosMovimiento() {
        return this;
    }
    
    /**
     * Confirma un movimiento para que no pueda volver a ser modificado
     * @param dniV Dni del voluntario que confirma el movimiento
     */    
    public void confirmar(String dniV){
        /*Confirmamos el movimiento*/
        confirmado = true;
        involucrado = dniV;
        
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Confirmamos el movimiento*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Confirmado = 1  , NIF_CIF_Confirma = \"" +
                    dniV + "\" WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Modifica los datos del movimiento
     * @param tipo_movimiento Tipo de movimiento
     * @param cuantia Cuantia del nuevo movimiento
     * @param descripcion Descripción del movimiento
     * @param involucrado DNI de la persona involucrada
     */
    public void modificar(String tipo_movimiento, double cuantia, String descripcion, String involucrado) {
        this.cuantia = cuantia;
        this.descripcion = descripcion;
        this.Tipo_Movimiento = tipo_movimiento;
        this.involucrado = involucrado;
        
               
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Tipo = \"" + tipo_movimiento +"\" , Cuantia = \""
                    + cuantia + "\", Descripcion = \""+descripcion+ "\", NIF_CIF_Implica = \"" + involucrado + "\" WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    } 
       
    public void eliminar(int cod_Movimiento){
        this.Cod_Movimiento = cod_Movimiento;
       
         ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("DELETE FROM Movimiento "
                    + "WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
     

