/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Exercise;
import model.Practice;
import model.QuestionCalculation;
import model.QuestionCustom;
import model.QuestionEquation;
import model.QuestionFraction;
import model.QuestionPower;

/**
 *
 * @author Maxime Blaise
 */
public class BmgPanelPractice {

    private final Exercise e;
    JPanel panHaut = new JPanel();
    JPanel panCenter;
    BmgFrame fen;
    int iterateur = 0;
    private int nombreDeQuestions = 0;
    Practice practice;

    public BmgPanelPractice(BmgFrame fen, Exercise e) {
        super();
        this.fen = fen;

        //Initialisation de l'exercice
        this.e = e;
        setNombreDeQuestions(e.getNumberOfQuestions());

        setPanel();

        //GO !! initialisation de practice
        practice = new Practice(e);
    }

    public void setPanel() {
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());

        //Panel du haut
        this.setPanHaut();
        pan.add(panHaut, BorderLayout.NORTH);

        //Panel du centre
        this.setPanCenter();
        pan.add(panCenter, BorderLayout.CENTER);

        fen.setPanel(pan);
    }

    public void setPanHaut() {
        //JPanel panHaut = new JPanel();
        panHaut.setLayout(new BorderLayout());

        String color1 = "black";
        String color2 = "grey";
        panHaut.add(new JLabel("<html><p style=\"margin-left: 380x;\"><br/>Vous faites l'exercice : " + e.getTitle() + "</p></html>"), BorderLayout.NORTH);

        panHaut.add(new JLabel("<html><p style=\"font-size: 16px;margin-left: 50x; color: " + color2 + ";\"><br/>Énoncé : " + e.getWordingText() + "</p></html>"), BorderLayout.CENTER);
    }

    public void setPanCenter() {
        iterateur++;
        panCenter = new JPanel();

        //Définition de la taille 
        panCenter.setBackground(new Color(45, 45, 45));
        panCenter.setPreferredSize(new Dimension(fen.width, 400));

        //On test si on arrive à la fin de l'exercice
        if (isFini()) {

            practice.updateSuccess();

            //Affichage
            panCenter.add(new BmgLabel("<br/><br/>C'est fini !"
                    + "<br/><br/>Score : " + practice.getSuccess() + "%"
                    + "<br/><br/>Mauvaise(s) réponse(s) : " + practice.getWrong_answersString()
                    + "<br/><br/>Temps d'execution : " + practice.getExecution_time() + "sec"
                    + "<br/><br/>Date d'execution : " + practice.getExecution_dateString(), "white", 14));

        } else {

            JPanel panCenter1 = new JPanel();

            panCenter1.setPreferredSize(new Dimension(fen.width - 199, 349));
            panCenter1.setBackground(new Color(45, 45, 45));

            //Si c'est pas fini on continue
            JLabel lab = new JLabel("<html><p style=\"color: orange;font-size: 14px;\">Question " + iterateur + " / " + getNombreDeQuestions() + "</p><br/><p style=\"color: white;font-size:13px;\">" + e.getQuestionText(iterateur - 1) + "</p>");
            lab.setPreferredSize(new Dimension(fen.width - 200, 100));

            panCenter1.add(lab);

            //Champ de saisie
            final JTextField saisie = new JTextField(30);
            saisie.setPreferredSize(new Dimension(50, 30));

            saisie.setEnabled(true);

            KeyListener key = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent ke) {
                }

                @Override
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        //if enter
                        action(saisie.getText());
                        setPanel();
                    }
                }

                @Override
                public void keyReleased(KeyEvent ke) {
                }

            };

            saisie.addKeyListener(key);

            panCenter1.add(saisie);
            panCenter.add(panCenter1);

            //Next
            JButton next = new JButton("Suivant");
            //Listener
            next.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    //System.out.println("Valeur de l'itérateur : " + iterateur);

                    action(saisie.getText());
                    setPanel();
                }
            });

            //Add button
            panCenter.add(next);
        }

    }

    public void action(String stringRecup) {
        if (stringRecup.equals("")) {
            practice.addWrong(iterateur - 1);
        } else {
            switch (e.getType()) {
                case "calculation":
                    actionIfCalculation(stringRecup);
                    break;
                case "fraction":
                    actionIfFraction(stringRecup);
                    break;
                case "power":
                    actionIfPower(stringRecup);
                    break;
                case "equation":
                    actionIfEquation(stringRecup);
                    break;
                case "custom":
                    actionIfCustom(stringRecup);
                    break;
            }
        }

    }

    public void actionIfCalculation(String recup) {

        //réponse
        double anwser = new Double(recup);

        //test
        if (anwser == ((QuestionCalculation) e.getQuestion(iterateur - 1)).solve()) {
            practice.addRight(iterateur - 1);
        } else {
            practice.addWrong(iterateur - 1);
        }
    }
    
    public void actionIfFraction(String recup) {

        //réponse
        double anwser = new Double(recup);

        //test
        if (anwser == ((QuestionFraction) e.getQuestion(iterateur - 1)).solve()) {
            practice.addRight(iterateur - 1);
        } else {
            practice.addWrong(iterateur - 1);
        }
    }
    
    public void actionIfPower(String recup) {

        //réponse
        double anwser = new Double(recup);

        //test
        if (anwser == ((QuestionPower) e.getQuestion(iterateur - 1)).solve()) {
            practice.addRight(iterateur - 1);
        } else {
            practice.addWrong(iterateur - 1);
        }
    }
    
    public void actionIfEquation(String recup) {

        //réponse
        double anwser = new Double(recup);

        //test
        if (anwser == ((QuestionEquation) e.getQuestion(iterateur - 1)).solve()[1]) {
            practice.addRight(iterateur - 1);
        } else {
            practice.addWrong(iterateur - 1);
        }
    }
    
    public void actionIfCustom(String recup) {

        //test
        if (recup.equals(((QuestionCustom) e.getQuestion(iterateur - 1)).getSolutionString())) {
            practice.addRight(iterateur - 1);
        } else {
            practice.addWrong(iterateur - 1);
        }
    }

    /**
     * Méthode qui permet de savoir si l'exercice est fini.
     *
     * @return
     */
    public boolean isFini() {
        //Retourne true si l'itérateur dépasse le nombre de question
        return getIterateur() > getNombreDeQuestions();
    }

    public int getIterateur() {
        return iterateur;
    }

    public void setIterateur(int iterateur) {
        this.iterateur = iterateur;
    }

    /**
     * Récupère le nombre de questions de l'exercice.
     *
     * @return le nombre de question.
     */
    public int getNombreDeQuestions() {
        return nombreDeQuestions;
    }

    /**
     * Permet de modifier le nombre de questions de l'exercice
     *
     * @param nombreDeQuestions
     */
    public void setNombreDeQuestions(int nombreDeQuestions) {
        this.nombreDeQuestions = nombreDeQuestions;
    }

}
