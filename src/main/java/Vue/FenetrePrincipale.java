package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class FenetrePrincipale extends JFrame implements Observer {

	private JPanel contentPane;
	private JButton buttonChargerLivraisons;
	private JButton buttonChargerPlan;
	private JButton buttonCalculerTournee;
	private ButtonChargerLivraisonsListener buttonChargerLivraisonsListener;
	private ButtonChargerPlanListener buttonChargerPlanListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
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
		panel.add(buttonCalculerTournee);
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
