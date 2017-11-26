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

public class FenetrePrincipale extends JFrame implements Observer {

	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;
	private ButtonChargerLivraisonsListener buttonChargerLivraisonsListener;
	private ButtonChargerPlanListener buttonChargerPlanListener;
	private ButtonCalculerTourneeListener buttonCalculerTourneeListener;
	VuePlan vuePlan;
	private Controleur controleur;
	private Plan plan;
	private Tournee tournee;
	/**
	 * Create the frame.
	 */
	public FenetrePrincipale(Plan plan, Tournee tournee, Controleur controleur) {
		this.plan = plan;
		plan.addObserver(this);
		this.tournee = tournee;
		tournee.addObserver(this);

		this.controleur = controleur;

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		vuePlan = new VuePlan();
		contentPane.add(vuePlan);
		
		JPanel panel = new JPanel();

		buttonChargerLivraisonsListener = new ButtonChargerLivraisonsListener(controleur);
		buttonChargerPlanListener = new ButtonChargerPlanListener(controleur);
		buttonCalculerTourneeListener = new ButtonCalculerTourneeListener(controleur);
		
		buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerPlan.addActionListener(buttonChargerPlanListener);
		panel.add(buttonChargerPlan);
		
		buttonChargerLivraisons = new JButton("Charger Livraisons");
		buttonChargerLivraisons.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerLivraisons.addActionListener(buttonChargerLivraisonsListener);
		panel.add(buttonChargerLivraisons);
		
		buttonCalculerTournee = new JButton("Calculer Tournee");
		buttonCalculerTournee.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCalculerTournee.addActionListener(buttonCalculerTourneeListener);
		panel.add(buttonCalculerTournee);
		contentPane.add(panel, BorderLayout.SOUTH);
	}

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
