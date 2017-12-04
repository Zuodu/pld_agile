package Algo;

import java.util.ArrayList;

/**
 * Created by flavi on 2017/12/2.
 */
public class TSP2 extends TSP1 {
    @Override
    protected double bound(Integer sommetCourant, Integer entrepot, ArrayList<Integer> nonVus, double[][] cout, double[] duree) {
        if (nonVus.size() == 1) {
            return 0;
        }
        ArrayList<Integer> pointsAlivrer = new ArrayList<Integer>(nonVus);
        pointsAlivrer.add(sommetCourant);
        double minCoutDepart = Double.MAX_VALUE;
        double minCoutArrivee = Double.MAX_VALUE;
        for (Integer point : nonVus) {
            if (cout[sommetCourant][point] < minCoutDepart) {
                minCoutDepart = cout[sommetCourant][point];
            }
        }
        for (Integer point : nonVus) {
            if (cout[point][entrepot] < minCoutArrivee) {
                minCoutArrivee = cout[point][entrepot];
            }

        }
        double bound = (minCoutDepart + minCoutArrivee) / 2;
        for (int i = 0; i < nonVus.size(); i++) {
            bound += getEvaluateCout(nonVus.get(i), cout, nonVus);
            bound += duree[i];
        }
        return bound;
    }

    private double getEvaluateCout(Integer sommet, double[][] cout, ArrayList<Integer> nonVus) {
        double minCoutDepart = Double.MAX_VALUE;
        Integer sommetTrouveDepart = sommet;
        double minCoutArrivee = Double.MAX_VALUE;
        for (Integer point : nonVus) {
            if (cout[sommet][point] < minCoutDepart) {
                minCoutDepart = cout[sommet][point];
                sommetTrouveDepart = point;
            }
        }
        if (nonVus.size() == 2) {
            return minCoutDepart;
        }
        for (Integer point : nonVus) {
            if (point != sommetTrouveDepart) {
                if (cout[point][sommet] < minCoutArrivee) {
                    minCoutArrivee = cout[point][sommet];
                }
            }
        }
        return (minCoutArrivee + minCoutDepart) / 2;

    }
}
