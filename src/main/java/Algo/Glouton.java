package Algo;

import java.util.*;

/**
 * Created by siyingjiang on 2017/11/22.
 */
public class Glouton {
    private Integer[] meilleureSolution;
    private double coutMeilleureSolution = 0;

    public Integer getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
            return null;
        return meilleureSolution[i];
    }

    public Glouton(int nbSommet) {
        this.meilleureSolution = new Integer[nbSommet];
        this.coutMeilleureSolution = 0;
    }

    public double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    public List<Integer> chercheSolution(double heureDeDepart, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int numNodes = cout.length;
        double distanceMin;
        int nextPos = 0;
        double distance = heureDeDepart;
        int posCurrent = 0;
        double arrivee=0;
        double depart=0;
        visited.add(posCurrent);
        res.add(posCurrent);
        while (visited.size() < numNodes) {
            distanceMin = Integer.MAX_VALUE;
            for (int i = 0; i < numNodes; i++) {
                int neighbor = i;
                if (visited.contains(neighbor)) {
                    continue;
                } else {
                    double dNei = cout[posCurrent][i];
                    if (dNei < distanceMin) {
                        distanceMin = dNei;
                        arrivee=distance+distanceMin;
                        depart=arrivee+duree[i];
                        if (plageArrivee[i] != null && plageDepart[i] != null) {
                            if (arrivee < plageArrivee[i]) {
                                arrivee = plageArrivee[i];
                                depart=arrivee+duree[i];
                            } else if (depart > plageDepart[i]) {
                                continue;
                            }
                        }
                        nextPos = neighbor;
                    }
                }
            }
            distance += depart;
            posCurrent = nextPos;
            visited.add(posCurrent);
            res.add(posCurrent);
        }
        visited.toArray(meilleureSolution);
        distance+=cout[posCurrent][0];
        coutMeilleureSolution = distance;
        System.out.println(distance);
        return res;
    }

}
