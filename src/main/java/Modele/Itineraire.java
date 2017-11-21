package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Itineraire extends Plan {
    private Noeud noeudOrigine;
    private Noeud noeudDestination;
    private double longueurTotale;
    private double temps;


    public Itineraire() {
        super();
        longueurTotale = 0;
        temps = 0;
    }

    public void addTroncon(Troncon troncon) {
        super.addTroncon(troncon);
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

    @Override
    public String toString() {
        return "Itineraire{" +
                "noeudOrigine=" + noeudOrigine +
                ", noeudDestination=" + noeudDestination +
                ", longueurTotale=" + longueurTotale +
                ", temps=" + temps +
                '}';
    }
}
