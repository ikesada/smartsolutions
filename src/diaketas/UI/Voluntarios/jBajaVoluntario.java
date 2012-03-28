/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.UI.Voluntarios;

import diaketas.UI.UI;
import diaketas.Modelo.ONG.ONG;
import diaketas.Usuarios.Voluntario.Gestor_de_voluntarios;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class jBajaVoluntario extends javax.swing.JPanel {

    public int fase;
    /**
     * Creates new form jAltaBeneficiario
     */
    public jBajaVoluntario() {
        initComponents();
        fase = 0;
        botonCancel.setVisible(true);
        jLabel4.setVisible(false);
        NIF_CIF_Voluntario.setVisible(false);
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
        jLabel2 = new javax.swing.JLabel();
        NIF_CIF = new javax.swing.JTextField();
        botonOK = new javax.swing.JButton();
        botonCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NIF_CIF_Voluntario = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Voluntarios");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Dar de baja a un voluntario");

        jLabel2.setText("DNI/NIF del voluntario");

        NIF_CIF.setColumns(9);

        botonOK.setText("Ok");
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        botonCancel.setText("Cancelar");
        botonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelActionPerformed(evt);
            }
        });

        jLabel4.setText("DNI voluntario actual");

        NIF_CIF_Voluntario.setBackground(new java.awt.Color(255, 255, 153));
        NIF_CIF_Voluntario.setColumns(9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NIF_CIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(NIF_CIF_Voluntario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(botonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCancel)))
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
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NIF_CIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NIF_CIF_Voluntario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonOK)
                    .addComponent(botonCancel))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {                                        
//GEN-FIRST:event_botonOKActionPerformed
        if (fase == 0){
            //Buscar el dni
            
            if (NIF_CIF.getText().compareTo("") == 0){
                JOptionPane.showMessageDialog(this, "El NIF del voluntario a eliminar no se ha introducido.", "NIF Voluntario", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                
                //compruebo que el voluntario que se quiere eliminar ya existe en el sistema
                
                boolean correcto = ONG.comprobarExistenciaVoluntario( (String)NIF_CIF.getText().toUpperCase() );
                
               
                //Se ha encontrado al voluntario a eliminar
                if (correcto == true)
                {
                    
                    //Cambios esteticos
                    NIF_CIF.setVisible(false);
                    jLabel2.setText("¿Desea borrar el voluntario con NIF-CIF " + NIF_CIF.getText()+ "?");
                    botonOK.setText("Confirmar");
                    botonCancel.setVisible(true);
                    jLabel4.setVisible(true);
                    NIF_CIF_Voluntario.setVisible(true);
                    
                    fase = 1;
                    
                }else   //No se encuentra al voluntario...
                    JOptionPane.showMessageDialog(this, "No se ha encontrado ningún voluntario con ese NIF.",
                            "NIF Voluntario", JOptionPane.ERROR_MESSAGE);

           }
            
        }else if (fase ==1){
            
            if (NIF_CIF_Voluntario.getText().compareTo("") == 0){
                JOptionPane.showMessageDialog(this, "El NIF del voluntario actual no se ha introducido.", "NIF Voluntario", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                
                //compruebo que el voluntario actual existe en el sistema
                
                boolean correcto2 = ONG.comprobarExistenciaVoluntario( NIF_CIF_Voluntario.getText().toUpperCase() );
                
                //Se ha encontrado al voluntario actual en el sistema
                if (correcto2 == true)
                {
                    
                    
                    //llamo a la funcion que se encarga de dar de baja al voluntario
                    Gestor_de_voluntarios.bajaVoluntario(NIF_CIF.getText().toUpperCase(), NIF_CIF_Voluntario.getText().toUpperCase());
                    
                    
                    //Cambios esteticos
                    botonCancel.setVisible(false);
                    NIF_CIF_Voluntario.setVisible(false);
                    jLabel2.setText("El voluntario ha sido dado de baja correctamente.");
                    jLabel4.setVisible(false);
                    botonOK.setText("Regresar");
                    fase = 2;
    
                }
                else   //No se encuentra al voluntario...
                    JOptionPane.showMessageDialog(this, "No se ha encontrado ningún voluntario con ese NIF.",
                            "NIF Voluntario", JOptionPane.ERROR_MESSAGE);
                
            }
        }else
            UI.cl.show(UI.jPrincipal, "Diaketas");
    
    }//GEN-LAST:event_botonOKActionPerformed

    private void botonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelActionPerformed
            UI.cl.show(UI.jPrincipal, "Diaketas");
            // TODO add your handling code here:
    }//GEN-LAST:event_botonCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NIF_CIF;
    private javax.swing.JTextField NIF_CIF_Voluntario;
    private javax.swing.JButton botonCancel;
    private javax.swing.JButton botonOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
