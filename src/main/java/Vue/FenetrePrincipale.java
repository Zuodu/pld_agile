package Vue;

import Controleur.Controleur;
import Modele.Plan;
import Modele.Tournee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class FenetrePrincipale extends JFrame implements Observer {
    public static final String CHARGER_PLAN = "Charger Plan";
    public static final String CHARGER_LIVRAISONS = "Charger Livraisons";
    public static final String CALCULER_TOURNEE = "Calculer Tournée";
    public static final String AJOUTER_POINT = "Ajouter Point de Livraison";
    public static final String GENERER_FEUILLE = "Générer Feuille de Route";
    public static final int BUTTON_WIDTH = 250;
    public static final int BUTTON_HEIGHT = 40;
    public static final int BUTTON_SPACE_VERTICAL = 50;
    public static final int ECART = 40;
    public static final int FENETRE_WIDTH = 1020;
    public static final int FENETRE_LENGTH = 780;
    public static final int BUTTONPANEL_WIDTH = 400;
    public static final int BUTTONPANEL_LENGTH = 300;
    private JPanel contentPane;
    private JButton buttonChargerLivraisons;
    private JButton buttonChargerPlan;
    private JButton buttonCalculerTournee;
    private JButton buttonAddPoint;
    private JButton buttonGenerate;

    private ButtonListener buttonListener;
    private MouseListener mouseListener;
    private VueGraphique vueGraphique;
    private VueTextuelle vueTextuelle;
    private Controleur controleur;
    private Plan plan;
    private Tournee tournee;
    private JPanel buttonPanel;


    public VueGraphique getVueGraphique() {
        return vueGraphique;
    }

    public VueTextuelle getVueTextuelle() {
        return vueTextuelle;
    }

    /**
     * Constructeur
     * Creer la fenetre principale
     *
     * @param plan
     * @param tournee
     * @param controleur
     */
    public FenetrePrincipale(Plan plan, Tournee tournee, Controleur controleur) {

        this.plan = plan;
        plan.addObserver(this);
        this.tournee = tournee;
        tournee.addObserver(this);
        this.controleur = controleur;
        buttonListener = new ButtonListener(controleur, this);

        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, FENETRE_WIDTH, FENETRE_LENGTH);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);


        vueGraphique = new Vue.VueGraphique(plan, tournee);
        contentPane.add(vueGraphique);
        vueTextuelle = new VueTextuelle(buttonListener);
        contentPane.add(vueTextuelle);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(VueGraphique.VUEPLAN_WIDTH + ECART, 20, BUTTONPANEL_WIDTH, BUTTONPANEL_LENGTH);

        buttonChargerPlan = new JButton(CHARGER_PLAN);
        buttonChargerPlan.addActionListener(buttonListener);
        buttonChargerPlan.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        try {
            buttonChargerPlan.setIcon(new ImageIcon(getClass().getResource("/ChargerPlan.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
        buttonChargerLivraisons.addActionListener(buttonListener);
        buttonChargerLivraisons.setBounds(0, BUTTON_SPACE_VERTICAL, BUTTON_WIDTH, BUTTON_HEIGHT);
        try {
            buttonChargerLivraisons.setIcon(new ImageIcon(getClass().getResource("/ChargerLivraisons.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonCalculerTournee = new JButton(CALCULER_TOURNEE);
        buttonCalculerTournee.addActionListener(buttonListener);
        buttonCalculerTournee.setBounds(0, BUTTON_SPACE_VERTICAL * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        try {
            buttonCalculerTournee.setIcon(new ImageIcon(getClass().getResource("/CalculerTournee.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonAddPoint = new JButton(AJOUTER_POINT);
        buttonAddPoint.addActionListener(buttonListener);
        buttonAddPoint.setBounds(0, BUTTON_SPACE_VERTICAL * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
        try {
            buttonAddPoint.setIcon(new ImageIcon(getClass().getResource("/ajouterPtLivraison.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonGenerate = new JButton(GENERER_FEUILLE);
        buttonGenerate.addActionListener(buttonListener);
        buttonGenerate.setBounds(0, BUTTON_SPACE_VERTICAL * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
        try {
            buttonGenerate.setIcon(new ImageIcon(getClass().getResource("/GenererFeuille.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        buttonPanel.add(buttonChargerPlan);
        buttonPanel.add(buttonChargerLivraisons);
        buttonPanel.add(buttonCalculerTournee);
        buttonPanel.add(buttonAddPoint);
        buttonPanel.add(buttonGenerate);
        contentPane.add(buttonPanel);

        mouseListener = new MouseListener(vueGraphique, controleur);
        vueGraphique.addMouseListener(mouseListener);

        repaint();
        contentPane.repaint();

    }

    /**
     * la surcharge du methode update du Observer
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        vueGraphique.repaint();
        if (vueGraphique.getPointLivraisonChoisi() != null) {
            vueTextuelle.afficheDetailPointChoisi(tournee, vueGraphique.getPointLivraisonChoisi().getId());
        } else {
            vueTextuelle.afficheDetailPointChoisi(null);

        }

    }


}
