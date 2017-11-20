package Vue;

import Controleur.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qifan on 2017/11/20.
 */
public class ButtonCalculerTourneeListener implements ActionListener {
    public ButtonCalculerTourneeListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controleur.getInstance().calculerTournee();
    }
}
