package Vue;

import Modele.PointLivraison;

import javax.swing.*;

/**
 * Created by flavi on 2017/12/3.
 */
public class VueTextuelle extends JPanel {
    public final static int LENGTH_VUETEXTUELLE = 200;
    public final static int WIDTH_VUETEXTUELLE = 250;
    public final static int ECART = 20;

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


    public VueTextuelle() {
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
        labelPlageDebut.setText("Plage debut: ");
        labelPlageDebut.setBounds(0, 0, 100, 30);
        this.add(labelPlageDebut);

        labelPlageFin = new JLabel();
        labelPlageFin.setText("Plage fin: ");
        labelPlageFin.setBounds(0, 40, 100, 30);
        this.add(labelPlageFin);

        labelDuree = new JLabel();
        labelDuree.setText("Duree : ");
        labelDuree.setBounds(0, 80, 100, 30);
        this.add(labelDuree);

        labelArrivee = new JLabel();
        labelArrivee.setText("Arrivee prevue : ");
        labelArrivee.setBounds(0, 120, 100, 30);
        this.add(labelArrivee);

        labelDepart = new JLabel();
        labelDepart.setText("Depart prevu : ");
        labelDepart.setBounds(0, 160, 100, 30);
        this.add(labelDepart);


    }

    public void clickedOnPoint(PointLivraison pointLivraison) {
        if (pointLivraison.getDebutPlage() != null) {
            int h = (int) (pointLivraison.getDebutPlage() / 3600);
            int m = (int) ((pointLivraison.getDebutPlage() % 3600) / 60);
            int s = (int) (pointLivraison.getDebutPlage() % 60);
            infoPlageDebut.setText(h + ":" + m + ":" + s);
        }
        if (pointLivraison.getFinPlage() != null) {

            int h = (int) (pointLivraison.getFinPlage() / 3600);
            int m = (int) ((pointLivraison.getFinPlage() % 3600) / 60);
            int s = (int) (pointLivraison.getFinPlage() % 60);
            infoPlageFin.setText(h + ":" + m + ":" + s);
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

    }
}
