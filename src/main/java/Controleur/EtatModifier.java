package Controleur;

import Modele.PointLivraison;
import Modele.Tournee;

public class EtatModifier extends EtatDefaut{
	
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
