package Modele;


/**
 * @author H4401
 * Classe représentant l'élément PointLivraison d'un plan, ne contient que des Get et des Set.
 */
public class PointLivraison extends Noeud {
    private Double debutPlage;
    private Double finPlage;
    private double duree;
    private double heureArrivee;
    private double heureDepart;


    public double getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(double heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public double getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(double heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Get
     * @return debutPage
     */
    public Double getDebutPlage() {
        return debutPlage;
    }

    /**
     * Get
     * @return finPage
     */
    public Double getFinPlage() {
        return finPlage;
    }

    /**
     * Get
     * @return heureDepart
     */

    /**
     * Constructeur avec plage horaire
     * @param id
     * @param x
     * @param y
     * @param duree
     * @param debutPlage
     * @param finPlage
     */
    public PointLivraison(Long id, int x, int y, double duree, double debutPlage, double finPlage) {

        super(id, x, y);
        this.duree=duree;
        this.debutPlage = debutPlage;
        this.finPlage = finPlage;
    }

    /**
     * Constructeur sans plage horaire
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
     * @return duree
     */
    public double getDuree() {
        return duree;
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

