package Modele;

import java.util.*;

/**
 * @author H4401
 * Classe représentant un plan, extends Observable, contient des Set, des add 
 * et une méthode indiquant à l'observeur la fin d'ajouts de tronçons et de noeuds au plan.
 */
public class Plan extends Observable {
    protected Set<Troncon> listeTroncons;
    protected Set<Noeud> listeNoeuds;

    public Plan () {
        listeTroncons= new HashSet<Troncon>();
        listeNoeuds= new HashSet<Noeud>();

    }

    /**
     * add
     * @param troncon
     */
    public void addTroncon (Troncon troncon) {
        listeTroncons.add(troncon);

    }
    /**
     * Ajoute un noeud à la liste
     * @param noeud
     */
    public void addNoeud(Noeud noeud) {listeNoeuds.add(noeud);}

    /**
     * Get
     * @return listeTroncon
     */
    public Set<Troncon> getListeTroncons() {
        return listeTroncons;
    }

    /**
     * Get
     * @return listeNoeuds
     */
    public Set<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }

    /**
     * Signale la fin des ajouts de tronçon et de noeux au plan
     */
    public void signalerFin() {
        setChanged();
        notifyObservers(this);
    }
}
