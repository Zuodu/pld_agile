package Vue;

import Modele.PointLivraison;
import Modele.Tournee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by flavi on 2017/12/3.
 */
public class VueTextuelle extends JPanel {

    public final static String MODIFIER_POINT = "Modifier";
    public final static String SUPPRIMER_POINT = "Supprimer";
    public final static String PROCHAIN_POINT = "Suivant";
    public final static String PRECEDENT_POINT = "Précédent";
    public final static String UNDO = "Undo";
    public final static String REDO = "Redo";
    public final static int LENGTH_VUETEXTUELLE = 400;
    public final static int WIDTH_VUETEXTUELLE = 250;
    public final static int ECART = 30;
    public final static int BUTTON_WIDTH = 100;
    public final static int BUTTON_LENGTH = 30;
    public final static int TEXTFIELD_WIDTH=150;
    public final static int TEXTFIELD_LENGTH=30;
    public final static int TEXTFIELD_LEFT_POS=100;
    public final static int TEXTFIELD_TOP_POS=40;
    public final static int LABEL_WIDTH=100;
    public final static int LABEL_LENGTH=30;
    public final static int LABEL_LEFT_POS=0;
    public final static int LABEL_TOP_POS=40;
    public final static int BUTTON_LEFT_POS=0;
    public final static int BUTTON_TOP_POS=220;
    public final static int BUTTON_HECART=50;
    public final static int BUTTON_VECART=60;


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

    /**
     * getter
     *
     * @return
     */
    public String getInfoPlageDebut() {
        return infoPlageDebut.getText();
    }

    /**
     * getter
     *
     * @return
     */
    public String getInfoPlageFin() {
        return infoPlageFin.getText();
    }

    /**
     * getter
     *
     * @return
     */
    public String getInfoDuree() {
        return infoDuree.getText();
    }

    /**
     * constructeur
     *
     * @param buttonListener
     */
    public VueTextuelle(ButtonListener buttonListener) {
        setLayout(null);
        this.setBounds(ECART + VueGraphique.VUEPLAN_WIDTH, FenetrePrincipale.BUTTONPANEL_LENGTH, WIDTH_VUETEXTUELLE, LENGTH_VUETEXTUELLE);
        infoPlageDebut = new JTextField();
        infoPlageDebut.setBounds(TEXTFIELD_LEFT_POS, TEXTFIELD_TOP_POS*0, TEXTFIELD_WIDTH, TEXTFIELD_LENGTH);
        infoPlageFin = new JTextField();
        infoPlageFin.setBounds(TEXTFIELD_LEFT_POS, TEXTFIELD_TOP_POS*1, TEXTFIELD_WIDTH, TEXTFIELD_LENGTH);
        infoDuree = new JTextField();
        infoDuree.setBounds(TEXTFIELD_LEFT_POS, TEXTFIELD_TOP_POS*2, TEXTFIELD_WIDTH, TEXTFIELD_LENGTH);
        infoDuree.setEnabled(false);

        infoHeureArrivee = new JTextField();
        infoHeureArrivee.setBounds(TEXTFIELD_LEFT_POS, TEXTFIELD_TOP_POS*3, TEXTFIELD_WIDTH, TEXTFIELD_LENGTH);
        infoHeureArrivee.setEnabled(false);

        infoHeureDepart = new JTextField();
        infoHeureDepart.setBounds(TEXTFIELD_LEFT_POS, TEXTFIELD_TOP_POS*4, TEXTFIELD_WIDTH, TEXTFIELD_LENGTH);
        infoHeureDepart.setEnabled(false);


        this.add(infoPlageDebut);
        this.add(infoPlageFin);
        this.add(infoDuree);
        this.add(infoHeureArrivee);
        this.add(infoHeureDepart);

        labelPlageDebut = new JLabel();
        labelPlageDebut.setText("Plage début: ");
        labelPlageDebut.setBounds(LABEL_LEFT_POS, LABEL_TOP_POS*0, LABEL_WIDTH, LABEL_LENGTH);
        this.add(labelPlageDebut);

        labelPlageFin = new JLabel();
        labelPlageFin.setText("Plage fin: ");
        labelPlageFin.setBounds(LABEL_LEFT_POS, LABEL_TOP_POS*1, LABEL_WIDTH, LABEL_LENGTH);
        this.add(labelPlageFin);

        labelDuree = new JLabel();
        labelDuree.setText("Durée : ");
        labelDuree.setBounds(LABEL_LEFT_POS, LABEL_TOP_POS*2, LABEL_WIDTH, LABEL_LENGTH);
        this.add(labelDuree);

        labelArrivee = new JLabel();
        labelArrivee.setText("Arrivée prévue : ");
        labelArrivee.setBounds(LABEL_LEFT_POS, LABEL_TOP_POS*3, LABEL_WIDTH, LABEL_LENGTH);
        this.add(labelArrivee);

        labelDepart = new JLabel();
        labelDepart.setText("Départ prévu : ");
        labelDepart.setBounds(LABEL_LEFT_POS, LABEL_TOP_POS*4, LABEL_WIDTH, LABEL_LENGTH);
        this.add(labelDepart);

        buttonModifier = new JButton(MODIFIER_POINT);
        buttonModifier.setBounds(BUTTON_LEFT_POS, BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonModifier);

        buttonSupprimer = new JButton(SUPPRIMER_POINT);
        buttonSupprimer.setBounds(BUTTON_LEFT_POS+BUTTON_WIDTH + BUTTON_HECART, BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonSupprimer);

        buttonPrevious = new JButton(PRECEDENT_POINT);
        buttonPrevious.setBounds(BUTTON_LEFT_POS, BUTTON_VECART+BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonPrevious);

        buttonNext = new JButton(PROCHAIN_POINT);
        buttonNext.setBounds(BUTTON_LEFT_POS+BUTTON_WIDTH + BUTTON_HECART, BUTTON_VECART+BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        this.add(buttonNext);

        buttonUndo = new JButton(UNDO);
        buttonUndo.setBounds(BUTTON_LEFT_POS, BUTTON_VECART*2+BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        try {
            buttonUndo.setIcon(new ImageIcon(getClass().getResource("/Undo.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.add(buttonUndo);

        buttonRedo = new JButton(REDO);
        buttonRedo.setBounds(BUTTON_LEFT_POS+BUTTON_WIDTH + BUTTON_HECART, BUTTON_VECART*2+BUTTON_TOP_POS, BUTTON_WIDTH, BUTTON_LENGTH);
        try {
            buttonRedo.setIcon(new ImageIcon(getClass().getResource("/Redo.png")));
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

    /**
     * afficher un l'info detaille d'un point de livraison
     *
     * @param pointLivraison
     */
    public void afficheDetailPointChoisi(PointLivraison pointLivraison) {
        if (pointLivraison != null) {
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

    /**
     * afficher un l'info detaille d'un point de livraison
     *
     * @param tournee
     * @param id
     */
    public void afficheDetailPointChoisi(Tournee tournee, long id) {
        PointLivraison pointLivraison = null;
        for (PointLivraison p : tournee.getListePointLivraisons()) {
            if (p.getId() == id) {
                pointLivraison = p;
            }
        }
        if (pointLivraison != null) {
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
