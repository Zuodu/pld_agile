package Vue;

import Controleur.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qifan on 2017/11/20.
 */
public class ButtonCalculerTourneeListener implements ActionListener {
    Controleur controleur;

    public ButtonCalculerTourneeListener(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controleur.calculerTournee();
    }
}
