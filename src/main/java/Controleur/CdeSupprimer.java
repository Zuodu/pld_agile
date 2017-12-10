package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

/**
 * @author H4401
 * Classe de Commande de Suppression d'un point de livraison
 */
public class CdeSupprimer implements Commande {
    Tournee tournee;
    Tournee oldTournee;
    Tournee tourneeRedo;
    PointLivraison pointLivraison;

    /**
     * Constructeur prenant en paramètre le point à supprimer et la tournée concernée
     * @param pointLivraison
     * @param tournee
     */
    public CdeSupprimer(PointLivraison pointLivraison, Tournee tournee) {
        this.tournee = tournee;
        this.pointLivraison = pointLivraison;
        oldTournee = new Tournee();
        oldTournee.clone(tournee,oldTournee);
        tourneeRedo = new Tournee();
    }

    /**
     * Méthode permettant la suppression (ou le "Redo")
     */
    public void doCde() {
        if(tourneeRedo.getListePointLivraisons().size()==0) {
            boolean res = tournee.supprimerPoint(pointLivraison.getId());
        }
        else{
            tournee.clone(tourneeRedo,tournee);
            tournee.SignalerFinDajoutPointsLivraisons();
        }
    }

    /**
     * Méthode permettant de revenir à l'état d'avant modification (Undo)
     */
    public void undoCde() {
        tourneeRedo.clone(tournee,tourneeRedo);
        tournee.clone(oldTournee,tournee);
        tournee.SignalerFinDajoutPointsLivraisons();
    }
}
