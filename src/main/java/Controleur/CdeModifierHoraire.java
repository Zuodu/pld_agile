package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

import javax.swing.*;

/**
 * @author H4401
 * Classe de Commande de modification d'horaire
 */
public class CdeModifierHoraire implements Commande {
    Tournee tournee;
    PointLivraison pointLivraison;
    Double debutPlage;
    Double finPlage;
    Tournee oldTournee;
    Tournee tourneeRedo;

    /**
     * Constructeur prenant en paramètre toutes les informations nécessaires à la modification
     * @param newPointLivraison :point de livraison a modifier
     * @param newTournee        :la tournee a modifier
     * @param newDebutPlage     :le nouveau debut de plage horaire
     * @param newFinPlage       :le nouveau fin de plage horaire
     */
    public CdeModifierHoraire(PointLivraison newPointLivraison, Tournee newTournee, Double newDebutPlage, Double newFinPlage) {
        tournee = newTournee;
        pointLivraison = newPointLivraison;
        debutPlage = newDebutPlage;
        finPlage = newFinPlage;
        oldTournee = new Tournee();
        oldTournee.clone(tournee,oldTournee);
        tourneeRedo = new Tournee();
    }

    /**
     * Méthode permettant la modification (ou le "Redo")
     */
	public void doCde()
	{
        if(tourneeRedo.getListePointLivraisons().size()==0) {
            if (!tournee.updateHoraire(pointLivraison.getId(),debutPlage,finPlage))
            {
                JOptionPane.showMessageDialog(null, "Modification echouee");
            } else {
                JOptionPane.showMessageDialog(null, "Modification reussie");
            }
        }
        else
        {
            tournee.clone(tourneeRedo,tournee);
        }

	}

    /**
     * Méthode permettant de revenir à l'état d'avant modification (Undo)
     */
	public void undoCde()
	{
        tourneeRedo.clone(tournee,tourneeRedo);
        tournee.clone(oldTournee,tournee);
	}
}
