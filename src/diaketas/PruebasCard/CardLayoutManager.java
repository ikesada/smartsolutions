/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.PruebasCard;

/**
 *
 * @author Alex
 */

 
// -----------------------------------------------------------------------------
// CardLayoutManager.java
// -----------------------------------------------------------------------------

/*
 * =============================================================================
 * Copyright (c) 1998-2005 Jeffrey M. Hunter. All rights reserved.
 * 
 * All source code and material located at the Internet address of
 * http://www.idevelopment.info is the copyright of Jeffrey M. Hunter, 2005 and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any other site without my express, prior, written
 * permission. Application to host any of the material elsewhere can be made by
 * contacting me at jhunter@idevelopment.info.
 *
 * I have made every effort and taken great care in making sure that the source
 * code and other content included on my web site is technically accurate, but I
 * disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from relying on it. I will in no
 * case be liable for any monetary damages arising from such loss, damage or
 * destruction.
 * 
 * As with any code, ensure to test this code in a development environment 
 * before attempting to run it in production.
 * =============================================================================
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * -----------------------------------------------------------------------------
 * The CardLayout class is used when you need to manage two or more components 
 * (usually several JPanel containers) that need to share the same area or 
 * display space. The easiest way to understand how the CardLayout layout 
 * manager works, think about traditional stack of playing cards. When the cards
 * are stacked in a pile, only the top card is visible at any given time.
 * 
 * Using our playing card analogy above, here is how to choose which card (or 
 * Swing component) is visiable: 
 * 
 *    - You can obtain the first or last card, in the order that the cards 
 *      (components) where added to the container.
 *    - You can flip through the cards (components) forward or backwards.
 *    - You can specify a card (component) with a specific name.
 * 
 * When you add a component to the CardLayout container, you need to specify a 
 * String that identifies the component.
 * 
 * @version 1.0
 * @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
 * @author  http://www.idevelopment.info
 * -----------------------------------------------------------------------------
 */

public class CardLayoutManager extends JFrame 
                               implements ItemListener {

    // Object fields
    private      JPanel cardsPanel;
    final static String BUTTONPANEL = "JPanel with JButtons";
    final static String TEXTPANEL   = "JPanel with JTextField";

    /**
     * Noarg constructor
     */
    public CardLayoutManager() {

        // ----------------------------
        // Call super class constructor
        // ----------------------------
        super("CardLayout Manager Example");

        // ----------------------------------------------------------------
        // Get the frame's content pane. It is not neccessary to set the 
        // layout manager to BorderLayout (as I did below) since BorderLayout
        // is the default for the frame's content pane.
        // ----------------------------------------------------------------
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // --------------------------------------
        // Put the JComboBox in a seperate JPanel
        // --------------------------------------
        JPanel    comboBoxPanel = new JPanel();
        String    comboBoxItems[] = {BUTTONPANEL, TEXTPANEL};
        JComboBox comboBox = new JComboBox(comboBoxItems);
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        comboBoxPanel.add(comboBox);

        // ---------------------------------------------------
        // Add the JComboBox Panel to the frame's content pane
        // ---------------------------------------------------
        contentPane.add(comboBoxPanel, BorderLayout.NORTH);

        // -----------------------------------------------------------------
        // Make a JPanel for the cards. This panel will be visiable to other 
        // methods.
        // -----------------------------------------------------------------
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new CardLayout());

        JPanel p1 = new JPanel();
        p1.add(new JButton("Button 1"));
        p1.add(new JButton("Button 2"));
        p1.add(new JButton("Button 3"));

        JPanel p2 = new JPanel();
        p2.add(new JTextField("TextField", 20));

        // --------------------------------------------------------------------
        // Add all of the JPanel components you want to the stack. In our case,
        // we created 2 JPanel components that will be added.
        // --------------------------------------------------------------------
        cardsPanel.add(p1, BUTTONPANEL);
        cardsPanel.add(p2, TEXTPANEL);

        // -----------------------------------------------
        // Add the Cards Panel to the frame's content pane
        // -----------------------------------------------
        contentPane.add(cardsPanel, BorderLayout.CENTER);

        // ------------------------------------------------------------
        // Window listener to close application when Window gets closed
        // ------------------------------------------------------------
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
    }


    /**
     * Overridden method that gets called when the user chooses a new option
     * in the item list.
     * @param evt The triggering event
     */
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.show(cardsPanel, (String)evt.getItem());
    }


    /**
     * Sole entry point to the class and application.
     * @param args Array of String arguments.
     */
    public static void main(String[] args) {
        CardLayoutManager mainFrame = new CardLayoutManager();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
}
 
 