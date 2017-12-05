package Controleur;

import Algo.AbstractGraphe;
import ChargeurXML.ChargeurLivraison;
import Modele.Plan;
import Modele.Tournee;

import javax.swing.*;

public class EtatChargerLivraison extends EtatDefaut{
	/**
	 * Le undo n'est pas possible ici
	 * @param l
	 */
	public void undo(LstDeCde l)
	{
	}
	
	/**
	 * Le redo n'est pas possible ici
	 * @param l
	 */
	public void redo(LstDeCde l)
	{
	}
	
	/**
     * M�thode lan�ant le calcul de la tourn�e
     */
    public void calculerTournee (Plan plan, Tournee tournee) {
        AbstractGraphe abstractGraphe = new AbstractGraphe(plan, tournee);
        abstractGraphe.chercheSolution();
        tournee.SignalerFinDajoutPointsLivraisons();
       // System.out.println(abstractGraphe.getTournee());

    }

	/**
	 * M�thode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
	 * @param filePath Le chemin d'acc�s au fichier xml
	 */
	public boolean chargerLivraison (String filePath,Tournee tournee){
		if(ChargeurLivraison.getInstance().parse(tournee, filePath))
			return true;
		JOptionPane.showMessageDialog(null,"Echec du chargement, réessayez");
		return false;
	}
}
