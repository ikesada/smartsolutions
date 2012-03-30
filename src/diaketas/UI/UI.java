/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.UI;

import diaketas.UI.Beneficiarios.jBeneficiario;
import diaketas.UI.Voluntarios.jVoluntarios;
import diaketas.UI.Donaciones.jDonaciones;
import diaketas.UI.Donantes.jDonantes;
import diaketas.UI.Empleo.jEmpleo;
import diaketas.UI.HistorialesyAcciones.jHistorialyAcciones;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author kesada
 */
public class UI extends javax.swing.JFrame {
    
    /**
     * 
     */
    public static CardLayout cl;
     
    /**
     * Creates new form Main
     */
    public UI() {
        initComponents();
  //      jPrincipalScroll.getViewport().setView(jPrincipal);
        cl = (CardLayout)(jPrincipal.getLayout());
        
        /*Paneles acciones */
        /*Categorias principales jPrincipal*/
        JPanel beneficiarios = new jBeneficiario();
        JPanel diaketas = new jVoluntarios();
        JPanel donaciones = new jDonaciones();
        JPanel empleo = new jEmpleo();
        JPanel donantes = new jDonantes();
        JPanel historialyacciones = new jHistorialyAcciones();
       
        
        /*JPrincipal*/
        jPrincipal.add("Donantes", donantes);
        jPrincipal.add("Empleo", empleo);
        jPrincipal.add("Donaciones", donaciones);
        jPrincipal.add("Diaketas", diaketas);
        jPrincipal.add("Beneficiarios", beneficiarios);
        jPrincipal.add("HistorialyAcciones", historialyacciones);
        
        /*Mostramos Diaketas*/
        cl.show(jPrincipal, "Diaketas");
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBotones = new javax.swing.JPanel();
        botonDiaketas = new javax.swing.JButton();
        botonDonantes = new javax.swing.JButton();
        botonBeneficiarios = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botonDonaciones = new javax.swing.JButton();
        botonEmpleo = new javax.swing.JButton();
        jPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diaketas");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1262, 628));
        setName("Principal");

        jMenuBotones.setLayout(new java.awt.GridLayout(1, 0));

        botonDiaketas.setFont(new Font("Courier", Font.BOLD,18));
        botonDiaketas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/Voluntario.png"))); // NOI18N
        botonDiaketas.setText("Voluntarios");
        botonDiaketas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDiaketasActionPerformed(evt);
            }
        });
        jMenuBotones.add(botonDiaketas);
        botonDiaketas.setVerticalTextPosition(SwingConstants.BOTTOM);
        botonDiaketas.setHorizontalTextPosition(SwingConstants.CENTER);

        botonDonantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/Socios.gif"))); // NOI18N
        botonDonantes.setText("Donantes");
        botonDonantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDonantesActionPerformed(evt);
            }
        });
        jMenuBotones.add(botonDonantes);
        botonDonantes.setVerticalTextPosition(SwingConstants.BOTTOM);
        botonDonantes.setHorizontalTextPosition(SwingConstants.CENTER);
        botonDonantes.setFont(new Font("Courier", Font.BOLD,18));

        botonBeneficiarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/beneficiarios.png"))); // NOI18N
        botonBeneficiarios.setText("Beneficiarios");
        botonBeneficiarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBeneficiariosActionPerformed(evt);
            }
        });
        jMenuBotones.add(botonBeneficiarios);
        botonBeneficiarios.setFont(new Font("Courier", Font.BOLD,18));
        botonBeneficiarios.setHorizontalTextPosition(SwingConstants.CENTER);
        botonBeneficiarios.setVerticalTextPosition(SwingConstants.BOTTOM);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/historiales.png"))); // NOI18N
        jButton1.setText("Historiales");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jMenuBotones.add(jButton1);
        jButton1.setFont(new Font("Courier", Font.BOLD,18));
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);

        botonDonaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/Donaciones.png"))); // NOI18N
        botonDonaciones.setText("Donaciones");
        botonDonaciones.setEnabled(false);
        botonDonaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDonacionesActionPerformed(evt);
            }
        });
        jMenuBotones.add(botonDonaciones);
        botonDonaciones.setFont(new Font("Courier", Font.BOLD,18));
        botonDonaciones.setHorizontalTextPosition(SwingConstants.CENTER);
        botonDonaciones.setVerticalTextPosition(SwingConstants.BOTTOM);

        botonEmpleo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/empleo.png"))); // NOI18N
        botonEmpleo.setText("Empleo");
        botonEmpleo.setEnabled(false);
        botonEmpleo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpleoActionPerformed(evt);
            }
        });
        jMenuBotones.add(botonEmpleo);
        botonEmpleo.setFont(new Font("Courier", Font.BOLD,18));
        botonEmpleo.setHorizontalTextPosition(SwingConstants.CENTER);
        botonEmpleo.setVerticalTextPosition(SwingConstants.BOTTOM);

        getContentPane().add(jMenuBotones, java.awt.BorderLayout.NORTH);

        jPrincipal.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBeneficiariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBeneficiariosActionPerformed
        /*Modificamos zona principal*/
        cl.show(jPrincipal, "Beneficiarios");
       this.setBackground(new java.awt.Color(205,255,204));

    }//GEN-LAST:event_botonBeneficiariosActionPerformed

    private void botonDiaketasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDiaketasActionPerformed
        /*Modificamos zona principal*/
        cl.show(jPrincipal, "Diaketas");
        this.setBackground(new java.awt.Color(255, 204, 204));
    }//GEN-LAST:event_botonDiaketasActionPerformed

    private void botonDonacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDonacionesActionPerformed
        /*Modificamos zona principal*/
        cl.show(jPrincipal, "Donaciones");
    }//GEN-LAST:event_botonDonacionesActionPerformed

    private void botonEmpleoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleoActionPerformed
        /*Modificamos zona principal*/
        cl.show(jPrincipal, "Empleo");
    }//GEN-LAST:event_botonEmpleoActionPerformed

    private void botonDonantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDonantesActionPerformed
        /*Modificamos zona principal*/
        cl.show(jPrincipal, "Donantes");
        this.setBackground(new java.awt.Color(167,223,247));
    }//GEN-LAST:event_botonDonantesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cl.show(jPrincipal, "HistorialyAcciones");
        this.setBackground(new java.awt.Color(255,204,153));
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     */
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("diaketas/Iconos/diaketas.png"));


        return retValue;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBeneficiarios;
    private javax.swing.JButton botonDiaketas;
    private javax.swing.JButton botonDonaciones;
    private javax.swing.JButton botonDonantes;
    private javax.swing.JButton botonEmpleo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jMenuBotones;
    public static javax.swing.JPanel jPrincipal;
    // End of variables declaration//GEN-END:variables
}
