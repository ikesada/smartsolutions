/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */


public class Gestor_de_voluntarios {
   
    
    
    //////////////////////////////////////////////////////////////////////////////////////
                            /*IMPORTADO DE LA CLASE ONG*/
    
    
    static Statement instruccion;
    static ResultSet rs;
    static ConexionBD con = new ConexionBD();
    
    
    
    
    static public Voluntario buscarVoluntario(String DNI){
        Voluntario v = null;
        System.out.println("Hola\n");
        try {
            
            
            instruccion = (Statement) con.conexion().createStatement();
            //System.out.println("DNI del voluntario buscado: "+DNI);
            
            rs = (ResultSet) instruccion.executeQuery("SELECT * FROM Usuario u, Voluntario v"
                    + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+DNI+"\"");
            
            //rs = (ResultSet) instruccion.executeQuery("SELECT * FROM Usuario u, Voluntario v"
            //        + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+(String)DNI+"\"");
    
            System.out.println("DNI del voluntario buscado: "+DNI);
 
            if(rs.next())
            {
                //desde el 1-9 son datos del usuario, y el 10 vuelve a ser el NIF_DNI pero de la tabla voluntario
                System.out.println("Los datos obtenidos son: \n"+rs.getString(1)+" "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getDate(4)+" "+ rs.getString(5)+" "+ rs.getInt(6)+" "+ rs.getDate(7)+" "+ rs.getString(8)+" "+ rs.getInt(9)+" "+rs.getString(11)+" "+ rs.getString(12)+" "+ rs.getInt(13)+" "+ rs.getDate(14)+" "+ rs.getString(15));
            
                //creo un nuevo voluntario v con esos datos
                v = new Voluntario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getString(8), rs.getInt(9),rs.getString(11), rs.getString(12), rs.getInt(13), rs.getDate(14), rs.getString(15));
                                
            }
            
            
               
            
                
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }        
        
        return v;
    }
    
    
    static public boolean comprobarExistenciaVoluntario(String DNI){
        Voluntario vol = buscarVoluntario(DNI);
        
        System.out.println("nombre del voluntario encontrado: "+vol.Nombre);
        return (vol != null);
    }
    
    
    
    
    
    public static void agregarAccion(Accion ac){
        
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
    
    
    
    
    
    
    
    
    //////////////////////////////////////////////////////////////////
    
    
    

    
    
    
    static public boolean altaVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs, String voluntarioDNI )
    {
    
       
        boolean confirma=false;
        
        System.out.println("Voy a ver si existe el voluntario con dni:"+voluntarioDNI);
        
        boolean existe = comprobarExistenciaVoluntario( (String)voluntarioDNI );
        System.out.println("Exite:"+existe);
        if(existe)
        {
            boolean existe1 = comprobarExistenciaVoluntario(DNI);
            
            if(!existe1)
            {
                
                //el constructor Date() nos devuelve la fecha actual    
                confirma = crearVoluntario( DNI, nombre, apellidos, fechaNacim, ciudad, email, telf, nacionalidad, direccion, codPost, obs);                

                registrarOperacion(DNI, voluntarioDNI, "alta voluntario", new Date());  //le paso la fecha actual
            }
            if(existe1)
            {
                confirma=false; //si ya hay un voluntario con ese mismo DNI
            }
        }
        
        return confirma;
               
    }
    
    
    
    
    public static boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs)
    {

        
        boolean confirma;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        
        
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, new Date(0,0,0), email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        confirma = Gestor_de_voluntarios.añadirVoluntario(v);
       
        return confirma;
    
    
    }   
    
            
            
    static public void registrarOperacion(String DNI, String voluntarioDNI, String tipoOperacion, Date fecha)
    {
    
        
        
    }
    
    
    
    public static boolean añadirVoluntario(Voluntario v)
    {
        
        boolean exito=true;
        
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(v.FechaNac.getTime());
        java.sql.Timestamp fecha_Inicio = new java.sql.Timestamp(v.Fecha_Inicio.getTime());
        java.sql.Timestamp fecha_Desactivacion = new java.sql.Timestamp(v.FechaDesac.getTime());
        
        
        
        
        //realizo dos insert, uno en la tabla Usuario y otro en la tabla Voluntario 
        
        
        //primero nos conectamos a la BD
        con.conectarBD();
        
        
        
        //INSERT EN USUARIO:
        try {
        instruccion = (Statement) con.conexion().createStatement();

        //inserto tupla en la tabla Usuario
        instruccion.executeUpdate("INSERT INTO Usuario( NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Fecha_Desactivacion, Email, Telefono  ) VALUES (\""
                                    + v.NIF_CIF + "\",\"" + v.Nombre + "\",\"" + v.Apellidos+"\",\"" +fecha_Nacimiento+ "\",\"" +v.Localidad+ "\",\"" +v.Activo+ "\",\"" +fecha_Desactivacion+ "\",\"" +v.Email+ "\",\"" +v.Telefono+"\")");
        }
        /*Captura de errores*/
        catch(SQLException e){ 
            System.out.println(e); 
            exito=false;
        }
        catch(Exception e){ 
            System.out.println(e);
            exito=false;
        }
        
        
        
        
        
        try {
        instruccion = (Statement) con.conexion().createStatement();

        //inserto tupla en la tabla Voluntario
        instruccion.executeUpdate("INSERT INTO Voluntario( NIF_CIF, Nacionalidad, Domicilio, Codigo_Postal, Fecha_Inicio, Observaciones  ) VALUES (\""
                                    + v.NIF_CIF + "\",\"" + v.Nacionalidad + "\",\"" + v.Domicilio+"\",\""+v.Codigo_Postal+ "\",\"" +fecha_Inicio+ "\",\"" +v.Observaciones+"\")");
        }
        /*Captura de errores*/
        catch(SQLException e){ 
            System.out.println(e); 
            exito=false;
        }
        catch(Exception e){ 
            System.out.println(e);
            exito=false;
        }

        
        ////////////////////
        
        
        
        
        
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
    
    
    static public boolean bajaVoluntario( String DNI, String voluntarioDNI )
    {
        
        boolean confirma=false;
        
        boolean existe = comprobarExistenciaVoluntario( voluntarioDNI );
        
        if(existe)
        {
            
            boolean existe1 = comprobarExistenciaVoluntario(DNI);
            
            if(existe1)
            {
                
                confirma = eliminarVoluntario(DNI);
                
                registrarOperacion(DNI, voluntarioDNI, "baja voluntario", new Date());  //le paso la fecha actual

            }
            
        }
        
        return confirma;
    
    }
    
    
    
    public static boolean eliminarVoluntario(String DNI)
    {
        
        boolean confirma=true;
        

        
        
        //realizo un update en la tabla de Usuario, poniendo los campos activo=0 y fechaDesac=fecha_actual 
        
        
        //primero nos conectamos a la BD
        con.conectarBD();
   
        
        try {
        instruccion = (Statement) con.conexion().createStatement();

        

        
        //modifico la tupla en la tabla Usuario

        
        Date fechaActual = new Date();
        instruccion.executeUpdate("UPDATE Usuario SET Activo=0, Fecha_Desactivacion=fechaActual WHERE NIF_CIF = DNI)");
        }
        /*Captura de errores*/
        catch(SQLException e){ 
            System.out.println(e); 
            confirma=false;
        }
        catch(Exception e){ 
            System.out.println(e);
            confirma=false;
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
        
     
        return confirma;
            
    }
    
    
    
    
}
