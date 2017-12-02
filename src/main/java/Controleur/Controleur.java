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
 * Classe Contrôleur du logiciel
 */
public class Controleur {
    private FenetrePrincipale fenetrePrincipale;
    private Plan plan;
    private Tournee tournee;
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
     * Méthode chargeant le plan depuis un fichier xml (par appel au package chargeur XML)
     * @param filePath Le chemin d'accès au fichier xml
     */
    public void chargerPlan (String filePath)
    {
        ChargeurPlan.getInstance().parse(plan, filePath);
        // plan=ChargeurPlan.getInstance().getPlan();
        for (Noeud noeud : plan.getListeNoeuds()) {
            //System.out.println(noeud);
        }

        for (Troncon troncon : plan.getListeTroncons()) {
            System.out.println(troncon);
        }
    }

    /**
     * Méthode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
     * @param filePath Le chemin d'accès au fichier xml
     */
    public void chargerLivraison (String filePath){
        ChargeurLivraison.getInstance().parse(tournee, filePath);

        for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
            System.out.println(pointLivraison);
        }

    }

    /**
     * Méthode lançant le calcul de la tournée
     */
    public void calculerTournee () {
        AbstractGraphe abstractGraphe = new AbstractGraphe(plan, tournee);
        abstractGraphe.getItineraire();
        tournee.SignalerFinDajoutPointsLivraisons();
        System.out.println(abstractGraphe.getTournee());

    }

    public void afficherPlan() {

    }

    public void afficherLivraison() {

    }
    public void afficherTournee() {

    }
}
