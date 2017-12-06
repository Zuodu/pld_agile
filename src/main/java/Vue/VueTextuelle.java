package Vue;

import Modele.PointLivraison;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static Vue.VueGraphique.LEFT_OFFSET;
import static Vue.VueGraphique.RAYON_POINTLIVRAISON;

/**
 * Created by flavi on 2017/12/3.
 */
public class VueTextuelle extends JPanel {

    public final static String MODIFIER_POINT = "Modifier";
    public final static String SUPPRIMER_POINT = "Supprimer";
    public final static String PROCHAIN_POINT = "Suivant";
    public final static String PRECEDENT_POINT = "Précédent";
    public final static String UNDO="Undo";
    public final static String REDO="Redo";
    public final static int LENGTH_VUETEXTUELLE = 400;
    public final static int WIDTH_VUETEXTUELLE = 250;
    public final static int ECART = 30;
    public final static int BUTTON_WIDTH = 100;
    public final static int BUTTON_LENGTH = 30;

    private JLabel labelPlageDebut;
    private JLabel labelPlageFin;
    private JLabel labelDuree;
    private JLabel labelArrivee;
    private JLabel labelDepart;



    private JTextField infoPlageDebut;
    private JTextField infoPlageFin;
    private JTextField infoDuree;
    private JTextField infoHeureArrivee;
    private JTextField infoHeureDepart;

    private JButton buttonModifier;
    private JButton buttonSupprimer;
    private JButton buttonNext;
    private JButton buttonPrevious;
    private JButton buttonUndo;
    private JButton buttonRedo;

    public String getInfoPlageDebut() {
        return infoPlageDebut.getText();
    }

    public String getInfoPlageFin() {
        return infoPlageFin.getText();
    }

    public String getInfoDuree() {
        return infoDuree.getText();
    }
    public VueTextuelle(ButtonListener buttonListener) {
        setLayout(null);
        this.setBounds(ECART + VueGraphique.VUEPLAN_WIDTH, FenetrePrincipale.BUTTONPANEL_LENGTH, WIDTH_VUETEXTUELLE, LENGTH_VUETEXTUELLE);
        infoPlageDebut = new JTextField();
        infoPlageDebut.setBounds(100, 0, 150, 30);
        infoPlageFin = new JTextField();
        infoPlageFin.setBounds(100, 40, 150, 30);
        infoDuree = new JTextField();
        infoDuree.setBounds(100, 80, 150, 30);

        infoHeureArrivee = new JTextField();
        infoHeureArrivee.setBounds(100, 120, 150, 30);
        infoHeureArrivee.setEnabled(false);

        infoHeureDepart = new JTextField();
        infoHeureDepart.setBounds(100, 160, 150, 30);
        infoHeureDepart.setEnabled(false);


        this.add(infoPlageDebut);
        this.add(infoPlageFin);
        this.add(infoDuree);
        this.add(infoHeureArrivee);
        this.add(infoHeureDepart);

        labelPlageDebut = new JLabel();
        labelPlageDebut.setText("Plage début: ");
        labelPlageDebut.setBounds(0, 0, 100, 30);
        this.add(labelPlageDebut);

        labelPlageFin = new JLabel();
        labelPlageFin.setText("Plage fin: ");
        labelPlageFin.setBounds(0, 40, 100, 30);
        this.add(labelPlageFin);

        labelDuree = new JLabel();
        labelDuree.setText("Durée : ");
        labelDuree.setBounds(0, 80, 100, 30);
        this.add(labelDuree);

        labelArrivee = new JLabel();
        labelArrivee.setText("Arrivée prévue : ");
        labelArrivee.setBounds(0, 120, 100, 30);
        this.add(labelArrivee);

        labelDepart = new JLabel();
        labelDepart.setText("Départ prévu : ");
        labelDepart.setBounds(0, 160, 100, 30);
        this.add(labelDepart);

        buttonModifier = new JButton(MODIFIER_POINT);
        buttonModifier.setBounds(0, 220, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonModifier);

        buttonSupprimer = new JButton(SUPPRIMER_POINT);
        buttonSupprimer.setBounds(BUTTON_WIDTH + 50, 220, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonSupprimer);

        buttonPrevious = new JButton(PRECEDENT_POINT);
        buttonPrevious.setBounds(0, 280, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonPrevious);

        buttonNext = new JButton(PROCHAIN_POINT);
        buttonNext.setBounds(BUTTON_WIDTH + 50, 280, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonNext);

        buttonUndo=new JButton(UNDO);
        buttonUndo.setBounds(0,340,BUTTON_WIDTH,BUTTON_LENGTH);
        try {
            Image img = ImageIO.read(new File("resources"+File.separator+"Undo.png"));
            buttonUndo.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.add(buttonUndo);

        buttonRedo=new JButton(REDO);
        buttonRedo.setBounds(BUTTON_WIDTH+50,340,BUTTON_WIDTH,BUTTON_LENGTH);
        try {
            Image img = ImageIO.read(new File("resources"+File.separator+"Redo.png"));
            buttonRedo.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.add(buttonRedo);

        buttonPrevious.addActionListener(buttonListener);
        buttonNext.addActionListener(buttonListener);
        buttonSupprimer.addActionListener(buttonListener);
        buttonModifier.addActionListener(buttonListener);
        buttonRedo.addActionListener(buttonListener);
        buttonUndo.addActionListener(buttonListener);
    }

    public void clickedOnPoint(PointLivraison pointLivraison) {
        if(pointLivraison!=null) {
            if (pointLivraison.getDebutPlage() != null) {
                int h = (int) (pointLivraison.getDebutPlage() / 3600);
                int m = (int) ((pointLivraison.getDebutPlage() % 3600) / 60);
                int s = (int) (pointLivraison.getDebutPlage() % 60);
                infoPlageDebut.setText(h + ":" + m + ":" + s);
            } else {
                infoPlageDebut.setText("");
            }
            if (pointLivraison.getFinPlage() != null) {

                int h = (int) (pointLivraison.getFinPlage() / 3600);
                int m = (int) ((pointLivraison.getFinPlage() % 3600) / 60);
                int s = (int) (pointLivraison.getFinPlage() % 60);
                infoPlageFin.setText(h + ":" + m + ":" + s);
            } else {
                infoPlageFin.setText("");
            }

            {
                int h = (int) (pointLivraison.getDuree() / 3600);
                int m = (int) ((pointLivraison.getDuree() % 3600) / 60);
                int s = (int) (pointLivraison.getDuree() % 60);
                infoDuree.setText(h + ":" + m + ":" + s);
            }

            {
                int h = (int) (pointLivraison.getHeureArrivee() / 3600);
                int m = (int) ((pointLivraison.getHeureArrivee() % 3600) / 60);
                int s = (int) (pointLivraison.getHeureArrivee() % 60);
                infoHeureArrivee.setText(h + ":" + m + ":" + s);
            }
            {
                int h = (int) (pointLivraison.getHeureDepart() / 3600);
                int m = (int) ((pointLivraison.getHeureDepart() % 3600) / 60);
                int s = (int) (pointLivraison.getHeureDepart() % 60);
                infoHeureDepart.setText(h + ":" + m + ":" + s);
            }
        }else {
            infoPlageDebut.setText("");
            infoPlageFin.setText("");
            infoDuree.setText("");
            infoHeureArrivee.setText("");
            infoHeureDepart.setText("");
        }

    }

    public void clickedOnPoint (VueGraphique vueGraphique, long id) {
        PointLivraison pointLivraison=null;
        for (PointLivraison p : vueGraphique.getTournee().getListePointLivraisons()) {
            if (p.getId()==id) {
                pointLivraison=p;
            }
        }
        if(pointLivraison!=null) {
            if (pointLivraison.getDebutPlage() != null) {
                int h = (int) (pointLivraison.getDebutPlage() / 3600);
                int m = (int) ((pointLivraison.getDebutPlage() % 3600) / 60);
                int s = (int) (pointLivraison.getDebutPlage() % 60);
                infoPlageDebut.setText(h + ":" + m + ":" + s);
            } else {
                infoPlageDebut.setText("");
            }
            if (pointLivraison.getFinPlage() != null) {

                int h = (int) (pointLivraison.getFinPlage() / 3600);
                int m = (int) ((pointLivraison.getFinPlage() % 3600) / 60);
                int s = (int) (pointLivraison.getFinPlage() % 60);
                infoPlageFin.setText(h + ":" + m + ":" + s);
            } else {
                infoPlageFin.setText("");
            }

            {
                int h = (int) (pointLivraison.getDuree() / 3600);
                int m = (int) ((pointLivraison.getDuree() % 3600) / 60);
                int s = (int) (pointLivraison.getDuree() % 60);
                infoDuree.setText(h + ":" + m + ":" + s);
            }

            {
                int h = (int) (pointLivraison.getHeureArrivee() / 3600);
                int m = (int) ((pointLivraison.getHeureArrivee() % 3600) / 60);
                int s = (int) (pointLivraison.getHeureArrivee() % 60);
                infoHeureArrivee.setText(h + ":" + m + ":" + s);
            }
            {
                int h = (int) (pointLivraison.getHeureDepart() / 3600);
                int m = (int) ((pointLivraison.getHeureDepart() % 3600) / 60);
                int s = (int) (pointLivraison.getHeureDepart() % 60);
                infoHeureDepart.setText(h + ":" + m + ":" + s);
            }
        } else {
            infoPlageDebut.setText("");
            infoPlageFin.setText("");
            infoDuree.setText("");
            infoHeureArrivee.setText("");
            infoHeureDepart.setText("");
        }

    }




}
