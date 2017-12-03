package FeuilleDeRoute;

import Modele.Troncon;

/**
 * @author H4401
 * Cette classe est utilisée pour garder la liste des tronçons représentant la même rue.
 */
public class PileTroncon {

    private Troncon troncon;
    private double distanceTotale;
    private Indication indication;

    /**
     * Construit une nouvelle pile de tronçons à partir d'un nouveau tronçon et de l'indication Indication
     *
     * @param t tronçon différent du précédent
     * @param v Indication calculée
     */
    public PileTroncon(Troncon t, Indication v) {
        troncon = t;
        indication = v;
        distanceTotale = t.getLongueur();
    }

    /**
     * Ajouter un troçon au nom de rue identique à la pile.
     *
     * @param t Tronçon à ajouter
     */
    public void addTroncon(Troncon t) {
        troncon = t;
        distanceTotale += t.getLongueur();
    }

    /**
     * Get
     *
     * @return troncon
     */
    public Troncon getTroncon() {
        return troncon;
    }

    /**
     * Get
     *
     * @return distance totale de la pile de tronçons
     */
    public double getDistanceTotale() {
        return distanceTotale;
    }

    /**
     * Get
     *
     * @return Indication
     */
    public Indication getIndication() {
        return indication;
    }
}
