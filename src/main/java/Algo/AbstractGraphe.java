package Algo;

import Modele.*;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class AbstractGraphe {
    private int[][] cout;
    private int[] duree;
    ArrayList<PointLivraison> listPointLivraisons;
    HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    Tournee tournee;
    int nbSommets;

    public AbstractGraphe(Plan plan, Tournee tournee) {
        this.tournee = tournee;
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
        this.listPointLivraisons = new ArrayList<PointLivraison>();
        int i = 0;
        listPointLivraisons.add(tournee.getEntrepot());
        //TODO:区分entrepot和pointlivraison
        for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
            listPointLivraisons.add(pointLivraison);
        }
        this.nbSommets = listPointLivraisons.size();
        for (PointLivraison pointLivraison1 : listPointLivraisons) {
            for (PointLivraison pointLivraison2 : listPointLivraisons) {
                if (pointLivraison1.getId().equals(pointLivraison2.getId())) {
                    continue;
                }
                Dijkstra dijkstra = new Dijkstra();
                dijkstra.chercheDistanceMin(pointLivraison1, pointLivraison2);
                itinerairesMap.put(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraison1, pointLivraison2), dijkstra.getMeilleurItineraire());
            }
        }
//        generateTableCout();
//        generateTableDuree();

    }

    public void getItineraire() {
        TSP tsp = new TSP1();
        tsp.chercheSolution(1000, tournee.getHeureDeDepart(), tournee.getVitesse(), nbSommets, listPointLivraisons, itinerairesMap);
        for (int i = 0; i < nbSommets - 1; i++) {
            tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(tsp.getMeilleureSolution(i), tsp.getMeilleureSolution(i + 1))));
        }
        tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(tsp.getMeilleureSolution(nbSommets - 1), listPointLivraisons.get(0))));
        tournee.setHoraireMeilleureSolution(tsp.getHoraireMeilleureSolution());
        for (Map.Entry<Double, Double> entry : tournee.getHoraireMeilleureSolution()) {
            System.out.println("horaire:" + entry);
        }
        if (tsp.getTempsLimiteAtteint()) {
            System.out.println("Temps limite atteint");
        }
    }


    public void getItineraireGlouton() {
        Glouton glouton = new Glouton(itinerairesMap);
        glouton.chercheSolution(listPointLivraisons);
        for (int i = 0; i < nbSommets - 1; i++) {
            tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(glouton.getMeilleureSolution(i), glouton.getMeilleureSolution(i + 1))));
        }
        tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(glouton.getMeilleureSolution(nbSommets - 1), listPointLivraisons.get(0))));
    }

    public Tournee getTournee() {
        return tournee;
    }
}
