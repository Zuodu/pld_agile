package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Itineraire extends Plan {
    private PointLivraison pointLivraisonOrigine;
    private PointLivraison pointLivraisonDestination;
    private double longueurTotale;
    private double temps;


    public Itineraire(PointLivraison pointLivraisonOrigine, PointLivraison pointLivraisonDestination) {
        super();
        this.pointLivraisonDestination=pointLivraisonDestination;
        this.pointLivraisonOrigine=pointLivraisonOrigine;
    }

    public void addTroncon(Troncon troncon) {
        listeTroncons.add(troncon);
        longueurTotale+=troncon.getLongueur();
    }

}
