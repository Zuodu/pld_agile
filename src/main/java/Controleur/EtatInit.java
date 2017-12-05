package Controleur;

import ChargeurXML.ChargeurPlan;
import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

public class EtatInit extends EtatDefaut{
	
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
}
