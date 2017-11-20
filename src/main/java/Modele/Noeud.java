package Modele;

import java.util.Set;

/**
 * Created by flavi on 2017/11/18.
 */
public class Noeud{
    private Long id;
    private int x;
    private int y;
    private Set<Troncon> neighbors;

    public Noeud(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void addNeighbor(Troncon neighbor){
        neighbors.add(neighbor);
    }

    public Long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<Troncon> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "Noeud{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
