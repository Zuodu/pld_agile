package Algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by siyingjiang on 2017/12/6.
 */
public class TSP4 extends TSP2 {
    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart, Boolean hasTW, double coutVus) {
        if (hasTW)
            return new IteratorPlage(nonVus, sommetCrt, cout, duree, plageArrivee, plageDepart, coutVus);
        else
            return new IteratorMin(nonVus, sommetCrt, cout, duree);
    }

}
