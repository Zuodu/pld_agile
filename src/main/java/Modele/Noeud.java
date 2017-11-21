package Modele;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flavi on 2017/11/18.
 */
public class Noeud{
    protected Long id;
    protected int x;
    protected int y;
    protected Set<Troncon> neighbors;

    public Noeud(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors=new HashSet<Troncon>();
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

    public void setNeighbors(Set<Troncon> neighbors) {
        this.neighbors = neighbors;
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
