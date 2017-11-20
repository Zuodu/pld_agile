package Algo;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.*;

/**
 * Created by siyingjiang on 2017/11/19.
 */
@SuppressWarnings("ALL")
public class Dijkstra {
    private HashMap<Vertex, Vertex> parent;
    private HashMap<Vertex, Double> distance;
    private LinkedList<Vertex> result;

    public Dijkstra() {
        this.parent = new HashMap<Vertex, Vertex>();
        this.distance = new HashMap<Vertex, Double>();
        this.result = new LinkedList<Vertex>();
    }

    public void chercheSolution(Vertex src, Vertex target) {

        parent.put(src, src);

        distance.put(src, 0d);

        Set<Vertex> x = new HashSet<Vertex>();

        Queue<Edge> y = new PriorityQueue<Edge>(10, longueurComparator);

        y.add(new Edge(src, 0));
        x.add(src);

        while (!y.isEmpty()) {
            Vertex vertex = y.remove().getVertexAdjacent();
            if (vertex == target) {
                while (vertex != src) {
                    result.addFirst(vertex);
                    vertex = parent.get(vertex);
                }
                result.addFirst(src);
                return;
            }

            x.add(vertex);

            for(Edge edge:vertex.getNeighbors()){
                if(x.contains(edge.getVertexAdjacent())){
                    continue;
                }
                if(getDistance(edge.getVertexAdjacent())>distance.get(vertex)+edge.getLongueur()){
                    distance.replace(edge.getVertexAdjacent(),distance.get(vertex)+edge.getLongueur());
                    parent.replace(edge.getVertexAdjacent(),vertex);
                    y.add(new Edge(edge.getVertexAdjacent(),distance.get(edge.getVertexAdjacent())));
                }
            }
        }


    }

    private Double getDistance(Vertex vertex) {
        if (distance.containsKey(vertex)) {
            return distance.get(vertex);
        }
        return Double.MAX_VALUE;
    }

    public static Comparator<Edge> longueurComparator = new Comparator<Edge>() {
        public int compare(Edge o1, Edge o2) {
            return (int) (o1.getLongueur() - o2.getLongueur());
        }
    };
}
