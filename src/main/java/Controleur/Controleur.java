package Controleur;

import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.*;
import Vue.FenetrePrincipale;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by flavi on 2017/11/18.
 */
public class Controleur {
    private FenetrePrincipale fenetrePrincipale;
    private Plan plan;
    private ListePointLivraisons pointLivraisons;
    private Tournee tournee;
    private static Controleur instance;



    public static Controleur getInstance () {
        if (instance==null) instance=new Controleur();
        return instance;
    }

    private Controleur() {
        plan = new Plan();
        pointLivraisons = new ListePointLivraisons();
        fenetrePrincipale = new FenetrePrincipale(plan, pointLivraisons);
    }

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

    public void chargerLivraison (String filePath){
        ChargeurLivraison.getInstance().parse(pointLivraisons, filePath);

        for (PointLivraison pointLivraison : pointLivraisons.getPointLivraisons()) {
            System.out.println(pointLivraison);
        }

    }

    public void calculerTournee () {

    }

    public void afficherPlan() {

    }

    public void afficherLivraison() {

    }
    public void afficherTournee() {

    }
}
