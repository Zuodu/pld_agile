package Vue;

import Controleur.Controleur;
import Modele.Noeud;
import Modele.PointLivraison;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by flavi on 2017/12/5.
 */
public class VueAjouterPoint extends JFrame {
    Controleur controleur;
    private JPanel contentPane;
    private Noeud noeud;
    private PointLivraison pointLivraison;

    private JTextField infoPlageDebut;
    private JTextField infoPlageFin;
    private JTextField infoDuree;

    private JLabel labelPlageDebut;
    private JLabel labelPlageFin;
    private JLabel labelDuree;

    private JButton confirmButton;
    public VueAjouterPoint (Noeud noeud,Controleur controleur) {
        setLayout(null);
        this.noeud=noeud;
        this.controleur=controleur;
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 400);
        contentPane=new JPanel();
        contentPane.setLayout(null);
        this.setContentPane(contentPane);

        infoPlageDebut = new JTextField();
        infoPlageDebut.setBounds(100, 0, 150, 30);
        infoPlageFin = new JTextField();
        infoPlageFin.setBounds(100, 40, 150, 30);
        infoDuree = new JTextField();
        infoDuree.setBounds(100, 80, 150, 30);
        contentPane.add(infoDuree);
        contentPane.add(infoPlageDebut);
        contentPane.add(infoPlageFin);

        labelPlageDebut = new JLabel();
        labelPlageDebut.setText("Plage d�but: ");
        labelPlageDebut.setBounds(0, 0, 100, 30);
        contentPane.add(labelPlageDebut);

        labelPlageFin = new JLabel();
        labelPlageFin.setText("Plage fin: ");
        labelPlageFin.setBounds(0, 40, 100, 30);
        contentPane.add(labelPlageFin);

        labelDuree = new JLabel();
        labelDuree.setText("Dur�e : ");
        labelDuree.setBounds(0, 80, 100, 30);
        contentPane.add(labelDuree);

        confirmButton=new JButton("ajouter");
        confirmButton.setBounds(150,200,100,30);
        contentPane.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plageDebut=infoPlageDebut.getText();
                String plageFin=infoPlageFin.getText();
                String duree=infoDuree.getText();
                Double debut=null;
                Double fin=null;
                double dureeDouble=0;
                try {
                    if (!plageDebut.equals("")) {
                        debut =  Double.parseDouble(plageDebut.substring(0, plageDebut.indexOf(':'))) * 3600
                                + Double.parseDouble(plageDebut.substring(plageDebut.indexOf(':') + 1, plageDebut.indexOf(':', plageDebut.indexOf(':') + 1))) * 60
                                + Double.parseDouble(plageDebut.substring(plageDebut.lastIndexOf(':') + 1, plageDebut.length()));


                    }
                    if (!plageFin.equals("")) {
                        fin = Double.parseDouble(plageFin.substring(0, plageFin.indexOf(':'))) * 3600
                                + Double.parseDouble(plageFin.substring(plageFin.indexOf(':') + 1, plageFin.indexOf(':', plageFin.indexOf(':') + 1))) * 60
                                + Double.parseDouble(plageFin.substring(plageFin.lastIndexOf(':') + 1, plageFin.length()));
                    }
                    if (!duree.equals("")) {
                        dureeDouble=Double.parseDouble(duree);
                    }
                }
                catch (StringIndexOutOfBoundsException se) {
                    JOptionPane.showMessageDialog(null, "Verifie la plage saisie");
                    return;
                }
                    controleur.addPointLivraison(noeud,debut,fin,dureeDouble);
                System.out.println("want to add point " );
                System.out.println(noeud);
                System.out.println(debut+" "+fin+" "+duree);
            }
        });



    }
}