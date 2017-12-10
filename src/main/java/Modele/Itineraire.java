package Modele;

import Main.main;

import java.util.LinkedList;

/**
 * @author H4401
 * Classe représentant un itinéraire, ne contient que des Get, Set et add.
 */
public class Itineraire {
    private Noeud noeudOrigine;
    private Noeud noeudDestination;
    private LinkedList<Troncon> listeTroncons;
    private double longueurTotale;
    private double temps;

    /**
     * Constructeur par défaut
     */
    public Itineraire() {
        listeTroncons = new LinkedList<Troncon>();
        longueurTotale = 0;
        temps = 0;
    }

    /**
     * Constructeur par copie
     * @param unItineraire
     */
    public Itineraire(Itineraire unItineraire){
        this();
        this.setNoeudOrigine(unItineraire.getNoeudOrigine());
        this.setNoeudDestination(unItineraire.getNoeudDestination());
        for(Troncon troncon:unItineraire.getListeTroncons()){
            this.addTroncon(troncon);
        }
    }

    /**
     * Ajoute un tron�on
     *
     * @param troncon
     */
    public void addTroncon(Troncon troncon) {
        listeTroncons.addFirst(troncon);
        longueurTotale += troncon.getLongueur();
        temps += troncon.getLongueur() / main.VITESSE;
    }

    /**
     * Méthode Get
     * @return temps
     */
    public double getTemps() {
        return temps;
    }

    /**
     * Get
     *
     * @return noeudOrigine
     */
    public Noeud getNoeudOrigine() {
        return noeudOrigine;
    }

    /**
     * Set
     *
     * @param noeudOrigine
     */
    public void setNoeudOrigine(Noeud noeudOrigine) {
        this.noeudOrigine = noeudOrigine;
    }

    /**
     * Get
     *
     * @return noeudDestination
     */
    public Noeud getNoeudDestination() {
        return noeudDestination;
    }

    /**
     * Set
     *
     * @param noeudDestination
     */
    public void setNoeudDestination(Noeud noeudDestination) {
        this.noeudDestination = noeudDestination;
    }

    /**
     * Get
     *
     * @return longueurTotale
     */
    public double getLongueurTotale() {
        return longueurTotale;
    }

    /**
     * Get
     *
     * @return listeTron�on
     */
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
