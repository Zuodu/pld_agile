package Controleur;

import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Tournee;

import java.util.List;

/**
 * Created by flavi on 2017/11/18.
 */
public class Controleur {
    private Plan plan;
    private List<PointLivraison> pointLivraisons;
    private Tournee tournee;
    private static Controleur instance;

    private static Controleur getInstance () {
        if (instance==null) instance=new Controleur();
        return instance;
    }

    private Controleur() {
    }

    public void chargerPlan (String filePath)
    {
        ChargeurPlan.getInstance().parse(filePath);
        plan=ChargeurPlan.getInstance().getPlan();
    }

    public void chargerLivraison (String filePath){
        ChargeurLivraison.getInstance().parse(filePath);
        pointLivraisons=ChargeurLivraison.getInstance().getPointLivraisons();

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
