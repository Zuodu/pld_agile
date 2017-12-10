package Controleur;

import Algo.TSPFactory;
import ChargeurXML.ChargeurLivraison;
import Modele.Plan;
import Modele.Tournee;

import javax.swing.*;

/**
 * @author H4401
 * Classe Etat avec les actions possibles après avoir chargé des livraisons
 */

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
     * Méthode lan�ant le calcul de la tourn�e
     */
    public void calculerTournee (Plan plan, Tournee tournee) {
        TSPFactory TSPFactory = new TSPFactory(plan, tournee);
        TSPFactory.chercheSolution();
        tournee.SignalerFinDajoutPointsLivraisons();
       // System.out.println(TSPFactory.getTournee());

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
