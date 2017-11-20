package Algo;

import Modele.Noeud;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Troncon;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class AbstractGraphe {
    private HashMap<Noeud, Vertex> vertexMap;
    private HashMap<Noeud, Set<Troncon>> tronconsMap;
    private Map<Long, Map<Integer, Double>> matAbstrait;

    public AbstractGraphe(Plan plan, List<Noeud> noeuds) {


    }


    private void addVertex(Noeud noeud) {
        Vertex vertex = new Vertex(noeud);
        for(Troncon neighborTroncon: tronconsMap.get(noeud)) {
            Noeud neighborNoeud=neighborTroncon.getDestination();
            if (vertexMap.containsKey(neighborNoeud)) {
                vertex.addNeighbor(new Edge(vertexMap.get(neighborNoeud), neighborTroncon.getLongueur()));
            } else {
                Vertex neighborVetex = new Vertex(neighborNoeud);
                vertex.addNeighbor(new Edge(neighborVetex, neighborTroncon.getLongueur()));
                vertexMap.put(neighborNoeud, neighborVetex);
            }
        }

    }

    private void generateTronconMap(Plan plan) {
        tronconsMap = new HashMap<Noeud, Set<Troncon>>();
        for (Troncon troncon : plan.getListeTroncons()) {
            if (tronconsMap.containsKey(troncon.getOrigine()))
                tronconsMap.get(troncon.getOrigine()).add(troncon);
            else
                tronconsMap.put(troncon.getOrigine(),new HashSet<Troncon>(Arrays.asList(troncon)));
        }
    }
}
