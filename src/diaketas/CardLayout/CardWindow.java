package diaketas.CardLayout;

/*
 * Swing version.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardWindow extends JFrame
                        implements ItemListener {
    boolean inAnApplet = true;
     
    JPanel cards;
    final static String BUTTONPANEL = "JPanel with JButtons";
    final static String TEXTPANEL = "JPanel with JTextField";

    public CardWindow() {
	Container contentPane = getContentPane();

        //Put the JComboBox in a JPanel to get a nicer look.
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JPanel cp = new JPanel();
        JComboBox c = new JComboBox(comboBoxItems);
	c.setEditable(false);
        c.addItemListener(this);
        cp.add(c);

	//Use the default layout manager, BorderLayout
        contentPane.add(cp, BorderLayout.NORTH);

        cards = new JPanel();
        cards.setLayout(new CardLayout());
   
        JPanel p1 = new JPanel();
        p1.add(new JButton("Button 1"));
        p1.add(new JButton("Button 2"));
        p1.add(new JButton("Button 3"));

        JPanel p2 = new JPanel();
        p2.add(new JTextField("TextField", 20));

        cards.add(BUTTONPANEL, p1);
        cards.add(TEXTPANEL, p2);
        contentPane.add(cards, BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (inAnApplet) {
                    dispose();
                } else {
                    System.exit(0);
                }
            }
        });
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public static void main(String args[]) {
        CardWindow window = new CardWindow();
        window.inAnApplet = false;

        window.setTitle("CardLayout");
        window.pack();
        window.setVisible(true);
    }
}