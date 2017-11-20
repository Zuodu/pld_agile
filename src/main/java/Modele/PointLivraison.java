package Modele;

import javafx.beans.Observable;

/**
 * Created by flavi on 2017/11/18.
 */
public class PointLivraison extends Noeud {
    private double debutPlage;
    private double finPlage;
    private double duree;

    public double getDebutPlage() {
        return debutPlage;
    }

    public double getFinPlage() {
        return finPlage;
    }

    public PointLivraison(Long id, int x, int y, double duree,double debutPlage, double finPlage) {

        super(id, x, y);
        this.duree=duree;
        this.debutPlage = debutPlage;
        this.finPlage = finPlage;
    }

    public double getDuree() {
        return duree;
    }

    public String toString() {
        return "Point livraison " +
                id + " " +
                x + " " +
                y + " " +
                duree + " " +
                debutPlage + " " +
                finPlage + " ";
    }
}
