package Modele;

import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class Plan extends Observable {
    protected LinkedList<Troncon> listeTroncons;
    protected LinkedList<Noeud> listeNoeuds;

    public Plan () {
        listeTroncons= new LinkedList<Troncon>();
        listeNoeuds= new LinkedList<Noeud>();

    }


    public void addTroncon (Troncon troncon) {
        listeTroncons.addFirst(troncon);

    }
    public void addNoeud(Noeud noeud) {listeNoeuds.addFirst(noeud);}

    public List<Troncon> getListeTroncons() {
        return listeTroncons;
    }

    public List<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }

    public void signalerFin() {
        setChanged();
        notifyObservers(this);
    }
}
