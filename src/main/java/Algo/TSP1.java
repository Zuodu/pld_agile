package Algo;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, double[][] cout, double[] duree,Double[] plageArrivee,Double[] plageDepart) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected double bound(Integer sommetCourant, Integer entrepot,ArrayList<Integer> nonVus, double[][] cout, double[] duree) {
		return 0;
	}
}
