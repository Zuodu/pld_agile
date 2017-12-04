package Controleur;

import Modele.*;

public class CdeAjout implements Commande {
	Tournee tournee;
	Tournee oldTournee;
	PointLivraison pointLivraison;
	
	public CdeAjout (PointLivraison newPointLivraison,Tournee newTournee)
	{
		tournee = newTournee;
		pointLivraison = newPointLivraison;
		oldTournee = new Tournee(newTournee);
	}
	
	public void doCde()
	{
		
	}
	public void undoCde()
	{
		tournee = oldTournee;
	}

}
