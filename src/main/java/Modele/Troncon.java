package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Troncon {
    private Noeud origine;
    private Noeud destination;
    private double longueur;
    private String nomRue;

    public Noeud getOrigine() {
        return origine;
    }

    public Noeud getDestination() {
        return destination;
    }

    public double getLongueur() {
        return longueur;
    }

    public String getNomRue() {
        return nomRue;
    }

    public Troncon(Noeud origine, Noeud destination, double longueur, String nomRue) {

        this.origine = origine;
        this.destination = destination;
        this.longueur = longueur;
        this.nomRue = nomRue;
    }

    public String toString() {
        return "Troncon " + origine.getId() + " " + destination.getId() + " " + nomRue + " " + longueur;
    }
}
