package Modele;

import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class Plan extends Observable {
    protected List<Troncon> listeTroncons;
    protected List<Noeud> listeNoeuds;

    public Plan () {
        listeTroncons=new ArrayList<Troncon>();
        listeNoeuds=new ArrayList<Noeud>();

    }


    public void addTroncon (Troncon troncon) {
        listeTroncons.add(troncon);

    }
    public void addNoeud(Noeud noeud) {listeNoeuds.add(noeud);}

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
