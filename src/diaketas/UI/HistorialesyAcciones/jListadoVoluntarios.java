/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.UI.HistorialesyAcciones;

import diaketas.ConexionBD;
import diaketas.Modelo.Gestores.Gestor_de_voluntarios;
import diaketas.Modelo.ONG.Voluntario;
import diaketas.UI.UI;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author cesar
 */
public class jListadoVoluntarios extends javax.swing.JPanel {

          
    /**
     * Creates new form jAltaBeneficiario
     */
    public jListadoVoluntarios() {
        
        initComponents();
        
        
        
        ArrayList<Voluntario> usuarios = new ArrayList<Voluntario>();      
        usuarios = Gestor_de_voluntarios.obtenerVoluntarios();
        
        //System.out.println("Numero de usuarios leidos: "+usuarios.size());
        
        
        /*
         Object [] fila = new Object[3];
			String nom=txtNombre.getText();
			String ape=txtApellido.getText();
			int ed=Integer.parseInt(txtEdad.getText());
			fila[0] = nom;
			fila[1] = ape;
			fila[2]	= ed;
			modelo.addRow ( fila );
         */
        
        //Para establecer el modelo al JTable
        DefaultTableModel modelo = new DefaultTableModel();
        this.jTable1.setModel(modelo);

        
        //PRIMERO VOY A INTENTAR MOSTRAR UNA FILA CON EL PRIMER USUARIO
        Object[] fila = new Object[14];     //creo un objeto fila con 14 campos
        
        fila[0] = (Object)usuarios.get(0).NIF_CIF;
        fila[1] = (Object)usuarios.get(0).Nombre;
    

        fila[2] = usuarios.get(0).Apellidos;
        //fila[3] = usuarios.get(contFilas).FechaNac;
        fila[4] = usuarios.get(0).Localidad;
        fila[5] = usuarios.get(0).Activo;
        //fila[6] = usuarios.get(contFilas).FechaDesac;
        fila[7] = usuarios.get(0).Email;
        fila[8] = usuarios.get(0).Telefono;
        fila[9] = usuarios.get(0).Nacionalidad;
        fila[10] = usuarios.get(0).Domicilio;
        fila[11] = usuarios.get(0).Codigo_Postal;
        //fila[12] = usuarios.get(contFilas).Fecha_Inicio;
        fila[13] = usuarios.get(0).Observaciones;


        //  El elemento jTable no permite mostrar datos del tipo Date, por ello, antes de mostrar el dato
        //  se comprueba si es de tipo Date, y si es asi, se pasa a string para mostrarlo en la tabla.
        //  Primero se lee el tipo Date, y despues con las dos ultimas instrucciones se transforma a String


        if( usuarios.get(0).FechaNac!=null  )
        {
            java.util.Date date = (java.util.Date) usuarios.get(0).FechaNac;   //primero leo el objeto fecha
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(date);
            fila[3] = fecha;                
        }
        else
        {
            fila[3] = "";

        }

        if( usuarios.get(0).FechaDesac!=null  )
        {
            java.util.Date date = (java.util.Date) usuarios.get(0).FechaDesac;   //primero leo el objeto fecha
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(date);
            fila[6] = fecha;                
        }
        else
        {
            fila[6] = "";

        }

        if( usuarios.get(0).Fecha_Inicio!=null  )
        {
            java.util.Date date = (java.util.Date) usuarios.get(0).Fecha_Inicio;   //primero leo el objeto fecha
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(date);
            fila[12] = fecha;                
        }
        else
        {
            fila[12] = "";

        }

        modelo.addRow(fila);
        
/*  LO MISMO PERO PARA TODOS LOS USUARIOS CONTENIDOS EN EL ARRAYLIST        
        int contFilas = 0;
        while ( contFilas < usuarios.size() ) 
        { 
            Object[] fila = new Object[14];     //creo un objeto fila con 14 campos
            
            
            System.out.println("NIF_CIF del voluntario primero:"+usuarios.get(contFilas).NIF_CIF);
            fila[0] = usuarios.get(contFilas).NIF_CIF;
            fila[1] = usuarios.get(contFilas).Nombre;
            fila[2] = usuarios.get(contFilas).Apellidos;
            //fila[3] = usuarios.get(contFilas).FechaNac;
            fila[4] = usuarios.get(contFilas).Localidad;
            fila[5] = usuarios.get(contFilas).Activo;
            //fila[6] = usuarios.get(contFilas).FechaDesac;
            fila[7] = usuarios.get(contFilas).Email;
            fila[8] = usuarios.get(contFilas).Telefono;
            fila[9] = usuarios.get(contFilas).Nacionalidad;
            fila[10] = usuarios.get(contFilas).Domicilio;
            fila[11] = usuarios.get(contFilas).Codigo_Postal;
            //fila[12] = usuarios.get(contFilas).Fecha_Inicio;
            fila[13] = usuarios.get(contFilas).Observaciones;
            
            
            //  El elemento jTable no permite mostrar datos del tipo Date, por ello, antes de mostrar el dato
            //    se comprueba si es de tipo Date, y si es asi, se pasa a string para mostrarlo en la tabla.
            //    Primero se lee el tipo Date, y despues con las dos ultimas instrucciones se transforma a String
            
            
            if( usuarios.get(contFilas).FechaNac!=null  )
            {
                java.util.Date date = (java.util.Date) usuarios.get(contFilas).FechaNac;   //primero leo el objeto fecha
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(date);
                fila[3] = fecha;                
            }
            else
            {
                fila[3] = "";

            }
            
            if( usuarios.get(contFilas).FechaDesac!=null  )
            {
                java.util.Date date = (java.util.Date) usuarios.get(contFilas).FechaDesac;   //primero leo el objeto fecha
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(date);
                fila[6] = fecha;                
            }
            else
            {
                fila[6] = "";

            }
            
            if( usuarios.get(contFilas).Fecha_Inicio!=null  )
            {
                java.util.Date date = (java.util.Date) usuarios.get(contFilas).Fecha_Inicio;   //primero leo el objeto fecha
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(date);
                fila[12] = fecha;                
            }
            else
            {
                fila[12] = "";

            }
            
            modelo.addRow(fila);

            contFilas++;
        }
*/    
            


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        botonOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Historial y Acciones");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Listado voluntarios");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        botonOK.setText("Ok");
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIF", "Nombre", "Apellidos", "Fecha nacimiento", "Nacionalidad", "Domicilio", "Codigo postal", "Localidad", "Telefono", "Fecha inicio", "Observaciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(442, 442, 442)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34)
                                .addComponent(botonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(botonOK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOKActionPerformed
        // TODO add your handling code here:       
            
            UI.cl.show(UI.jPrincipal, "HistorialyAcciones");
       
    }//GEN-LAST:event_botonOKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
