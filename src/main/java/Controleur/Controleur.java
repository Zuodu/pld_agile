package Controleur;

import Algo.AbstractGraphe;
import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.*;
import Vue.FenetrePrincipale;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author H4401
 * Classe Contr�leur du logiciel
 */
public class Controleur {
    private FenetrePrincipale fenetrePrincipale;
    private Plan plan;
    private Tournee tournee;
    private static final EtatInit etatInit = new EtatInit();
    private static final EtatChargerPlan etatChargerPlan = new EtatChargerPlan();
    private static final EtatChargerLivraison etatChargerLivraison = new EtatChargerLivraison();
    private static final EtatCalculerTournee etatCalculerTournee = new EtatCalculerTournee();
    private static final EtatModifier etatModifier = new EtatModifier();
    private static Etat etatCourant;

    private static void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }
    //private static Controleur instance;


    /** public static Controleur getInstance () {
        if (instance==null) instance=new Controleur();
        return instance;
     }**/

    public Controleur() {
        plan = new Plan();
        tournee = new Tournee();
        fenetrePrincipale = new FenetrePrincipale(plan, tournee, this);
    }

    /**
     * M�thode chargeant le plan depuis un fichier xml (par appel au package chargeur XML)
     * @param filePath Le chemin d'acc�s au fichier xml
     */
    public void chargerPlan (String filePath)
    {
        etatCourant.chargerPlan(filePath, plan);
    }

    /**
     * M�thode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
     * @param filePath Le chemin d'acc�s au fichier xml
     */
    public void chargerLivraison (String filePath){
        etatCourant.chargerLivraison(filePath, tournee);
    }

    /**
     * M�thode lan�ant le calcul de la tourn�e
     */
    public void calculerTournee () {
        etatCourant.calculerTournee(plan, tournee);
    }

    public void afficherPlan() {

    }

    public void afficherLivraison() {

    }
    public void afficherTournee() {

    }

    /**
     * M�thode permettant d'afficher les informations du point cliqu�
     * @param pointLivraison
     */
    public void clickedOnPoint(PointLivraison pointLivraison) {
        etatCourant.clickedOnPoint(pointLivraison, fenetrePrincipale);
    }
}
