package Controleur;

import Algo.AbstractGraphe;
import ChargeurXML.ChargeurLivraison;
import Modele.*;
import Vue.VueAjouterPoint;

import javax.swing.*;

public class EtatCalculerTournee extends EtatDefaut{

	public void cdeAjouterLivraison(Noeud noeud,Double debut, Double fin, double duree, Tournee tournee,LstDeCde lstDeCde) {

		CdeAjout commandeAjout = new CdeAjout(noeud,debut,fin,duree, tournee,lstDeCde);
		try {
			commandeAjout.doCde();
			lstDeCde.ajouterCommande(commandeAjout);
		}catch(NullPointerException ne)
		{

		}
	}

	public void cdeSupprimerLivraison(PointLivraison pointLivraison,Tournee tournee,LstDeCde lstDeCde)
	{
		CdeSupprimer commandeSupprimer = new CdeSupprimer(pointLivraison,tournee);
		try{
			commandeSupprimer.doCde();
			lstDeCde.ajouterCommande(commandeSupprimer);
		}catch(NullPointerException ne) {}
	}

	public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, Double debutPlage,Double finPlage,LstDeCde lstDeCde)
	{
		CdeModifierHoraire commandeModifier = new CdeModifierHoraire(pointLivraison,tournee,debutPlage,finPlage);
		try{
			commandeModifier.doCde();
			lstDeCde.ajouterCommande(commandeModifier);
		}catch(NullPointerException ne) {}
	}

	/**
	 * Méthode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
	 * @param filePath Le chemin d'accès au fichier xml
	 */
	public boolean chargerLivraison (String filePath,Tournee tournee){
		if(ChargeurLivraison.getInstance().parse(tournee, filePath))
			return true;
		JOptionPane.showMessageDialog(null,"Echec du chargement, réessayez");
		return false;
	}

	public boolean rightClickedOnPoint (Noeud noeud,Controleur controleur) {
		VueAjouterPoint vueAjouterPoint=new VueAjouterPoint(noeud,controleur);
		return false;
	}
	public void calculerTournee (Plan plan, Tournee tournee) {
		JOptionPane.showMessageDialog(null,"La tournée est déjà calculée");

	}

}
