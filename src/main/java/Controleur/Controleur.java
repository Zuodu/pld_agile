package Controleur;

import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.*;

import java.util.List;

/**
 * Created by flavi on 2017/11/18.
 */
public class Controleur {

    private Plan plan;
    private List<PointLivraison> pointLivraisons;
    private Tournee tournee;
    private static Controleur instance;


    public Plan getPlan() {
        return plan;
    }

    public List<PointLivraison> getPointLivraisons() {
        return pointLivraisons;
    }

    public Tournee getTournee() {
        return tournee;
    }

    public static Controleur getInstance () {
        if (instance==null) instance=new Controleur();
        return instance;
    }

    private Controleur() {
    }

    public void chargerPlan (String filePath)
    {
        ChargeurPlan.getInstance().parse(filePath);
        plan=ChargeurPlan.getInstance().getPlan();
        for (Noeud noeud : plan.getListeNoeuds()) {
            System.out.println(noeud);
        }

        for (Troncon troncon : plan.getListeTroncons()) {
            System.out.println(troncon);
        }
    }

    public void chargerLivraison (String filePath){
        ChargeurLivraison.getInstance().parse(filePath);
        pointLivraisons=ChargeurLivraison.getInstance().getPointLivraisons();
        for (PointLivraison pointLivraison : pointLivraisons) {
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
