package Modele;


/**
 * @author H4401
 *         Classe représentant l'élément PointLivraison d'un plan, ne contient que des Get et des Set.
 */
public class PointLivraison extends Noeud {
    private Double debutPlage;
    private Double finPlage;
    private double duree;
    private Double heureArrivee;
    private Double heureDepart;

    /**
     * Méthode permettant de récupérer l'heure d'arrivée à un point de livraison
     *
     * @return heureArrivée
     */
    public Double getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Double heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    /**
     * Get
     *
     * @return heureDepart
     */
    public Double getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Double heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Get
     *
     * @return debutPlage
     */
    public Double getDebutPlage() {
        return debutPlage;
    }

    public void setDebutPlage(Double debutPlage) {
        this.debutPlage = debutPlage;
    }

    /**
     * Get
     *
     * @return finPage
     */
    public Double getFinPlage() {
        return finPlage;
    }

    public void setFinPlage(Double finPlage) {
        this.finPlage = finPlage;
    }

    /**
     * Constructeur avec plage horaire
     *
     * @param id
     * @param x
     * @param y
     * @param duree
     * @param debutPlage
     * @param finPlage
     */
    public PointLivraison(Long id, int x, int y, double duree, Double debutPlage, Double finPlage) {

        super(id, x, y);
        this.duree = duree;
        this.debutPlage = debutPlage;
        this.finPlage = finPlage;
    }

    public PointLivraison(PointLivraison unPointLivraison) {
        super(unPointLivraison);
        this.duree = unPointLivraison.getDuree();
        this.heureDepart = unPointLivraison.getHeureDepart();
        this.heureArrivee = unPointLivraison.getHeureArrivee();
        if (unPointLivraison.getDebutPlage() != null) {
            this.debutPlage = unPointLivraison.getDebutPlage();
            this.finPlage = unPointLivraison.getFinPlage();
        }
    }

    /**
     * Constructeur sans plage horaire
     *
     * @param id
     * @param x
     * @param y
     * @param duree
     */
    public PointLivraison(Long id, int x, int y, double duree) {
        super(id, x, y);
        this.duree = duree;
    }

    /**
     * Get
     *
     * @return duree
     */
    public double getDuree() {
        return duree;
    }

    /**
     * Set
     *
     * @return duree
     */
    public void setDuree(double d) {
        duree = d;
    }

    @Override
    public String toString() {
        return "PointLivraison{" +
                "id=" + id +
                "debutPlage=" + debutPlage +
                ", finPlage=" + finPlage +
                ", duree=" + duree +
                '}';
    }

}

