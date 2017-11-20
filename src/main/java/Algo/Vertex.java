package Algo;

import Modele.Noeud;
import Modele.PointLivraison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by flavi on 2017/11/18.
 */
public class Vertex {
    private Set<Edge> neighbors;
    private Noeud noeud;

    public Vertex(Noeud noeud) {
        this.neighbors = new HashSet<Edge>();
        this.noeud = noeud;
    }

    public void addNeighbor(Edge neighbor){
        neighbors.add(neighbor);
    }

    public Set<Edge> getNeighbors() {
        return neighbors;
    }

    public Noeud getNoeud() {
        return noeud;
    }



    @Override
    public String toString() {
        return "Vertex{" +
                "neighbors=" + neighbors +
                ", noeud=" + noeud +
                '}';
    }
}
