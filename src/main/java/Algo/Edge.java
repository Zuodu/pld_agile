package Algo;

import Modele.Noeud;

/**
 * Created by flavi on 2017/11/18.
 */
public class Edge {
    private Vertex vertexAdjacent;
    private double longueur;

    public Edge(Vertex vertexAdjacent, double longueur) {
        this.vertexAdjacent = vertexAdjacent;
        this.longueur = longueur;
    }

    public Vertex getVertexAdjacent() {
        return vertexAdjacent;
    }

    public double getLongueur() {
        return longueur;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexAdjacent=" + vertexAdjacent +
                ", longueur=" + longueur +
                '}';
    }
}
