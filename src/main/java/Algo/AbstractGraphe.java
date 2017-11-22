package Algo;

import Modele.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class AbstractGraphe {
    private int[][] cout;
    private int[] duree;
    HashMap<Integer, PointLivraison> pointLivraisonMap;
    HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    Tournee tournee;
    int nbSommets;

    public AbstractGraphe(Plan plan, Tournee tournee) {
        this.tournee = tournee;
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
        this.pointLivraisonMap = new HashMap<Integer, PointLivraison>();
        int i = 0;
        pointLivraisonMap.put(i++, tournee.getEntrepot());
        //TODO:区分entrepot和pointlivraison
        for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
            pointLivraisonMap.put(i++, pointLivraison);
        }
        this.nbSommets = pointLivraisonMap.size();
        this.cout = new int[nbSommets][nbSommets];
        this.duree = new int[nbSommets];
        generateTableCount();
        generateTableDuree();

    }

    public void generateTableCount() {
        for (int m = 0; m < cout.length; m++) {
            for (int n = 0; n < cout[m].length; n++) {
                if (m == n) {
                    cout[m][n] = 0;
                    continue;
                }
                Dijkstra dijkstra = new Dijkstra();
                dijkstra.chercheDistanceMin(pointLivraisonMap.get(m), pointLivraisonMap.get(n));
                itinerairesMap.put(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(m), pointLivraisonMap.get(n)), dijkstra.getMeilleurItineraire());
                cout[m][n] = (int) dijkstra.getMeilleurItineraire().getLongueurTotale();
            }
        }
    }

    public void generateTableDuree() {
        for (int i = 0; i < cout.length; i++) {
            duree[i] = (int) pointLivraisonMap.get(i).getDuree();
        }
    }

    public void getItineraire() {
        TSP tsp = new TSP1();
        tsp.chercheSolution(1000, nbSommets, cout, duree);
        for (int i = 0; i < nbSommets - 1; i++) {
            tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(tsp.getMeilleureSolution(i)), pointLivraisonMap.get(tsp.getMeilleureSolution(i + 1)))));
        }
        tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(tsp.getMeilleureSolution(nbSommets - 1)), pointLivraisonMap.get(0))));
    }

    public Tournee getTournee() {
        return tournee;
    }
}
