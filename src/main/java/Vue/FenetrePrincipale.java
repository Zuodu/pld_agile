package Vue;

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

	private Plan plan;
	private Tournee tournee;
	/**
	 * Create the frame.
	 */
	public FenetrePrincipale(Plan plan, Tournee tournee) {
		this.plan = plan;
		plan.addObserver(this);
		this.tournee = tournee;
		tournee.addObserver(this);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		buttonChargerLivraisonsListener=new ButtonChargerLivraisonsListener();
		buttonChargerPlanListener=new ButtonChargerPlanListener();
		buttonCalculerTourneeListener = new ButtonCalculerTourneeListener();
		
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
	}

	@Override
	public void update(Observable o, Object arg) {
		VuePlan vuePlan = new VuePlan();


			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 600);
			frame.setVisible(true);
			frame.setContentPane(vuePlan);
		vuePlan.addPlan(plan);
		vuePlan.addTournee(tournee);
		}


}
