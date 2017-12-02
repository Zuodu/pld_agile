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
import javax.swing.JTextField;

public class FenetrePrincipale extends JFrame implements Observer {
	public static final String CHARGER_PLAN = "Charger Plan";
	public static final String CHARGER_LIVRAISONS = "Charger Livraisons";
	public static final String CALCULER_TOURNEE = "Calculer Tournee";
	public final int BUTTON_WIDTH=200;
	public final int BUTTON_HEIGHT=40;

	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;

	private ButtonListener buttonListener;
	VuePlan vuePlan;
	private Controleur controleur;
	private Plan plan;
	private Tournee tournee;
	private JPanel infoPanel;
	private JTextField infoText;
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
		setBounds(0, 0, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		vuePlan = new VuePlan();
		contentPane.add(vuePlan);
		
		JPanel buttonPanel = new JPanel();


		buttonListener = new ButtonListener(controleur);

		buttonChargerPlan = new JButton(CHARGER_PLAN);
		//buttonChargerPlan.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerPlan.addActionListener(buttonListener);
		buttonChargerPlan.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
		buttonPanel.add(buttonChargerPlan);

		buttonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
	//	buttonChargerLivraisons.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerLivraisons.addActionListener(buttonListener);
		buttonChargerLivraisons.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);

		buttonPanel.add(buttonChargerLivraisons);

		buttonCalculerTournee = new JButton(CALCULER_TOURNEE);
		//buttonCalculerTournee.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCalculerTournee.addActionListener(buttonListener);

		buttonCalculerTournee.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);

		buttonPanel.add(buttonCalculerTournee);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		infoPanel = new JPanel();
		infoPanel.setSize(200,800);

		contentPane.add(infoPanel, BorderLayout.EAST);
		
		infoText = new JTextField();
		infoText.setText("hello world");
		infoPanel.add(infoText);
		infoText.setColumns(10);
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
