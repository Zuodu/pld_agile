package Modele;

import java.util.HashSet;
import java.util.Set;

/**
 * @author H4401
 * Classe représentant l'élément Noeud d'un plan, ne contient que des Get, Set et add.
 */
public class Noeud{
    protected Long id;
    protected int x;
    protected int y;
    protected Set<Troncon> neighbors;

    /**
     * Constructeur
     * @param id
     * @param x
     * @param y
     */
    public Noeud(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors=new HashSet<Troncon>();
    }

    public Noeud(PointLivraison pointLivraison)
    {
        this.id = pointLivraison.getId();
        this.x = pointLivraison.getX();
        this.y = pointLivraison.getY();
        this.neighbors = new HashSet<Troncon>();
        this.getNeighbors().addAll(pointLivraison.getNeighbors());
    }

    /**
     * Ajout de tronçon voisin
     * @param neighbor
     */
    public void addNeighbor(Troncon neighbor){
        neighbors.add(neighbor);
    }

    /**
     * get
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * get
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * get
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set
     * @param neighbors
     */
    public void setNeighbors(Set<Troncon> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Get 
     * @return neighbors
     */
    public Set<Troncon> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", x=" + x +
                        ", y=" + y;
    }
}
