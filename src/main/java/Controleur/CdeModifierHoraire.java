package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

import javax.swing.*;

public class CdeModifierHoraire implements Commande {
    Tournee tournee;
    PointLivraison pointLivraison;
    double debutPlage;
    double finPlage;
    double ancientDebut;
    double ancientFin;

    public CdeModifierHoraire(PointLivraison newPointLivraison, Tournee newTournee, double newDebutPlage, double newFinPlage) {
        tournee = newTournee;
        pointLivraison = newPointLivraison;
        debutPlage = newDebutPlage;
        finPlage = newFinPlage;
        //oldTournee = new Tournee(newTournee);
    }

	public void doCde()
	{
	    try {
            ancientDebut = pointLivraison.getDebutPlage();
            ancientFin = pointLivraison.getFinPlage();
        } catch (NullPointerException npe) {
        }
        if (!tournee.updateHoraire(pointLivraison,debutPlage,finPlage))
        {
            JOptionPane.showMessageDialog(null, "Modification echouee");
        } else {
            JOptionPane.showMessageDialog(null, "Modification reussie");
        }
	}
	public void undoCde()
	{
        if (!tournee.updateHoraire(pointLivraison,ancientDebut,ancientFin))
        {
            JOptionPane.showMessageDialog(null, "Undo echouee");
        } else {
            JOptionPane.showMessageDialog(null, "Undo reussi");
        }
	}
}
