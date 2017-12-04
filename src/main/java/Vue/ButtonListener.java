package Vue;

import Controleur.Controleur;
import Modele.Plan;
import Modele.PointLivraison;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author H4401
 * Classe implements ActionListener prmettant aux boutons de l'interface de communiquer avec le logiciel
 */
public class ButtonListener implements ActionListener {
    FenetrePrincipale fenetrePrincipale;
    Controleur controleur;

    public ButtonListener(Controleur controleur, FenetrePrincipale fenetrePrincipale) {
        this.controleur = controleur;
        this.fenetrePrincipale = fenetrePrincipale;
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
        } else if (s.equals(VueTextuelle.PRECEDENT_POINT)) {
            PointLivraison pointLivraison = fenetrePrincipale.getVueGraphique().getPointLivraisonChoisi();
            if (pointLivraison != null) {
                Iterator<PointLivraison> iterator = controleur.getTournee().getListePointLivraisons().iterator();
                PointLivraison p2 = null;
                while (iterator.hasNext()) {
                    PointLivraison p = iterator.next();
                    if (p.equals(pointLivraison)) {
                        controleur.clickedOnPoint(p2);
                    }
                    p2 = p;
                }
            }

        } else if (s.equals(VueTextuelle.PROCHAIN_POINT)) {
            PointLivraison pointLivraison = fenetrePrincipale.getVueGraphique().getPointLivraisonChoisi();
            if (pointLivraison != null) {
                Iterator<PointLivraison> iterator = controleur.getTournee().getListePointLivraisons().iterator();
                while (iterator.hasNext()) {
                    PointLivraison p = iterator.next();
                    if (p.equals(pointLivraison)) {
                        controleur.clickedOnPoint(iterator.next());
                    }
                }
            }
        } else if (s.equals(FenetrePrincipale.GENERER_FEUILLE)) {
            JFileChooser jfcd = new JFileChooser();
            jfcd.setDialogTitle("Selection du répertoire de sortie");
            jfcd.setCurrentDirectory(new File("."));
            jfcd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // int returnValue = jfc.showSaveDialog(null);
            if (jfcd.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfcd.getSelectedFile();
                try {
                    controleur.sortirFeuilleDeRoute(selectedFile.getAbsolutePath());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println(selectedFile.getAbsolutePath());
            }
        } else if (s.equals(VueTextuelle.SUPPRIMER_POINT)) {
            controleur.supprimerPoint(fenetrePrincipale.getVueGraphique().getPointLivraisonChoisi());
        } else if (s.equals(VueTextuelle.MODIFIER_POINT)) {
            String plageDebut=fenetrePrincipale.getVueTextuelle().getInfoPlageDebut();
            String plageFin=fenetrePrincipale.getVueTextuelle().getInfoPlageFin();
            Double debut=null;
            Double fin=null;
            if (!plageDebut.equals("")) {
                debut = Double.parseDouble(plageDebut);
            }
            if (!plageFin.equals("")) {
                fin = Double.parseDouble(plageFin);
            }
            controleur.modifierPlageHoraire(fenetrePrincipale.getVueGraphique().getPointLivraisonChoisi(),debut,fin);
        } else if (s.equals(VueTextuelle.UNDO)) {
            controleur.undo();
        } else if (s.equals(VueTextuelle.REDO)) {
            controleur.redo();
        }

    }
}
