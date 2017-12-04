package Controleur;

import Algo.AbstractGraphe;
import Modele.Plan;
import Modele.Tournee;

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
}
