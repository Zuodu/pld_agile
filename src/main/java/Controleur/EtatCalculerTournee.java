package Controleur;

import ChargeurXML.ChargeurLivraison;
import Modele.*;

import javax.swing.*;

public class EtatCalculerTournee extends EtatDefaut{
	
	public void cdeAjouterLivraison(PointLivraison pointLivraison, Tournee tournee,LstDeCde lstDeCde)
	{
		CdeAjout commandeAjout = new CdeAjout(pointLivraison, tournee);
		commandeAjout.doCde();
		lstDeCde.ajouterCommande(commandeAjout);
	}
	
	public void cdeSupprimerLivraison(PointLivraison pointLivraison,Tournee tournee,LstDeCde lstDeCde)
	{
		CdeSupprimer commandeSupprimer = new CdeSupprimer(pointLivraison,tournee);
		commandeSupprimer.doCde();
		lstDeCde.ajouterCommande(commandeSupprimer);
	}
	
	public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, double debutPlage, double finPlage,LstDeCde lstDeCde)
	{
		CdeModifierHoraire commandeModifier = new CdeModifierHoraire(pointLivraison,tournee,debutPlage,finPlage);
		commandeModifier.doCde();
		lstDeCde.ajouterCommande(commandeModifier);
	}

	/**
	 * M?thode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
	 * @param filePath Le chemin d'acc?s au fichier xml
	 */
	public boolean chargerLivraison (String filePath,Tournee tournee){
		if(ChargeurLivraison.getInstance().parse(tournee, filePath))
			return true;
		JOptionPane.showMessageDialog(null,"Echec du chargement, réessayez");
		return false;
	}
}
