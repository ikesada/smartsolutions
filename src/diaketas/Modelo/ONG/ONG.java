/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.Gestores.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ONG implements iONG{
      static Statement instruccion;
      static ResultSet tabla;
      static ConexionBD con = new ConexionBD();
      
      
      String Nombre;
      String Direccion;
      int telefon;
      int Codigo_Postal;
      
      
    /**
    * 
    */
    public static Gestor_de_beneficiarios gestorBeneficiarios;
    /**
     * 
     */
    public static Gestor_de_historiales gestorHistoriales;
    /**
     * 
     */
    public static Gestor_de_voluntarios gestorVoluntarios;
    /**
     * 
     */
    public static Gestor_de_donantes gestorDonantes;
    
    public static Gestor_de_ofertas gestorOfertas;

    /**
     * 
     */
    public ONG() {
    
        /*Creamos los gestores*/
        gestorBeneficiarios = new Gestor_de_beneficiarios();
        gestorHistoriales = new Gestor_de_historiales();
        gestorVoluntarios = new Gestor_de_voluntarios();
        gestorDonantes = new Gestor_de_donantes();
        gestorOfertas = new Gestor_de_ofertas();
    
    }

    
      

    /****************************ACCION***************************************
     * @param ac 
     */
    @Override
    public void agregarAccion(Accion ac){
        /*Se guarda la accion en el sistema*/
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha = new java.sql.Timestamp(ac.Fecha.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            instruccion.executeUpdate("INSERT INTO Accion(Nombre, Fecha,"
                                        + "NIF_CIF_Voluntario, NIF_CIF_Usuario) VALUES (\""
                                        + ac.tipo + "\",\"" + fecha + "\",\"" + ac.DNI_Voluntario+"\",\""+ac.DNI_Usuario+"\")");
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
    /**************************BENEFICIARIO************************************
     * @param DNI Dni del Beneficario que buscaremos en la BBDD
     * @return Devuelve un objteo con los datos del beneficiario
     */    
    @Override
    public Beneficiario buscarBeneficiario(String DNI){
        Beneficiario beneficiario = null;
        con.conectarBD();

         try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Usuario u ,Beneficiario b WHERE u.NIF_CIF = b.NIF_CIF and u.NIF_CIF = \""+ DNI+"\"");

            /*Si se ha encontrado una tubla*/
            if (rs.next()){

                beneficiario = new Beneficiario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6),
                                                rs.getDate(7), rs.getString(8), rs.getInt(9),rs.getString(11), rs.getString(12), rs.getString(13),
                                                rs.getInt(14), rs.getDate(15), rs.getInt(16), rs.getString(17), rs.getDouble(18), rs.getString(19),
                                                rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25),
                                                rs.getString(26), rs.getString(27));
            }
            
            if (beneficiario != null){
                /*Obtenemos los familiares y parentescos*/
                beneficiario.familiares = new ArrayList<Familiar>();
                instruccion = (Statement) con.conexion().createStatement();
                rs = instruccion.executeQuery("Select * From Familiar f WHERE"
                        + " DNI_Beneficiario = \""+ beneficiario.NIF_CIF+"\"");

                while (rs.next()){
                    /*Creamos un familiar con los datos*/
                    Familiar familiar = new Familiar (rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5));
                    /*Agregamos a la lista*/
                    beneficiario.familiares.add(familiar);

                }
            }
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
        
        return beneficiario;
    }
       
    /**
     * Anyade un Beneficiario a la BBDD
     * @param nuevoBeneficiario Beneficiario que se va a anyadir
     */
    @Override
    public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoBeneficiario.FechaNac.getTime());
        java.sql.Timestamp fecha_Activacion = new java.sql.Timestamp(nuevoBeneficiario.Fecha_Inscripcion.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos la parte de Usuario*/
            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nombre + "\",\"" + nuevoBeneficiario.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoBeneficiario.Localidad + "\",\""   + nuevoBeneficiario.Activo + "\", NULL, \"" + nuevoBeneficiario.Email + "\",\"" + nuevoBeneficiario.Telefono + "\")");
            
            /*Introducimos la parte de Beneficiario*/
             instruccion.executeUpdate("INSERT INTO Beneficiario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nacionalidad + "\",\"" + nuevoBeneficiario.Estado_civil + "\",\""  + nuevoBeneficiario.Domicilio
                    + "\",\"" + nuevoBeneficiario.Codigo_Postal + "\",\""  + fecha_Activacion + "\",\"" +  nuevoBeneficiario.Expediente
                    + "\",\"" + nuevoBeneficiario.Motivo + "\",\"" + nuevoBeneficiario.Precio_Vivienda + "\",\"" + nuevoBeneficiario.Tipo_Vivienda
                    + "\",\"" + nuevoBeneficiario.Observaciones_Datos_Personales + "\",\"" + nuevoBeneficiario.Observaciones_Familiares
                    + "\",\"" + nuevoBeneficiario.Observaciones_Vivienda + "\",\"" + nuevoBeneficiario.Ciudad_Nacimiento
                    + "\",\"" + nuevoBeneficiario.Situacion_Economica + "\",\"" + nuevoBeneficiario.Nivel_Estudios
                    + "\",\"" + nuevoBeneficiario.Profesion + "\",\"" + nuevoBeneficiario.Experiencia_Laboral +"\")");           
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
    
    /*****************************FAMILIAR***********************************
     *  Funcion que realiza la busqueda de un familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar buscado
     * @param Fecha_Nac Fecha de nacimiento del familiar buscado     
     * @return Devuelve el familiar encontrado
     
    @Override
    public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac){
        Familiar familiar = null;
        con.conectarBD();
        /*Convertimos Date para trabajar
        java.sql.Timestamp fecha = new java.sql.Timestamp(Fecha_Nac.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select f.Nombre_Apellidos, f.Fecha_Nacimiento, f.Ocupacion, f.Cod_Familiar"
                    + " From Familiar f WHERE Nombre_Apellidos = \""
                    + Nombre_Apellidos+"\" and Fecha_Nacimiento=\""+fecha+"\"");
         
            if (rs.next()){
                familiar = new Familiar (rs.getString(1),rs.getDate(2),rs.getString(3));
                //Indicamos su Codigo Interno
                familiar.Cod_Familiar = rs.getInt(4);
            }
         }
         /*Captura de errores
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         
         return familiar;
    } */
       
    
    /*****************************DONANTE***********************************
     *  Agrega un nuevo donante a la BBDD
     * @param nuevoDonante Donante que va a ser agregado
     */
    
    @Override
    public void agregarNuevoDonante(Donante nuevoDonante){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoDonante.FechaNac.getTime());
        java.sql.Timestamp fecha_Activacion = new java.sql.Timestamp(nuevoDonante.Fecha_Inscripcion.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            /*Introducimos la parte de Usuario*/

            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoDonante.NIF_CIF + "\",\""
                    + nuevoDonante.Nombre + "\",\"" + nuevoDonante.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoDonante.Localidad + "\",\""   + nuevoDonante.Activo + "\", NULL, \"" + nuevoDonante.Email + "\",\"" + nuevoDonante.Telefono + "\")");
            
            /*Introducimos la parte de Donante*/
             instruccion.executeUpdate("INSERT INTO Donante VALUES (\""+nuevoDonante.NIF_CIF + "\",\""
                    + nuevoDonante.Tipo_Donante + "\",\"" + fecha_Activacion + "\",\""  + nuevoDonante.Observaciones
                    + "\",\"" + nuevoDonante.Periodicidad_Donaciones + "\",\""   + nuevoDonante.Cuantia_Donaciones
                    + "\",\"" + nuevoDonante.Tipo_Periodicidad+"\")");           
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
     * 
     *  Busca el donante que se le ha pasado en la BBDD
     * @param DNI Dni del donante a buscar
     * @return Devuelve los datos del donante buscado en caso de existir
     */
    @Override
    public Donante buscarDonante(String DNI){
            
            Donante donante = null;
            con.conectarBD();
        
        try {
            instruccion = (Statement) con.conexion().createStatement();

            tabla = instruccion.executeQuery("Select * From Usuario u ,Donante d WHERE u.NIF_CIF = d.NIF_CIF and u.NIF_CIF = \""+ DNI+"\" LIMIT 1");
            
            if(tabla.next()){
                donante = new Donante((String)tabla.getObject("NIF_CIF"), (String)tabla.getObject("Nombre"), (String)tabla.getObject("Apellidos"), (Date)tabla.getObject("Fecha_Nacimiento_Fundacion"),
                        (String)tabla.getObject("Localidad"), (Integer)tabla.getInt("Activo"), (Date)tabla.getDate("Fecha_Desactivacion"), (String)tabla.getString("Email"), (Integer)tabla.getInt("Telefono"), 
                        (String)tabla.getString("Tipo_Donante"), tabla.getDate("Fecha_Inscripcion"), (String)tabla.getString("Observaciones"), (Integer)tabla.getInt("Periodicidad_Donaciones"),
                        (Double)tabla.getDouble("Cuantia_Donaciones"), (String)tabla.getString("Tipo_Periodicidad"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return donante;
        }
            
    
    
    /******************************VOLUNTARIO*************************
     * 
     * Busca el voluntario que se le ha pasado en la BBDD
     * @param DNI Dni del voluntario a buscar
     * @return Devuelve los datos del voluntarios buscado en caso de existir
     */  
    @Override
    public Voluntario buscarVoluntario(String DNI){
        Voluntario v = null;
        
        con.conectarBD();
        
        try {
            
        
            
            instruccion = (Statement) con.conexion().createStatement();
            
            
            tabla = (ResultSet) instruccion.executeQuery("SELECT * FROM Usuario u, Voluntario v"
                    + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+(String)DNI+"\"");
            
       
    
 
            if(tabla.next())
            {
                //desde el 1-9 son datos del usuario, y el 10 vuelve a ser el NIF_DNI pero de la tabla voluntario
                
                //creo un nuevo voluntario v con esos datos
                v = new Voluntario(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getDate(4), tabla.getString(5), tabla.getInt(6), tabla.getDate(7), tabla.getString(8), tabla.getInt(9),tabla.getString(11), tabla.getString(12), tabla.getInt(13), tabla.getDate(14), tabla.getString(15));
                                
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
        return v;
    }

    /**
     *  Anyade el nuevo voluntario a la BBDD
     * @param nuevoVoluntario Datos del voluntario a agregar
     */
    @Override
    public boolean agregarNuevoVoluntario(Voluntario nuevoVoluntario){
        
        boolean exito = true;
        
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoVoluntario.FechaNac.getTime());
        java.sql.Timestamp fecha_Inicio = new java.sql.Timestamp(nuevoVoluntario.Fecha_Inicio.getTime());
        
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos la parte de Usuario*/
            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoVoluntario.NIF_CIF + "\",\""
                    + nuevoVoluntario.Nombre + "\",\"" + nuevoVoluntario.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoVoluntario.Localidad + "\",\""   + nuevoVoluntario.Activo + "\", NULL, \"" + nuevoVoluntario.Email + "\",\"" + nuevoVoluntario.Telefono + "\")");
            
            /*Introducimos la parte de Voluntario*/
             instruccion.executeUpdate("INSERT INTO Voluntario VALUES (\""+nuevoVoluntario.NIF_CIF + "\",\""
                    + nuevoVoluntario.Nacionalidad + "\",\"" + nuevoVoluntario.Domicilio + "\",\""  + nuevoVoluntario.Codigo_Postal + "\",\""   + fecha_Inicio + "\",\"" + nuevoVoluntario.Observaciones +"\")");           
         }
         /*Captura de errores*/
         catch(SQLException e){ 
             System.out.println(e); 
             exito = false;
         }
         catch(Exception e){ 
             System.out.println(e);
             exito = false;
         }
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
         
        return exito; 
    }    
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Voluntarios del sistema
     * @return Devuelve un listado con todos los Voluntarios del sistema
     */
    @Override
    public ArrayList<Voluntario> buscarVoluntarios(){
      
        ArrayList<Voluntario> usuarios=new ArrayList<Voluntario>();
        
        
        con.conectarBD();
        
        try{
                
            //Crear objeto Statement para realizar queries a la base de datos
            Statement s = (Statement) con.conexion().createStatement();

            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet rs = s.executeQuery("select u.NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Fecha_Desactivacion, Email, Telefono, Nacionalidad, Domicilio, Codigo_Postal, Fecha_Inicio, Observaciones from Usuario u, Voluntario v where u.NIF_CIF=v.NIF_CIF");

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();

            
            while (rs.next())   //avanzo a la siguiente tupla obtenida en la consulta (mientras haya)
            { 
                
                //Para cada tupla creo un objeto Voluntario que luego introducire en el ArrayList
                
                //me creo una variable int auxiliar, ya que el campo "activo" es booleano en la tabla y en la clase es int
                int act;
                if( (Boolean)rs.getObject(6) == true )
                    act = 1;
                else
                    act = 0;
                
                
                Voluntario v = new Voluntario( (String) rs.getObject(1), (String)rs.getObject(2), (String)rs.getObject(3), (Date)rs.getObject(4), (String)rs.getObject(5), act, (Date)rs.getObject(7), (String)rs.getObject(8), (Integer)rs.getObject(9), (String)rs.getObject(10), (String)rs.getObject(11), (Integer)rs.getObject(12), (Date)rs.getObject(13), (String)rs.getObject(14) );

                usuarios.add(v);

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
        
        return usuarios;
    }
    
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Donantes del sistema
     * @return Devuelve un listado con todos los Donantes del sistema
     */
    @Override
    public ArrayList<Donante> buscarDonantes(){
      
        ArrayList<Donante> usuarios=new ArrayList<Donante>();
        
        
        con.conectarBD();
        
        try{
                
            //Crear objeto Statement para realizar queries a la base de datos
            Statement s = (Statement) con.conexion().createStatement();

            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet rs = s.executeQuery("select u.NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Fecha_Desactivacion, Email, Telefono, Tipo_Donante, Fecha_Inscripcion, Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad from Usuario u, Donante d where u.NIF_CIF=d.NIF_CIF");

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();

            
            while (rs.next())   //avanzo a la siguiente tupla obtenida en la consulta (mientras haya)
            { 
                
                //Para cada tupla creo un objeto Donante que luego introducire en el ArrayList
                
                //me creo una variable int auxiliar, ya que el campo "activo" es booleano en la tabla y en la clase es int
                int act;
                if( (Boolean)rs.getObject(6) == true )
                    act = 1;
                else
                    act = 0;
                
                                                                                                                                                                                                                               //Tipo_Donante,            Fecha_Inscripcion,       Observaciones,           Periodicidad_Donaciones,         Cuantia_Donaciones,          Tipo_Periodicidad
                Donante d = new Donante( (String) rs.getObject(1), (String)rs.getObject(2), (String)rs.getObject(3), (Date)rs.getObject(4), (String)rs.getObject(5), act, (Date)rs.getObject(7), (String)rs.getObject(8), (Integer)rs.getObject(9), (String)rs.getObject(10), (Date)rs.getObject(11), (String)rs.getObject(12), (Integer)rs.getObject(13),        (Double)rs.getObject(14), (String)rs.getObject(15) );

                usuarios.add(d);

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
        
        return usuarios;
    }
    
    
    
    
    
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Beneficiarios del sistema
     * @return Devuelve un listado con todos los Beneficiarios del sistema
     */
    @Override
    public ArrayList<Beneficiario> buscarBeneficiarios(){
      
        ArrayList<Beneficiario> usuarios=new ArrayList<Beneficiario>();
        
        
        con.conectarBD();
        
        try{
                
            //Crear objeto Statement para realizar queries a la base de datos
            Statement s = (Statement) con.conexion().createStatement();

            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet rs = s.executeQuery("select u.NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Fecha_Desactivacion, Email, Telefono, Nacionalidad, Estado_Civil, Domicilio, Codigo_Postal, Fecha_Inscripcion, Expediente, Motivo, Precio_Vivienda, Tipo_Vivienda, Observaciones_Datos_Personales, Observaciones_Familiares, Observaciones_Vivienda, Ciudad_Nacimiento, Situacion_Economica, Nivel_Estudios, Profesion, Experiencia_Laboral from Usuario u, Beneficiario b where u.NIF_CIF=b.NIF_CIF");

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();

            
            while (rs.next())   //avanzo a la siguiente tupla obtenida en la consulta (mientras haya)
            { 
                
                //Para cada tupla creo un objeto Beneficiario que luego introducire en el ArrayList
                
                //me creo una variable int auxiliar, ya que el campo "activo" es booleano en la tabla y en la clase es int
                int act;
                if( (Boolean)rs.getObject(6) == true )
                    act = 1;
                else
                    act = 0;


                Beneficiario b = new Beneficiario( (String) rs.getObject(1), (String)rs.getObject(2), (String)rs.getObject(3), (Date)rs.getObject(4), (String)rs.getObject(5), act, (Date)rs.getObject(7), (String)rs.getObject(8), (Integer)rs.getObject(9), (String)rs.getObject(10), (String)rs.getObject(11), (String)rs.getObject(12), (Integer)rs.getObject(13), (Date)rs.getObject(14), (Integer)rs.getObject(15), (String)rs.getObject(16), (Double)rs.getObject(17), (String)rs.getObject(18), (String)rs.getObject(19), (String)rs.getObject(20), (String)rs.getObject(21), (String)rs.getObject(22), (String)rs.getObject(23), (String)rs.getObject(24), (String)rs.getObject(25), (String)rs.getObject(26) );

                usuarios.add(b);

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
        
        return usuarios;
    }

    /**
     * Funcion que devuelve la información asociada a una oferta a partir del codigo identificativo de la misma
     * @return Devuelve un objeto de tipo Oferta cuyo codigo coincide con el pasado como argumento.
     */
    @Override
    public Oferta buscarOferta(int codOferta) {
        Oferta oferta = null;
        con.conectarBD();

         try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Oferta o WHERE o.Cod_Oferta = \""+ codOferta+"\"");

            /*Si se ha encontrado una tupla*/
            if (rs.next()){

                oferta = new Oferta(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5), 
                         rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),new Double(rs.getDouble(11))
                         ,rs.getString(12),rs.getString(13));
            }
            
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
        
        return oferta;
    }
    
    /**
     * Funcion que obtiene la lista de ofertas que satisfacen unos criterios de búsqueda basados
     * en el código, concepto y población de las mismas.
     * @return Devuelve la lista de ofertas que cumple los criterios.
     */

    @Override
    public ArrayList<Oferta> obtenerOfertas(int codigo, String concepto, String poblacion) {
        ArrayList<Oferta> ofertas=new ArrayList<Oferta>();
        String query = "select * from Oferta o";
        
        
        con.conectarBD();
        
        try{
                
            //Crear objeto Statement para realizar queries a la base de datos
            Statement s = (Statement) con.conexion().createStatement();
            
            if(codigo != -1)
                query += "where o.Cod_Oferta = \""+codigo+"\"";
            else {
                if(concepto != null) {
                    query += "where o.Concepto REGEXP \'"+concepto+"\'";
                    
                    if(poblacion != null)
                        query += " and o.Poblacion = \""+poblacion+"\"";
                }
                else {
                    if(poblacion != null)
                        query += " where o.Poblacion = \""+poblacion+"\"";
               }
            }
                

            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet rs = s.executeQuery(query);

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();

            
            while (rs.next())   //avanzo a la siguiente tupla obtenida en la consulta (mientras haya)
            { 
               
                if( (Boolean)rs.getObject(4) == true ) {

                    Oferta o = new Oferta(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5), 
                         rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),new Double(rs.getDouble(11))
                         ,rs.getString(12),rs.getString(13));
                
                    ofertas.add(o);
                }

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
    
      
}
