/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kesada
 */
public class Beneficiario extends Usuarios{
    /**
     * Nacionalidad del beneficiario
     */
    public String Nacionalidad;
    /**
     * Estado civil del beneficiario
     */
    public String Estado_civil;
    /**
     * Domicilio del beneficiario
     */
    public String Domicilio;
    /**
     * Codigo postal del beneficiario
     */
    public int Codigo_Postal;
    /**
     * Observaciones acerca de los datos personales del beneficiario
     */
    public String Observaciones_Datos_Personales;
    /**
     * Fecha de inscripción del beneficiario
     */
    public Date Fecha_Inscripcion;
    /**
     * Motivo del beneficiario
     */
    public String Motivo;
    /**
     * Tipo de vivienda del beneficiario
     */
    public String Tipo_Vivienda;
    /**
     * Precio de la vivienda del beneficiario
     */
    public Double Precio_Vivienda;
    /**
     * Situacion economica del beneficiario
     */
    public String Situacion_Economica;
    /**
     * Expediente del beneficiario
     */
    public int Expediente;
    /**
     * Observaciones acerca de los familiares del beneficiario
     */
    public String Observaciones_Familiares;
    /**
     * Observaciones acerca de la vivienda del beneficiario
     */
    public String Observaciones_Vivienda;
    /**
     * Nivel de estudios del beneficiario
     */
    public String Nivel_Estudios;
    /**
     * Profesión del beneficiario
     */
    public String Profesion;
    /**
     * Experiencia laboral del beneficiario
     */
    public String Experiencia_Laboral;
    /**
     * Ciudad de nacimiento del beneficiario
     */
    public String Ciudad_Nacimiento;
    /**
     * Lista de familiares asociados al beneficiario
     */
    public ArrayList<Familiar> familiares;
   
    /*-----------------------------Constructores-------------------------------*/
    /**
     * Crea un nuevo beneficiario con los datos especificados
     * @param NIF_CIF DNI del beneficiario
     * @param Nombre Nombre del beneficiario
     * @param Apellidos Apellidos del beneficiario
     * @param FechaNac Fecha de nacimiento del beneficiario
     * @param Localidad Localidad del beneficiario
     * @param Activo Indica si el beneficiario esta activo (1) o no (0)
     * @param FechaDesac Fecha de desactivación del beneficiario
     * @param Email Email del beneficiario
     * @param Telefono Telefono del beneficiario
     * @param Nacionalidad Nacionalidad del beneficiario
     * @param Estado_civil Estado civil del beneficiario
     * @param Domicilio Domicilio del beneficiario
     * @param Codigo_Postal Codigo postal del beneficiario
     * @param Fecha_Inscripcion Fecha de inscripción del beneficiario
     * @param Expediente Expediente del beneficiario
     * @param Motivo Motivo por el que se da de alta al beneficiario
     * @param Precio_Vivienda Precio de la vivienda del beneficiario
     * @param Tipo_Vivienda Tipo de vivienda del beneficiario
     * @param Observaciones_Datos_Personales Observaciones acerca de los datos personales
     * @param Observaciones_Familiares Observaciones acerca de los familiares
     * @param Observaciones_Vivienda Observaciones acerca de la vivienda
     * @param Ciudad_Nacimiento Ciudad de nacimiento del beneficiario
     * @param Situacion_Economica Situación economica del beneficiario
     * @param Nivel_Estudios Nivel de estudios del beneficiario
     * @param Profesion Profesion del beneficiario
     * @param Experiencia_Laboral Experiencia laboral del beneficiario
     */
    public Beneficiario (String NIF_CIF, String Nombre,
            String Apellidos, Date FechaNac, String Localidad, int Activo,
            Date FechaDesac, String Email, int Telefono, String Nacionalidad,
            String Estado_civil, String Domicilio, int Codigo_Postal,
            Date Fecha_Inscripcion, int Expediente, String Motivo,
            Double Precio_Vivienda, String Tipo_Vivienda, String Observaciones_Datos_Personales,
            String Observaciones_Familiares, String Observaciones_Vivienda,
            String Ciudad_Nacimiento, String Situacion_Economica, String Nivel_Estudios,
            String Profesion, String Experiencia_Laboral){
        
        this.Nacionalidad = Nacionalidad;
        this.Estado_civil = Estado_civil;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Fecha_Inscripcion = Fecha_Inscripcion;
        this.Expediente = Expediente;
        this.Motivo = Motivo;
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Precio_Vivienda = Precio_Vivienda;
        this.Tipo_Vivienda = Tipo_Vivienda;
        this.Observaciones_Datos_Personales = Observaciones_Datos_Personales;
        this.Observaciones_Familiares = Observaciones_Familiares;
        this.Observaciones_Vivienda = Observaciones_Vivienda;
        this.Ciudad_Nacimiento = Ciudad_Nacimiento;
        this.Situacion_Economica = Situacion_Economica;
        this.Nivel_Estudios = Nivel_Estudios;
        this.Profesion = Profesion;
        this.Experiencia_Laboral = Experiencia_Laboral;
        
        this.familiares = new ArrayList<Familiar>();
    }
 
    /**
     * Crea un nuevo beneficiario a partir de otro objeto Beneficiario
     * @param datosBeneficiario Beneficiario con los que se inicializará el nuevo beneficiario
     */
    public Beneficiario (Beneficiario datosBeneficiario){    
        this.Nacionalidad = datosBeneficiario.Nacionalidad;
        this.Estado_civil = datosBeneficiario.Estado_civil;
        this.Domicilio = datosBeneficiario.Domicilio;
        this.Codigo_Postal = datosBeneficiario.Codigo_Postal;
        this.Fecha_Inscripcion = datosBeneficiario.Fecha_Inscripcion;
        this.Expediente = datosBeneficiario.Expediente;
        this.Motivo = datosBeneficiario.Motivo;
        this.NIF_CIF = datosBeneficiario.NIF_CIF;
        this.Nombre = datosBeneficiario.Nombre;
        this.Apellidos = datosBeneficiario.Apellidos;
        this.FechaNac = datosBeneficiario.FechaNac;
        this.Localidad = datosBeneficiario.Localidad;
        this.Activo = datosBeneficiario.Activo;
        this.FechaDesac = datosBeneficiario.FechaDesac;
        this.Email = datosBeneficiario.Email;
        this.Telefono = datosBeneficiario.Telefono;
        this.Precio_Vivienda = datosBeneficiario.Precio_Vivienda;
        this.Tipo_Vivienda = datosBeneficiario.Tipo_Vivienda;
        this.Observaciones_Datos_Personales = datosBeneficiario.Observaciones_Datos_Personales;
        this.Observaciones_Familiares = datosBeneficiario.Observaciones_Familiares;
        this.Observaciones_Vivienda = datosBeneficiario.Observaciones_Vivienda;
        this.Ciudad_Nacimiento = datosBeneficiario.Ciudad_Nacimiento;
        this.Situacion_Economica = datosBeneficiario.Situacion_Economica;
        this.Nivel_Estudios = datosBeneficiario.Nivel_Estudios;
        this.Profesion = datosBeneficiario.Profesion;
        this.Experiencia_Laboral = datosBeneficiario.Experiencia_Laboral;
        
        this.familiares = (ArrayList<Familiar>) datosBeneficiario.familiares.clone();
     }
    
    /*---------------------------Beneficiario----------------------------------*/
    /**
     * Desactiva un usuario del sistema
     * @param fecha_desactivacion Fecha en la que se produce la desactivación
     */
    public void desactivarUsuario(Date fecha_desactivacion){
        ConexionBD con = new ConexionBD();
    
        /*Modificamos los datos del objeto*/
        Activo = 0;
        FechaDesac = fecha_desactivacion;
        
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Desac = new java.sql.Timestamp(FechaDesac.getTime());

        con.conectarBD();
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /* Desactivamos el usuario y actualizamos fecha de Baja*/
            instruccion.executeUpdate("UPDATE Usuario SET Activo = " + Activo + ", Fecha_Desactivacion = \""
                    +fecha_Desac+"\" WHERE NIF_CIF = \"" + NIF_CIF + "\"");
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
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }         
    }
    
    /**
     * Cambia los datos del beneficiario
     * @param datosBeneficiario Datos del beneficiario con los que se va a actualizar el beneficiario
     */
    public void cambiarDatosBeneficiario (Beneficiario datosBeneficiario){
    
        ConexionBD con = new ConexionBD();
        
        /* Actualizamos los datos */
        con.conectarBD();

        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(datosBeneficiario.FechaNac.getTime());
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            instruccion.executeUpdate("Update Usuario SET "
                    + "NIF_CIF = \"" + datosBeneficiario.NIF_CIF + "\", "
                    + "Nombre = \"" + datosBeneficiario.Nombre + "\", "                    
                    + "Apellidos = \"" + datosBeneficiario.Apellidos + "\", "                    
                    + "Fecha_Nacimiento_Fundacion = \"" + fecha_Nacimiento + "\", "                    
                    + "Localidad = \"" + datosBeneficiario.Localidad + "\", "
                    + "Email = \"" + datosBeneficiario.Email + "\", "
                    + "Telefono = \"" + datosBeneficiario.Telefono + "\""
                    + " WHERE NIF_CIF = \""+ONG.gestorBeneficiarios.NIF_Beneficiario+"\"");
         
            instruccion.executeUpdate("Update Beneficiario SET "
                    + "NIF_CIF = \"" + datosBeneficiario.NIF_CIF + "\", "
                    + "Nacionalidad = \"" + datosBeneficiario.Nacionalidad + "\", "                    
                    + "Estado_Civil = \"" + datosBeneficiario.Estado_civil + "\", "                    
                    + "Domicilio = \"" + datosBeneficiario.Domicilio + "\", "                    
                    + "Codigo_Postal = " + datosBeneficiario.Codigo_Postal + ", "
                    + "Expediente = " + datosBeneficiario.Expediente + ", "
                    + "Motivo = \"" + datosBeneficiario.Motivo + "\", "                  
                    + "Precio_Vivienda = " + datosBeneficiario.Precio_Vivienda + ", "
                    + "Tipo_Vivienda = \"" + datosBeneficiario.Tipo_Vivienda + "\", "
                    + "Observaciones_Datos_Personales = \"" + datosBeneficiario.Observaciones_Datos_Personales + "\", "
                    + "Observaciones_Familiares = \"" + datosBeneficiario.Observaciones_Familiares + "\", "
                    + "Observaciones_Vivienda = \"" + datosBeneficiario.Observaciones_Vivienda + "\", "
                    + "Ciudad_Nacimiento = \"" + datosBeneficiario.Ciudad_Nacimiento + "\", "
                    + "Situacion_Economica = \"" + datosBeneficiario.Situacion_Economica + "\", "
                    + "Nivel_Estudios = \"" + datosBeneficiario.Nivel_Estudios + "\", "
                    + "Profesion = \"" + datosBeneficiario.Profesion + "\", "
                    + "Experiencia_Laboral = \"" + datosBeneficiario.Experiencia_Laboral + "\" "
                    + " WHERE NIF_CIF = \""+ONG.gestorBeneficiarios.NIF_Beneficiario+"\"");
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
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /*----------------------------Familiares-----------------------------------*/
    /**
     * Agrega un nuevo familiar a la lista de familiares del beneficiario
     * @param familiar Familiar que se va a añadir al beneficiario
     */
    public void agregarFamiliar(Familiar familiar){
        ConexionBD con = new ConexionBD();

        con.conectarBD();
        /*Agregamos el nuevo familiar al sistema*/
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(familiar.Fecha_Nacimiento.getTime());

        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            /*Introducimos al nuevo Familiar en el sistema*/
            instruccion.executeUpdate("INSERT INTO Familiar VALUES (\""
                    + this.NIF_CIF + "\",\"" + familiar.Nombre_Apellidos + "\",\"" + fecha_Nacimiento + "\",\"" 
                    + familiar.Ocupacion + "\",\"" + familiar.parentesco + "\")");
                        
            /*Agregamos el familiar a la lista de familiares*/
            familiares.add(familiar);
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
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }         
    }
    
    /**
     * Obtiene los familiares de un beneficiario
     * @return Devuelve un listado con los familiares
     */
    public ArrayList<Familiar> consultarFamiliares(){
        return this.familiares;
    }
    
    /**
     * Busca un familiar entre los familiares de un beneficiario
     * @param Nombre_Apellidos Nombre y apellidos del familiar que se quiere buscar
     * @return Un objeto familiar conteniendo los datos del familiar
     */
    public Familiar buscarFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento){
        /*Busca el familiar cuyo nombre coincide con Nombre_Apellidos*/
        for (int i = 0; i < familiares.size(); i++)
            if(familiares.get(i).Nombre_Apellidos.compareTo(Nombre_Apellidos) == 0 &&
                    familiares.get(i).Fecha_Nacimiento.compareTo(Fecha_Nacimiento) == 0)
                return familiares.get(i);
        return null;
    }
    
    /**
     * Consulta las ofertas que tiene asociado el beneficiario
     * @return Devuelve la lista de las ofertas relacionadas con el beneficiario (null si no tiene ninguna)
     */
    
    public ArrayList<Oferta> obtenerOfertas() {
        ArrayList<Oferta> ofertas = new ArrayList();
        
        ConexionBD con = new ConexionBD();
        
         String query = "select o.Cod_Oferta, Concepto, Fecha, Activo, Poblacion,"
                 +"Numero_Vacantes, Descripcion, Requisitos_Minimos, Tipo_Contrato,"
                 +"Jornada_Laboral, Salario, Observaciones, NIF_CIF_Donante from"
                 +" Oferta o, Recibe r where o.Cod_Oferta = r.Cod_Oferta AND r.NIF_CIF = \""
                 +this.NIF_CIF+"\"";
                 
        con.conectarBD();
        
        try{
                
            //Crear objeto Statement para realizar queries a la base de datos
            Statement s = (Statement) con.conexion().createStatement();
            
            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet rs = s.executeQuery(query);

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            
            while (rs.next())   //avanzo a la siguiente tupla obtenida en la consulta (mientras haya)
            { 
               
                   Oferta o = new Oferta(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5), 
                         rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),new Double(rs.getDouble(11))
                         ,rs.getString(12),rs.getString(13));
                
                    ofertas.add(o);
                

            }

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
             
        
        /*Desconexión de la BD*/
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        
        
        return ofertas;
    }
    
    public void AgregarOferta(Oferta oferta) {
        ConexionBD con = new ConexionBD();
        
        String insert = "insert into Recibe values( \""+oferta.cod_oferta+"\", "+NIF_CIF+"\")";
                         
        con.conectarBD();
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            instruccion.executeUpdate(insert);
                        
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
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }         
        
    }
        
}
