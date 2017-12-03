package Vue;

import Controleur.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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
     * M�thode appelant les m�thodes ad�quates apr�s avoir appuy� sur un bouton de l'interface
     * @param e
     */
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

        } else if (s.equals((FenetrePrincipale.GENERER_FEUILLE))) {
            JFileChooser jfcd = new JFileChooser();
            jfcd.setCurrentDirectory(new java.io.File("."));
            jfcd.setDialogTitle("R�pertoire de sortie de feuille de route :");
            jfcd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (jfcd.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfcd.getSelectedFile();
                controleur.sortirFeuilleDeRoute(selectedFile.getAbsolutePath());
                System.out.println(selectedFile.getAbsolutePath());
            }
        }
    }
}
