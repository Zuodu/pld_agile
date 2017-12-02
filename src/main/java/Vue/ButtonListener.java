package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author H4401
 * Classe implements ActionListener prmettant aux boutons de l'interface de communiquer avec le logiciel
 */
public class ButtonListener implements ActionListener {
    Controleur controleur;

    public ButtonListener(Controleur controleur) {
        this.controleur = controleur;
    }

    /**
     * Méthode appelant les méthodes adéquates après avoir appuyé sur un bouton de l'interface
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals(FenetrePrincipale.CHARGER_PLAN)) {
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                controleur.chargerPlan(selectedFile.getAbsolutePath());
                System.out.println(selectedFile.getAbsolutePath());
            }

        } else if (s.equals(FenetrePrincipale.CHARGER_LIVRAISONS)) {
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                controleur.chargerLivraison(selectedFile.getAbsolutePath());
                System.out.println(selectedFile.getAbsolutePath());
            }


        } else if (s.equals(FenetrePrincipale.CALCULER_TOURNEE)) {
            controleur.calculerTournee();
        }

    }
}
