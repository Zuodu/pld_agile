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
	
	public void cdeSupprimerLivraison(PointLivraison pointLivraison,Tournee tournee)
	{
		CdeSupprimer commandeSupprimer = new CdeSupprimer(pointLivraison,tournee);
		commandeSupprimer.doCde();
	}
	
	public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, double debutPlage, double finPlage)
	{
		CdeModifierHoraire commandeModifier = new CdeModifierHoraire(pointLivraison,tournee,debutPlage,finPlage);
		commandeModifier.doCde();
	}
}
