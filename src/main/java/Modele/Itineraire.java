package Modele;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by flavi on 2017/11/18.
 */
public class Itineraire {
    private Noeud noeudOrigine;
    private Noeud noeudDestination;
    private LinkedList<Troncon> listeTroncons;
    private double longueurTotale;
    private double temps;


    public Itineraire() {
        listeTroncons = new LinkedList<Troncon>();
        longueurTotale = 0;
        temps = 0;
    }

    public void addTroncon(Troncon troncon) {
        listeTroncons.addFirst(troncon);
        longueurTotale += troncon.getLongueur();
    }

    public Noeud getNoeudOrigine() {
        return noeudOrigine;
    }

    public void setNoeudOrigine(Noeud noeudOrigine) {
        this.noeudOrigine = noeudOrigine;
    }

    public Noeud getNoeudDestination() {
        return noeudDestination;
    }

    public void setNoeudDestination(Noeud noeudDestination) {
        this.noeudDestination = noeudDestination;
    }

    public double getLongueurTotale() {
        return longueurTotale;
    }

    public LinkedList<Troncon> getListeTroncons() {
        return listeTroncons;
    }

    @Override
    public String toString() {
        return "Itineraire{" +
                "noeudOrigine=" + noeudOrigine +
                ", noeudDestination=" + noeudDestination +
                ", longueurTotale=" + longueurTotale +

                "}\r\n";
    }
}
