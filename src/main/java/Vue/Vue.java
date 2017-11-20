package Vue;

import Algo.Edge;
import Algo.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by flavi on 2017/11/18.
 */
public class Vue {
    public static void main(String[] args) {
        Queue<Edge> queue=new PriorityQueue<Edge>(10,longueurComparator);
        queue.add(new Edge(new Vertex(1),10));
        queue.add(new Edge(new Vertex(2),9));
        queue.add(new Edge(new Vertex(3),8));
        queue.add(new Edge(new Vertex(4),50));
        queue.add(new Edge(new Vertex(5),12));
        while (queue.size() != 0)
        {
            System.out.println(queue.remove());
        }
    }

    public static Comparator<Edge> longueurComparator=new Comparator<Edge>() {
        public int compare(Edge o1, Edge o2) {
            return (int)(o1.getLongueur() - o2.getLongueur());
        }
    };
}
