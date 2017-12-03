package FeuilleDeRoute;

import java.util.ArrayList;

/**
 * @author H4401
 * La classe LigneFeuille sert à encapsuler les données lors de la génération de la feuille de route.
 */
public class LigneFeuille {

    private String hDepart;
    private String hArrivee;
    private String indication;
    private String rue;
    private String longueur;

    /**
     * COnstructeur d'une ligne de feuille de route
     *
     * @param arrivee heure d'arrivée (Itinéraire)
     * @param depart  heure de départ (Itinéraire)
     * @param indic   indication à suivre
     * @param r       nom de la rue
     * @param l       longueur de la rue à suivre
     */
    public LigneFeuille(double arrivee, double depart, String indic, String r, double l) {
        if (depart == 0) this.hDepart = "-";
        else {
            int h = (int) (depart / 3600);
            int m = (int) ((depart % 3600) / 60);
            int s = (int) (depart % 60);
            this.hDepart = h + ":" + m + ":" + s;
        }
        if (arrivee == 0) this.hArrivee = "-";
        else {
            int h = (int) (arrivee / 3600);
            int m = (int) ((arrivee % 3600) / 60);
            int s = (int) (arrivee % 60);
            this.hArrivee = h + ":" + m + ":" + s;
        }
        this.indication = indic == null ? "-" : indic;
        this.rue = r == null ? "-" : r;
        this.longueur = l == 0 ? "-" : Double.toString(l);
    }

    /**
     * Getter heure départ sous la forme d'un String
     *
     * @return heure de départ
     */
    public String gethDepart() {
        return hDepart;
    }

    /**
     * Getter heure d'arrivée sous la forme d'un String
     *
     * @return heure d'arrivée
     */
    public String gethArrivee() {
        return hArrivee;
    }

    /**
     * Getter indication à suivre sous la forme d'un String
     *
     * @return indication (par exemple, "tournez à gauche")
     */
    public String getIndication() {
        return indication;
    }

    /**
     * Getter nom de la rue sous la forme d'un String
     *
     * @return nom de la rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Getter longueur de la rue sous la forme d'un String
     *
     * @return longueur de la rue
     */
    public String getLongueur() {
        return longueur;
    }
}
