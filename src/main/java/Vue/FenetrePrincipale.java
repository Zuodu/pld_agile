package Vue;

import Controleur.Controleur;
import Modele.Plan;
import Modele.Tournee;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

public class FenetrePrincipale extends JFrame implements Observer {
	public static final String CHARGER_PLAN = "Charger Plan";
	public static final String CHARGER_LIVRAISONS = "Charger Livraisons";
	public static final String CALCULER_TOURNEE = "Calculer Tournee";
	public static final int BUTTON_WIDTH = 200;
	public static final int BUTTON_HEIGHT = 40;
	public static final int BORDER = 50;
	public static final int FENETRE_WIDTH = 1280;
	public static final int FENETRE_LENGTH = 800;
	public static final int BUTTONPANEL_WIDTH = 400;
	public static final int BUTTONPANEL_LENGTH = 400;
	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;

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
	 * Create the frame.
	 */
	public FenetrePrincipale(Plan plan, Tournee tournee, Controleur controleur) {
		setLayout(null);
		this.plan = plan;
		plan.addObserver(this);
		this.tournee = tournee;
		tournee.addObserver(this);

		this.controleur = controleur;

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, FENETRE_WIDTH, FENETRE_LENGTH);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);


		vueGraphique = new Vue.VueGraphique();
		contentPane.add(vueGraphique);
		vueTextuelle = new VueTextuelle();
		contentPane.add(vueTextuelle);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(VueGraphique.VUEPLAN_WIDTH + 100, 10, BUTTONPANEL_WIDTH, BUTTONPANEL_LENGTH);
		buttonListener = new ButtonListener(controleur);

		buttonChargerPlan = new JButton(CHARGER_PLAN);
		buttonChargerPlan.addActionListener(buttonListener);
		buttonChargerPlan.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
		buttonChargerLivraisons.addActionListener(buttonListener);
		buttonChargerLivraisons.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttonCalculerTournee = new JButton(CALCULER_TOURNEE);
		buttonCalculerTournee.addActionListener(buttonListener);
		buttonCalculerTournee.setBounds(0, 100, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttonPanel.add(buttonChargerPlan);
		buttonPanel.add(buttonChargerLivraisons);
		buttonPanel.add(buttonCalculerTournee);

		contentPane.add(buttonPanel);

		mouseListener = new MouseListener(vueGraphique, controleur);
		vueGraphique.addMouseListener(mouseListener);

		repaint();
		contentPane.repaint();

	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Plan)
			vueGraphique.addPlan(plan);
		else if (o instanceof Tournee)
			vueGraphique.addTournee(tournee);
	}


}
