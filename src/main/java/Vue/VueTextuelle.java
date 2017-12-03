package Vue;

import Modele.PointLivraison;

import javax.swing.*;

/**
 * Created by flavi on 2017/12/3.
 */
public class VueTextuelle extends JPanel {
    public final static int LENGTH_VUETEXTUELLE = 200;
    public final static int WIDTH_VUETEXTUELLE = 300;

    private JTextField infoPlageDebut;
    private JTextField infoPlageFin;
    private JTextField infoDuree;
    private JTextField infoHeureArrivee;
    private JTextField infoHeureDepart;


    public VueTextuelle() {
        setLayout(null);
        this.setBounds(100 + VueGraphique.VUEPLAN_WIDTH, FenetrePrincipale.BUTTONPANEL_LENGTH, WIDTH_VUETEXTUELLE, LENGTH_VUETEXTUELLE);
        infoPlageDebut = new JTextField();
        infoPlageDebut.setBounds(0, 0, 150, 30);
        infoPlageFin = new JTextField();
        infoPlageFin.setBounds(0, 40, 150, 30);
        infoDuree = new JTextField();
        infoDuree.setBounds(0, 80, 150, 30);

        infoHeureArrivee = new JTextField();
        infoHeureArrivee.setBounds(0, 120, 150, 30);
        infoHeureArrivee.setEnabled(false);

        infoHeureDepart = new JTextField();
        infoHeureDepart.setBounds(0, 160, 150, 30);
        infoHeureDepart.setEnabled(false);

        infoHeureDepart.setHorizontalAlignment(JTextField.CENTER);

        this.add(infoPlageDebut);
        this.add(infoPlageFin);
        this.add(infoDuree);
        this.add(infoHeureArrivee);
        this.add(infoHeureDepart);

    }

    public void clickedOnPoint(PointLivraison pointLivraison) {
        if (pointLivraison.getDebutPlage() != null)
            infoPlageDebut.setText(pointLivraison.getDebutPlage().toString());
        if (pointLivraison.getFinPlage() != null)

            infoPlageFin.setText(pointLivraison.getFinPlage().toString());

        infoDuree.setText("" + pointLivraison.getDuree());

        infoHeureArrivee.setText("" + pointLivraison.getHeureArrivee());

        infoHeureDepart.setText("" + pointLivraison.getHeureDepart());


    }
}
