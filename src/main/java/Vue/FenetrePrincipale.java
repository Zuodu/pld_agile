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
	public final int BUTTON_WIDTH=200;
	public final int BUTTON_HEIGHT=40;

	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;

	private ButtonListener buttonListener;
	private MouseListener mouseListener;
	Vue.VueGraphique vueGraphique;
	private Controleur controleur;
	private Plan plan;
	private Tournee tournee;
	private JPanel infoPanel;
	private JPanel buttonPanel;
	private JTextField infoText;

	public JTextField getInfoText() {
		return infoText;
	}

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
		setBounds(0, 0, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		vueGraphique = new Vue.VueGraphique();
		contentPane.add(vueGraphique);

		buttonPanel = new JPanel();


		buttonListener = new ButtonListener(controleur);

		buttonChargerPlan = new JButton(CHARGER_PLAN);
		//buttonChargerPlan.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerPlan.addActionListener(buttonListener);
		//buttonChargerPlan.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
		buttonPanel.add(buttonChargerPlan);

		buttonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
	//	buttonChargerLivraisons.setHorizontalAlignment(SwingConstants.LEFT);
		buttonChargerLivraisons.addActionListener(buttonListener);
		buttonChargerLivraisons.setLocation(0, 200);
		//buttonChargerLivraisons.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);

		buttonPanel.add(buttonChargerLivraisons);

		buttonCalculerTournee = new JButton(CALCULER_TOURNEE);
		//buttonCalculerTournee.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCalculerTournee.addActionListener(buttonListener);
		buttonCalculerTournee.setLocation(0, 400);

		//buttonCalculerTournee.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);

		buttonPanel.add(buttonCalculerTournee);

		contentPane.add(buttonPanel, BorderLayout.EAST);
		
		infoPanel = new JPanel();


		infoText = new JTextField();
		infoText.setText("hello world");
		//	infoPanel.add(infoText);
		buttonPanel.add(infoText);
		infoText.setColumns(10);


		mouseListener = new MouseListener(vueGraphique, controleur);
		vueGraphique.addMouseListener(mouseListener);

		contentPane.add(infoPanel, BorderLayout.SOUTH);
	}

	@Override
	public void update(Observable o, Object arg) {

/**

 JFrame frame = new JFrame();
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setBounds(100, 100, 800, 600);
 frame.setVisible(true);
 frame.setContentPane(vueGraphique);
 if (o instanceof Plan)
 vueGraphique.addPlan(plan);
 else if (o instanceof Tournee)
 vueGraphique.addTournee(tournee);
 }
 **/
		if (o instanceof Plan)
			vueGraphique.addPlan(plan);
		else if (o instanceof Tournee)
			vueGraphique.addTournee(tournee);
	}


}
