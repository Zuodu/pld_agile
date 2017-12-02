package Vue;

import Controleur.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by qifan on 2017/11/20.
 */
public class ButtonListener implements ActionListener {
    Controleur controleur;

    public ButtonListener(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals(FenetrePrincipale.CHARGER_PLAN)) {
            JFileChooser jfc = new JFileChooser();
            FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
            //得到桌面路径
            jfc.setCurrentDirectory(new File(fsv.getDefaultDirectory().toString()+File.separator+"PlanLyon"));
            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                controleur.chargerPlan(selectedFile.getAbsolutePath());
                System.out.println(selectedFile.getAbsolutePath());
            }

        } else if (s.equals(FenetrePrincipale.CHARGER_LIVRAISONS)) {
            JFileChooser jfc = new JFileChooser();
            FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
            //得到桌面路径
            jfc.setCurrentDirectory(new File(fsv.getDefaultDirectory().toString()+File.separator+"PlanLyon"));
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
