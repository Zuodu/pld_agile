package Algo;

import java.util.*;

/**
 * Created by siyingjiang on 2017/11/22.
 */
public class Glouton {
    private Integer[] meilleureSolution;
    private int coutMeilleureSolution = 0;

    public Integer getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
            return null;
        return meilleureSolution[i];
    }

    public Glouton(int nbSommet) {
        this.meilleureSolution = new Integer[nbSommet];
        this.coutMeilleureSolution = 0;
    }

    public int getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    public List<Integer> chercheSolution(int[][] cout, int[] duree) {
        List<Integer> res = new ArrayList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int numNodes = cout.length;
        int distanceMin;
        int nextPos = 0;
        int distance = 0;
        int posCurrent = 0;
        visited.add(posCurrent);
        res.add(posCurrent);
        while (visited.size() < numNodes) {
            int[] neighborDistance = cout[posCurrent];
            distanceMin = Integer.MAX_VALUE;
            for (int i = 0; i < numNodes; i++) {
                int neighbor = i;
                if (visited.contains(neighbor)) {
                    continue;
                } else {
                    int dNei = cout[posCurrent][i];
                    if (dNei < distanceMin) {
                        distanceMin = dNei;
                        nextPos = neighbor;
                    }
                }
            }
            distance += distanceMin;
            posCurrent = nextPos;
            visited.add(posCurrent);
            res.add(posCurrent);
        }
        visited.toArray(meilleureSolution);
        coutMeilleureSolution = distance;
        System.out.println(distance);
        return res;
    }

}
