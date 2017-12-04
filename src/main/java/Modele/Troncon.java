package Modele;

/**
 * @author H4401
 * Classe repr�sentant l'�l�ment Tron�on d'un plan, ne contient que des Get.
 */
public class Troncon {
    private Noeud origine;
    private Noeud destination;
    private double longueur;
    private String nomRue;

    /**
     * Get
     * @return origine
     */
    public Noeud getOrigine() {
        return origine;
    }

    /**
     * Get
     * @return destination
     */
    public Noeud getDestination() {
        return destination;
    }

    /**
     * Get
     * @return longueur
     */
    public double getLongueur() {
        return longueur;
    }

    /**
     * Get
     * @return nomRue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Constructeur 
     * @param origine
     * @param destination
     * @param longueur
     * @param nomRue
     */
    public Troncon(Noeud origine, Noeud destination, double longueur, String nomRue) {

        this.origine = origine;
        this.destination = destination;
        this.longueur = longueur;
        this.nomRue = nomRue;
    }

    public Troncon(Troncon unTroncon){
        this(new Noeud(unTroncon.getOrigine()),new Noeud(unTroncon.getDestination()),unTroncon.getLongueur(),unTroncon.getNomRue());
    }

    public String toString() {
        return "Troncon " + origine.getId() + " " + destination.getId() + " " + nomRue + " " + longueur;
    }
}
