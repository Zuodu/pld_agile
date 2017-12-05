package Controleur;

import ChargeurXML.ChargeurLivraison;
import Modele.*;

import javax.swing.*;

public class EtatChargerPlan extends EtatDefaut{
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
