package Algo;

import Modele.Itineraire;
import Modele.PointLivraison;

import java.util.*;

/**
 * Created by siyingjiang on 2017/11/22.
 */
public class Glouton {
    private PointLivraison[] meilleureSolution;
    private double coutMeilleureSolution = 0;
    private HashMap<PointLivraison, HashMap<PointLivraison, Double>> neiborsDistanceMap;

    public PointLivraison getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
            return null;
        return meilleureSolution[i];
    }

    public Glouton(HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        neiborsDistanceMap = new HashMap<PointLivraison, HashMap<PointLivraison, Double>>();
        for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> entry : itinerairesMap.entrySet()) {
            if (neiborsDistanceMap.containsKey(entry.getKey().getKey())) {
                neiborsDistanceMap.get(entry.getKey().getKey()).put(entry.getKey().getValue(), entry.getValue().getLongueurTotale());
            } else {
                neiborsDistanceMap.put(entry.getKey().getKey(), new HashMap<PointLivraison, Double>());
                neiborsDistanceMap.get(entry.getKey().getKey()).put(entry.getKey().getValue(), entry.getValue().getLongueurTotale());
            }
        }
        this.coutMeilleureSolution = 0;
    }

    public double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    public void chercheSolution(ArrayList<PointLivraison> listPointLivraisons) {
        meilleureSolution = new PointLivraison[listPointLivraisons.size()];
        List<PointLivraison> visited = new ArrayList<PointLivraison>();
        int numNodes = listPointLivraisons.size();
        double distanceMin;
        PointLivraison nextPos = null;
        int distance = 0;
        PointLivraison posCurrent = listPointLivraisons.get(0);
        visited.add(posCurrent);
        while (visited.size() < numNodes) {
            HashMap<PointLivraison, Double> neighborsDistance = neiborsDistanceMap.get(posCurrent);
            distanceMin = Double.MAX_VALUE;
            for (Map.Entry<PointLivraison, Double> neighbor : neighborsDistance.entrySet()) {
                if (visited.contains(neighbor)) {
                    continue;
                } else {
                    double dNei = neighbor.getValue();
                    if (dNei < distanceMin) {
                        distanceMin = dNei;
                        nextPos = neighbor.getKey();
                    }
                }
            }
            distance += distanceMin;
            posCurrent = nextPos;
            visited.add(posCurrent);
        }
        visited.toArray(meilleureSolution);
        coutMeilleureSolution = distance;
        System.out.println(distance);
    }

}
