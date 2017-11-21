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
    HashMap<Map.Entry<PointLivraison,PointLivraison>,Itineraire> itinerairesMap;
    int nbSommets;

    public AbstractGraphe(Plan plan, Set<PointLivraison> pointLivraisons) {
        this.pointLivraisonMap = new HashMap<Integer, PointLivraison>();
        this.nbSommets = pointLivraisons.size();
        this.cout = new int[nbSommets][nbSommets];
        this.duree = new int[nbSommets];
        int i = 0;
        for (PointLivraison pointLivraison : pointLivraisons) {
            pointLivraisonMap.put(i++, pointLivraison);
        }
        generateTableCount();
        generateTableDuree();

    }

    public void generateTableCount() {
        for (int m = 0; m < cout.length; m++) {
            for (int n = 0; n < cout[m].length; n++) {
                Dijkstra dijkstra = new Dijkstra();
                dijkstra.chercheDistanceMin(pointLivraisonMap.get(m), pointLivraisonMap.get(n));
                cout[m][n] = (int) dijkstra.getMeilleurItineraire().getLongueurTotale();
            }
        }
    }

    public void generateTableDuree() {
        for (int i = 0; i < cout.length; i++) {
            duree[i] = (int) pointLivraisonMap.get(i).getDuree();
        }
    }

    public List<PointLivraison> getItineraire() {
        TSP tsp = new TSP1();
        tsp.chercheSolution(1000, nbSommets, cout, duree);
        List<PointLivraison> pointLivraisons = new ArrayList<PointLivraison>();
        for (int i = 0; i < nbSommets; i++) {
            pointLivraisons.add(pointLivraisonMap.get(tsp.getMeilleureSolution(i)));
        }
        return pointLivraisons;
    }
}
