package Modele;

import java.util.*;

/**
 * @author H4401
 * Classe représentant un plan, extends Observable, contient des Set, des add 
 * et une méthode indiquant à l'observeur la fin d'ajouts de tronçons et de noeuds au plan.
 */
public class Plan extends Observable {
    protected boolean isCharge;
    protected Set<Troncon> listeTroncons;
    protected Set<Noeud> listeNoeuds;

    /**
     * Constructeur par défaut
     */
    public Plan () {
        isCharge=false;
        listeTroncons= new HashSet<>();
        listeNoeuds= new HashSet<>();

    }

    /**
     * Réinitialisation du plan
     */
    public void reinitialise () {
        isCharge=false;
        listeTroncons= new HashSet<>();
        listeNoeuds= new HashSet<>();
        setChanged();
        notifyObservers();
    }

    /**
     * Get
     * @return isCharge
     */
    public boolean isCharge() {
        return isCharge;
    }

    /**
     * Set
     * @param charge
     */
    public void setCharge(boolean charge) {
        isCharge = charge;
        setChanged();
        notifyObservers();
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


}
