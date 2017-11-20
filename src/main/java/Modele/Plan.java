package Modele;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

/**
 * Created by flavi on 2017/11/18.
 */
public class Plan extends Observable {
    protected Set<Troncon> listeTroncons;
    protected Set<Noeud> listeNoeuds;

    public Plan () {
        listeTroncons=new HashSet<Troncon>();
        listeNoeuds=new HashSet<Noeud>();

    }


    public void addTroncon (Troncon troncon) {
        listeTroncons.add(troncon);
    }
    public void addNoeud(Noeud noeud) {listeNoeuds.add(noeud);}

    public Set<Troncon> getListeTroncons() {
        return listeTroncons;
    }

    public Set<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }
}
