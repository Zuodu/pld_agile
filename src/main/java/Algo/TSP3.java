package Algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by flavi on 2017/12/2.
 */
public class TSP3 extends TSP2 {
    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart,Boolean hasTW,double coutVus) {
        return new IteratorMin(nonVus, sommetCrt, cout, duree);
    }
}
