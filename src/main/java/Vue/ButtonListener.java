package Vue;

import Controleur.Controleur;
import Modele.PointLivraison;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author H4401
 *         Classe implements ActionListener prmettant aux boutons de l'interface de communiquer avec le logiciel
 */
public class ButtonListener implements ActionListener {
    FenetrePrincipale fenetrePrincipale;
    Controleur controleur;

    public ButtonListener(Controleur controleur, FenetrePrincipale fenetrePrincipale) {
        this.controleur = controleur;
        this.fenetrePrincipale = fenetrePrincipale;
    }

    /**
     * Méthode appelant les méthodes adéquates après avoir appuyé sur un bouton de l'interface
     *
     * @param e :l'evenement déclenchant l'écouteur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals(FenetrePrincipale.CHARGER_PLAN)) {
            JFileChooser jfc = FileChooserFactory("PlanLyon");
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                controleur.chargerPlan(selectedFile.getAbsolutePath());
                System.out.println(selectedFile.getAbsolutePath());
            }

        } else if (s.equals(FenetrePrincipale.CHARGER_LIVRAISONS)) {
            JFileChooser jfc = FileChooserFactory("PlanLyon");
            int returnValue = jfc.showOpenDialog(null);
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
                        break;
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
            String plageDebut = fenetrePrincipale.getVueTextuelle().getInfoPlageDebut();
            String plageFin = fenetrePrincipale.getVueTextuelle().getInfoPlageFin();
            Double debut = null;
            Double fin = null;
            try {
                if (!plageDebut.equals("")) {
                    debut = PlageParser(plageDebut);
                    if(debut == null) return;
                }
                if (!plageFin.equals("")) {
                    fin = PlageParser(plageFin);
                    if(fin == null) return;
                }
            } catch (StringIndexOutOfBoundsException se) {
                JOptionPane.showMessageDialog(null, "Verifie la plage saisie");
                return;
            }
            controleur.modifierPlageHoraire(fenetrePrincipale.getVueGraphique().getPointLivraisonChoisi(), debut, fin);
        } else if (s.equals(VueTextuelle.UNDO)) {
            controleur.undo();
        } else if (s.equals(VueTextuelle.REDO)) {
            controleur.redo();
        }

    }

    /**
     * Construit un objet JFileChooser pour l'application
     * @param directoryName nom du répertoire par défaut
     * @return un JFileChooser paramétré pour l'application
     */
    private JFileChooser FileChooserFactory(String directoryName){
        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        FileSystemView fsv = FileSystemView.getFileSystemView();
        jfc.setCurrentDirectory(new File(fsv.getDefaultDirectory().toString() + File.separator + directoryName));
        jfc.setFileFilter(filter);
        return jfc;
    }

    /**
     * Converti une plage format string ("xx:xx:xx") en un objet Double
     * @param plage le String contenant le temps choisi
     * @return un Double représentant le temps, ou null si l'opération a échouée, en cas de mauvaise saisie par exemple
     */
    private Double PlageParser(String plage){
        Double h = Double.parseDouble(plage.substring(0, plage.indexOf(':')));
        Double m = Double.parseDouble(plage.substring(plage.indexOf(':') + 1, plage.indexOf(':', plage.indexOf(':') + 1)));
        Double sec = Double.parseDouble(plage.substring(plage.lastIndexOf(':') + 1, plage.length()));
        if (h >= 24 || h < 0) {
            JOptionPane.showMessageDialog(null, "Verifie la plage saisie");
            return null;
        }
        if (m >= 60 || m < 0) {
            JOptionPane.showMessageDialog(null, "Verifie la plage saisie");
            return null;
        }
        if (sec >= 60 || sec < 0) {
            JOptionPane.showMessageDialog(null, "Verifie la plage saisie");
            return null;
        }
        return Double.parseDouble(plage.substring(0, plage.indexOf(':'))) * 3600
                + Double.parseDouble(plage.substring(plage.indexOf(':') + 1, plage.indexOf(':', plage.indexOf(':') + 1))) * 60
                + Double.parseDouble(plage.substring(plage.lastIndexOf(':') + 1, plage.length()));
    }
}
