package Controleur;

import Modele.*;

public class CdeAjout implements Commande {
	Tournee tournee;
	PointLivraison pointLivraison;
	
	public CdeAjout (PointLivraison newPointLivraison,Tournee newTournee)
	{
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
