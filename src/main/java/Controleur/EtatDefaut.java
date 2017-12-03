package Controleur;

import Modele.*;
import Vue.FenetrePrincipale;

public class EtatDefaut implements Etat {
    public void chargerPlan(String filePath, Plan plan) {
    }

    public void chargerLivraison(String filePath, Tournee tournee) {
    }

    public void calculerTournee(Plan plan, Tournee tournee) {
    }

    public void clickedOnPoint(PointLivraison pointLivraison, FenetrePrincipale fenetrePrincipale) {
        /*String toShow = "";
        toShow += pointLivraison.getId() + "\r\n";
        toShow += pointLivraison.getDebutPlage() + "\r\n";
        toShow += pointLivraison.getFinPlage() + "\r\n";
        toShow += pointLivraison.getDuree() + "\r\n";
        System.out.println(toShow);
        fenetrePrincipale.getVueTextuelle().clickedOnPoint(pointLivraison);
        //fenetrePrincipale.getInfoText().setText(toShow);
        */
    	
        fenetrePrincipale.getVueTextuelle().clickedOnPoint(pointLivraison);
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(pointLivraison);
        fenetrePrincipale.getVueGraphique().repaint();
        //fenetrePrincipale.getInfoText().setText(toShow);*/
    }

    public void undo(LstDeCde l) {
    	l.undo();
    }

    public void redo(LstDeCde l) {
    	l.redo();
    }

    public void cdeAjouterLivraison(PointLivraison pointLivraison, Tournee tournee) {
    }

    public void cdeSupprimerLivraison(PointLivraison pointLivraison,Tournee tournee) {
    }

    public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, double debutPlage, double finPlage) {
    }

    //Méthodes qui n'avaient pas de corps
    public void afficherPlan() {
    }

    public void afficherLivraison() {
    }

    public void afficherTournee() {
    }
}
