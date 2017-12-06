package Algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by siyingjiang on 2017/12/6.
 */
public class TSP4 extends TSP1 {
    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart) {
        return new IteratorPlage(nonVus, sommetCrt, cout, duree, plageArrivee, plageDepart);
    }

    @Override
    protected double bound(Integer sommetCourant, Integer entrepot, ArrayList<Integer> nonVus, double[][] cout, double[] duree) {
        return 99999999;
    }
}
