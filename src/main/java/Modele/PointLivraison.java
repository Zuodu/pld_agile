package Modele;


/**
 * Created by flavi on 2017/11/18.
 */
public class PointLivraison extends Noeud {
    private Double debutPlage;
    private Double finPlage;
    private double duree;

    public Double getDebutPlage() {
        return debutPlage;
    }

    public Double getFinPlage() {
        return finPlage;
    }


    public PointLivraison(Long id, int x, int y, double duree,double debutPlage, double finPlage) {

        super(id, x, y);
        this.duree=duree;
        this.debutPlage = debutPlage;
        this.finPlage = finPlage;
    }

    public PointLivraison(Long id, int x, int y, double duree) {
        super(id, x, y);
        this.duree = duree;
    }

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

