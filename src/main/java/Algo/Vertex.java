package Algo;

import Modele.Noeud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flavi on 2017/11/18.
 */
public class Vertex {
    private int idVertex;
    private List<Edge> neighbors;

    public Vertex(int idVertex) {
        this.idVertex = idVertex;
        neighbors=new ArrayList<Edge>();
    }

    public int getIdVertex() {
        return idVertex;
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Edge neighbor){
        neighbors.add(neighbor);
    }


    @Override
    public String toString() {
        return "Vertex{" +
                "idVertex=" + idVertex +
                ", neighbors=" + neighbors +
                '}';
    }
}
