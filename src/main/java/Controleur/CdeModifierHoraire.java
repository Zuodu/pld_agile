package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

import javax.swing.*;

public class CdeModifierHoraire implements Commande {
    Tournee tournee;
    Tournee oldTournee;
    PointLivraison pointLivraison;
    double debutPlage;
    double finPlage;

    public CdeModifierHoraire(PointLivraison newPointLivraison, Tournee newTournee, double newDebutPlage, double newFinPlage) {
        tournee = newTournee;
        pointLivraison = newPointLivraison;
        debutPlage = newDebutPlage;
        finPlage = newFinPlage;
        //oldTournee = new Tournee(newTournee);
    }

	public void doCde()
	{
        if (!tournee.updateHoraire(pointLivraison,debutPlage,finPlage))
        {
            JOptionPane.showMessageDialog(null, "Modification non reussie");
        }
	}
	public void undoCde()
	{
		tournee = oldTournee;
	}
}
