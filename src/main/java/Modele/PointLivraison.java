package Modele;


/**
 * @author H4401
 * Classe représentant l'élément PointLivraison d'un plan, ne contient que des Get et des Set.
 */
public class PointLivraison extends Noeud {
    private double debutPlage;
    private double finPlage;
    private double duree;
    private double heureDepart;
    private double heureArrivee;

    /**
     * Get
     * @return debutPage
     */
    public double getDebutPlage() {
        return debutPlage;
    }

    /**
     * Get
     * @return finPage
     */
    public double getFinPlage() {
        return finPlage;
    }

    /**
     * Get
     * @return heureDepart
     */
    public double getHeureDepart() {
        return heureDepart;
    }

    /**
     * Set
     * @param heureDepart
     */
    public void setHeureDepart(double heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Get
     * @return heureArrivee
     */
    public double getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * Set
     * @param heureArrivee
     */
    public void setHeureArrivee(double heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

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
        this.debutPlage = -1;
        this.finPlage = -1;
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

