/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

// Here all imports needed for this class.
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Maxime Blaise
 */
public class BmgMenuBar extends JMenuBar {

    JButton buttonIndex = new JButton(new ImageIcon("index_small.png"));
    JMenu menuExercises = new JMenu("Exercices");
    JMenuItem itemGenerate = new JMenuItem("Générer");
    JMenuItem itemCustom = new JMenuItem("Créer");
    JMenuItem itemPractice = new JMenuItem("Pratiquer");
    JMenuItem itemSolve = new JMenuItem("Résoudre");
    JMenuItem itemImport = new JMenuItem("Importer");
    JMenuItem itemExport = new JMenuItem("Exporter");
    JMenuItem itemEdit = new JMenuItem("Éditer");

    JMenu menuAccount = new JMenu("Compte");
    JMenuItem itemSignIn = new JMenuItem("Se connecter");
    JMenuItem itemSignUp = new JMenuItem("S'enregistrer");

    JMenu menuSettings = new JMenu("Configuration");
    JMenuItem itemBaseSettings = new JMenuItem("Base de données");
    JMenuItem itemConnexionTest = new JMenuItem("Tester la connexion");

    JMenu menuHelp = new JMenu("?");
    JMenuItem itemHelp = new JMenuItem("Aide");
    JMenuItem itemAbout = new JMenuItem("À propos");

    BmgFrame fen;
   // String s = "Not connected yet";
    String slabel;
    JLabel labelConnexion;

    /**
     * Create BmgMenuBar
     *
     * @param fen
     */
    public BmgMenuBar(BmgFrame fen) {
        super();
        this.fen = fen;

        buttonIndex.setMaximumSize(new Dimension(40, 40));

        setLabel();
        setMenuSettings(fen.width);
    }
    
    public void setLabel() {
        String s = fen.getLabelConnection();
        this.slabel = "<html><p style=\"margin-left: 50px;\">" + s + "</p></html>";
        this.labelConnexion = new JLabel(slabel);
        this.labelConnexion.repaint();
        
        repaint();
    }

    private void setMenuSettings(int width) {
        this.setPreferredSize(new Dimension(width, 40));

        setIndexSettings();

        setMenuExercisesSettings();

        setMenuAccountSettings();

        setMenuBaseSettings();

        setMenuHelpSettings();

        //Others settings
        
        this.add(labelConnexion);
    }

    /**
     * Exercises menu.
     */
    private void setMenuExercisesSettings() {
        //Add listener and add items.
        addListenersMenuExercises();
        addItemMenuExercises();
    }

    /**
     * Add listeners in all items in menu exercises.
     */
    private void addListenersMenuExercises() {
        //Generate exercises
        itemGenerate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
        itemGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panGenerate);
            }
        });
        
        

        //Custom exercises
        itemCustom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        itemCustom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panCustom);
            }
        });
        
        
        //Practice exercises !
        itemPractice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
        itemPractice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panPractice);
            }
        });
        
        //Export 
        itemExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        itemExport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panExport);
            }
        });
        
        //Import
        itemImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
        itemImport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panImport);
            }
        });
        
        itemSolve.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
        itemSolve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSolve);
            }
        });
        
    }

    /**
     * Add all items into menu.
     */
    private void addItemMenuExercises() {

        menuExercises.add(itemGenerate);
        menuExercises.add(itemCustom);
        menuExercises.add(itemPractice);
        menuExercises.add(itemSolve);
        menuExercises.add(itemImport);
        menuExercises.add(itemExport);
        //menuExercises.add(itemEdit);
        this.add(menuExercises);
    }

    /**
     * Help menu.
     */
    private void setMenuHelpSettings() {
        //Add listener and add items.
        addListenersMenuHelp();
        addItemMenuHelp();
    }

    /**
     * Add listeners in all items in help menu.
     */
    private void addListenersMenuHelp() {
        // About Us !
        itemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                fen.setPanel(BmgFrame.panAboutUs);
            }
        });

        itemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        itemHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //Dialog
                JDialog dialog = createDialogHelp();
            }
        });
    }

    public JDialog createDialogHelp() {
        final JDialog dialog = new JDialog();

        JPanel pan = new JPanel();
        pan.add(new BmgLabel("Les exercices sont enregistrés dans le dossier où se situe le programme, au format \".bmg\"", "#E43B01"));

        //Close button
        JButton fermer = new JButton("Fermer");
        fermer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();

            }
        });

        pan.add(fermer);
        
        //Dialog settings
        dialog.setContentPane(pan);
        dialog.setTitle("Aide");
        dialog.setLocation(300, 300);
        dialog.setPreferredSize(new Dimension(800, 100));
        dialog.pack();
        dialog.setVisible(true);

        return dialog;
    }

    /**
     * Add items into help menu.
     */
    private void addItemMenuHelp() {
        menuHelp.add(itemHelp);
        menuHelp.add(itemAbout);
        this.add(menuHelp);
    }

    /**
     * Menu base settings, with information of database and test connection.
     */
    private void setMenuBaseSettings() {
        addListenersBaseMenu();
        addItemBaseMenu();
    }

    /**
     * Add listeners in all items in base menu.
     */
    private void addListenersBaseMenu() {
        // Database settings
        itemBaseSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        itemBaseSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                fen.setPanel(BmgFrame.panSettings);
            }
        });

        //Connexion test
        itemConnexionTest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
        itemConnexionTest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane jop = new JOptionPane();
                fen.bs.setInfo();
                if (fen.bs.testerConnexion()) {
                    JOptionPane.showMessageDialog(null, "Connexion OK !", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur !", "Information", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    /**
     * Add items into base menu.
     */
    private void addItemBaseMenu() {
        menuSettings.add(itemBaseSettings);
        menuSettings.add(itemConnexionTest);
        this.add(menuSettings);
    }

    /**
     * Set settings for account menu, with sign in and sign up.
     */
    private void setMenuAccountSettings() {
        addListenersAccountMenu();
        addItemAccountMenu();
    }

    /**
     * Add listeners in all items in account menu.
     */
    private void addListenersAccountMenu() {
        // Sign in
        itemSignIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSignin);
            }
        });

        // Sign up 
        itemSignUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSignup);
            }
        });
    }

    /**
     * Add items into account menu.
     */
    private void addItemAccountMenu() {
        menuAccount.add(itemSignIn);
        menuAccount.add(itemSignUp);
        this.add(menuAccount);
    }

    /**
     * Set settings for index button.
     */
    private void setIndexSettings() {
        //Ajout raccourci clavier sur le button
        //buttonIndex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
        buttonIndex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panMain);
            }
        });
        this.add(buttonIndex);
    }
}
