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
        longueurTotale = 0;
        temps = 0;
    }

    public void addTroncon(Troncon troncon) {
        super.addTroncon(troncon);
        longueurTotale += troncon.getLongueur();
    }

    public void addNoeud(Noeud noeud) {
        super.addNoeud(noeud);
    }

    public void setPointLivraisonOrigine(PointLivraison pointLivraisonOrigine) {
        this.pointLivraisonOrigine = pointLivraisonOrigine;
    }

    public void setPointLivraisonDestination(PointLivraison pointLivraisonDestination) {
        this.pointLivraisonDestination = pointLivraisonDestination;
    }

}
