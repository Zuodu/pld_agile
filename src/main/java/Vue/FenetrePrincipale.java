package Vue;

import Controleur.Controleur;
import Modele.Plan;
import Modele.Tournee;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author H4401
 *	Classe de la fen�tre principale de l'application
 */
public class FenetrePrincipale extends JFrame implements Observer {
	public static final String CHARGER_PLAN = "Charger Plan";
	public static final String CHARGER_LIVRAISONS = "Charger Livraisons";
	public static final String CALCULER_TOURNEE = "Calculer Tournee";

	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;

	private ButtonListener buttonListener;
	private MouseListener mouseListener;
	VuePlan vuePlan;
	private Controleur controleur;
	private Plan plan;
	private Tournee tournee;
	
	/**
	 * Cr�e la fenetre principale
	 * @param plan Le plan charg� ou non
	 * @param tournee La tourn�e, charg�e ou non
	 * @param controleur Le contr�leur
	 */
	public FenetrePrincipale(Plan plan, Tournee tournee, Controleur controleur) {
		this.plan = plan;
		plan.addObserver(this);
		this.tournee = tournee;
		tournee.addObserver(this);

		this.controleur = controleur;

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 1280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		vuePlan = new VuePlan();
		contentPane.add(vuePlan);
		
		JPanel panel = new JPanel();


		buttonListener = new ButtonListener(controleur);

		buttonChargerPlan = new JButton(CHARGER_PLAN);
		buttonChargerPlan.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerPlan.addActionListener(buttonListener);
		panel.add(buttonChargerPlan);

		buttonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
		buttonChargerLivraisons.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerLivraisons.addActionListener(buttonListener);
		panel.add(buttonChargerLivraisons);

		buttonCalculerTournee = new JButton(CALCULER_TOURNEE);
		buttonCalculerTournee.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCalculerTournee.addActionListener(buttonListener);
		panel.add(buttonCalculerTournee);
		contentPane.add(panel, BorderLayout.SOUTH);

		mouseListener = new MouseListener(vuePlan, controleur);
		vuePlan.addMouseListener(mouseListener);
	}

	public VuePlan getVuePlan() {
		return vuePlan;
	}

	@Override
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getButtonChargerLivraisons() {
		return buttonChargerLivraisons;
	}

	public JButton getButtonChargerPlan() {
		return buttonChargerPlan;
	}

	public JButton getButtonCalculerTournee() {
		return buttonCalculerTournee;
	}

	public ButtonListener getButtonListener() {
		return buttonListener;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public Controleur getControleur() {
		return controleur;
	}

	public Plan getPlan() {
		return plan;
	}

	public Tournee getTournee() {
		return tournee;
	}

	/**
	 * Met � jour lorsque que le plan et la tourn�e sont charg�s
	 * @param o
	 * @param arg 
	 */
	@Override
	public void update(Observable o, Object arg) {

/**

 JFrame frame = new JFrame();
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setBounds(100, 100, 800, 600);
 frame.setVisible(true);
 frame.setContentPane(vuePlan);
 if (o instanceof Plan)
 vuePlan.addPlan(plan);
 else if (o instanceof Tournee)
 vuePlan.addTournee(tournee);
 }
 **/
		if (o instanceof Plan)
			vuePlan.addPlan(plan);
		else if (o instanceof Tournee)
			vuePlan.addTournee(tournee);
	}


}
