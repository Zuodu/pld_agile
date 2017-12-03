package Controleur;

import Modele.*;
import Vue.FenetrePrincipale;
/**
 * 
 * @author H4401
 *	Interface des Etats
 */

public interface Etat {
	public void chargerPlan (String filePath,Plan plan);
	public void chargerLivraison (String filePath, Tournee tournee);
	public void calculerTournee (Plan plan, Tournee tournee) ;
	public void clickedOnPoint(PointLivraison pointLivraison, FenetrePrincipale fenetrePrincipale);
	public void undo(LstDeCde l);
	public void redo(LstDeCde l);
	public void cdeAjouterLivraison(PointLivraison pointL);
	public void cdeSupprimerLivraison(PointLivraison pointL);
	public void cdeModifierPlageHoraire(PointLivraison pointL, double debutPlage, double finPlage);
	//M�thodes qui n'avaient pas de corps
	public void afficherPlan() ;
	public void afficherLivraison();
	public void afficherTournee() ;
}
