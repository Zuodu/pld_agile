package Controleur;

import Modele.*;
import Vue.FenetrePrincipale;
/**
 * 
 * @author H4401
 *	Interface des Etats
 */

public interface Etat {
	public boolean chargerPlan (String filePath,Plan plan);
	public boolean chargerLivraison (String filePath, Tournee tournee);
	public void calculerTournee (Plan plan, Tournee tournee) ;
	public void clickedOnPoint(PointLivraison pointLivraison, FenetrePrincipale fenetrePrincipale);
	public boolean rightClickedOnPoint(Noeud noeud,Controleur controleur);
	public void undo(LstDeCde l);
	public void redo(LstDeCde l);
	public void cdeAjouterLivraison(Noeud noeud,Double debut, Double fin, double duree, Tournee tournee,LstDeCde lstDeCde);
	public void cdeSupprimerLivraison(PointLivraison pointLivraison, Tournee tournee,LstDeCde lstDeCde);
	public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, double debutPlage, double finPlage,LstDeCde lstDeCde);
	//M�thodes qui n'avaient pas de corps
	public void afficherPlan() ;
	public void afficherLivraison();
	public void afficherTournee() ;

}
