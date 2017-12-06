package Controleur;

import ChargeurXML.ChargeurLivraison;
import Modele.Noeud;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Tournee;
import Vue.VueAjouterPoint;

import javax.swing.*;

public class EtatPointSelectionne extends EtatDefaut {
    public void cdeAjouterLivraison(Noeud noeud, Double debut, Double fin, double duree, Tournee tournee, LstDeCde lstDeCde) {

        CdeAjout commandeAjout = new CdeAjout(noeud,debut,fin,duree, tournee,lstDeCde);
        commandeAjout.doCde();
        lstDeCde.ajouterCommande(commandeAjout);
    }

    public void cdeSupprimerLivraison(PointLivraison pointLivraison, Tournee tournee, LstDeCde lstDeCde)
    {
        CdeSupprimer commandeSupprimer = new CdeSupprimer(pointLivraison,tournee);
        commandeSupprimer.doCde();
        lstDeCde.ajouterCommande(commandeSupprimer);
    }

    public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, Double debutPlage,Double finPlage,LstDeCde lstDeCde)
    {
        CdeModifierHoraire commandeModifier = new CdeModifierHoraire(pointLivraison,tournee,debutPlage,finPlage);
        commandeModifier.doCde();
        lstDeCde.ajouterCommande(commandeModifier);
    }

    /**
     * Méthode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
     * @param filePath Le chemin d'accès au fichier xml
     */
    public boolean chargerLivraison (String filePath,Tournee tournee){
        if(ChargeurLivraison.getInstance().parse(tournee, filePath))
            return true;
        JOptionPane.showMessageDialog(null,"Echec du chargement, réessayez");
        return false;
    }

    public boolean rightClickedOnPoint (Noeud noeud,Controleur controleur) {
        VueAjouterPoint vueAjouterPoint=new VueAjouterPoint(noeud,controleur);
        return false;
    }
    public void calculerTournee (Plan plan, Tournee tournee) {
        JOptionPane.showMessageDialog(null,"La tournée est déjà calculée");

    }
}
