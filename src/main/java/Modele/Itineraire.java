package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Itineraire extends Plan {
    private PointLivraison pointLivraisonOrigine;
    private PointLivraison pointLivraisonDestination;
    private double longueurTotale;
    private double temps;


    public Itineraire() {
        super();
    }

    public void addTroncon(Troncon troncon) {
        listeTroncons.add(troncon);
        longueurTotale += troncon.getLongueur();
    }

    public void setPointLivraisonOrigine(PointLivraison pointLivraisonOrigine) {
        this.pointLivraisonOrigine = pointLivraisonOrigine;
    }

    public void setPointLivraisonDestination(PointLivraison pointLivraisonDestination) {
        this.pointLivraisonDestination = pointLivraisonDestination;
    }

    public void setLongueurTotale(double longueurTotale) {
        this.longueurTotale = longueurTotale;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }
}
