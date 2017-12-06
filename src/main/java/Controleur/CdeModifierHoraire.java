package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

import javax.swing.*;

public class CdeModifierHoraire implements Commande {
    Tournee tournee;
    PointLivraison pointLivraison;
    Double debutPlage;
    Double finPlage;
    Tournee oldTournee;
    Tournee tourneeRedo;


    public CdeModifierHoraire(PointLivraison newPointLivraison, Tournee newTournee, Double newDebutPlage, Double newFinPlage) {
        tournee = newTournee;
        pointLivraison = newPointLivraison;
        debutPlage = newDebutPlage;
        finPlage = newFinPlage;
        oldTournee = new Tournee();
        oldTournee.clone(tournee,oldTournee);
        tourneeRedo = new Tournee();
    }

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
	public void undoCde()
	{
        tourneeRedo.clone(tournee,tourneeRedo);
        tournee.clone(oldTournee,tournee);
	}
}
