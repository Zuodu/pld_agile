package Algo;

import Modele.Itineraire;
import Modele.PointLivraison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TSP1 extends TemplateTSP {

    @Override
    protected Iterator<PointLivraison> iterator(PointLivraison sommetCrt, ArrayList<PointLivraison> nonVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        return new IteratorSeq(nonVus, sommetCrt);
    }

    @Override
    protected double bound(double vitesse, PointLivraison sommetCourant, ArrayList<PointLivraison> nonVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        return 0;
    }


}
