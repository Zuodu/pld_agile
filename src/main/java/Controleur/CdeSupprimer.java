package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

public class CdeSupprimer implements Commande {
	Tournee tournee;
	PointLivraison pointLivraison;

	public CdeSupprimer(PointLivraison newPointLivraison, Tournee newTournee) {
		tournee = newTournee;
		pointLivraison = newPointLivraison;
	}

	public void doCde() 
	{
		
	}
	public void undoCde()
	{
		
	}
}
