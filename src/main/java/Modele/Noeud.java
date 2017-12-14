package Modele;

import java.util.HashSet;
import java.util.Set;

/**
 * @author H4401
 *         Classe représentant l'élément Noeud d'un plan, ne contient que des Get, Set et add.
 */
public class Noeud {
    protected Long id;
    protected int x;
    protected int y;
    protected Set<Troncon> neighbors;

    /**
     * Constructeur
     *
     * @param id :id du noeud
     * @param x  :position x du noeud
     * @param y  :position y du noeud
     */
    public Noeud(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<>();
    }

    /**
     * Constructeur par copie
     *
     * @param unNoeud :le noeud a copier
     */
    public Noeud(Noeud unNoeud) {
        this(unNoeud.getId(), unNoeud.getX(), unNoeud.getY());
        for (Troncon troncon : unNoeud.getNeighbors()) {
            this.addNeighbor(troncon);
        }
    }

    /**
     * Ajout de tronçon voisin
     *
     * @param neighbor :le troncon voison a ajouter
     */
    public void addNeighbor(Troncon neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * Get
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set
     *
     * @param neighbors
     */
    public void setNeighbors(Set<Troncon> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Get
     *
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
