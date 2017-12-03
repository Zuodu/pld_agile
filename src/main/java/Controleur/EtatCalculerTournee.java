package Controleur;

import Modele.*;

public class EtatCalculerTournee extends EtatDefaut{
	/**
	 * Le undo n'est pas possible ici
	 * @param l
	 */
	public void undo(LstDeCde l)
	{
	}
	
	public void cdeAjouterLivraison(PointLivraison pointLivraison, Tournee tournee)
	{
		CdeAjout commandeAjout = new CdeAjout(pointLivraison, tournee);
		commandeAjout.doCde();
	}
	
	public void cdeSupprimerLivraison(PointLivraison pointLivraison)
	{
	}
	
	public void cdeModifierPlageHoraire(PointLivraison pointLivraison, double debutPlage, double finPlage)
	{
	}
}
