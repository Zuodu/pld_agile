package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by qifan on 2017/11/20.
 */
public class ButtonChargerLivraisonsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc=new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            Controleur.getInstance().chargerLivraison(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
        }


    }

    public ButtonChargerLivraisonsListener() {
    }
}


