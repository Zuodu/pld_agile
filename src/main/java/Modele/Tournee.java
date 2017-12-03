package Modele;

import java.util.*;

/**
 * @author H4401
 *         Classe représentant une Tournée, extends Observable, contient des Set, des get, des add
 *         et une méthode indiquant à l'observeur la fin d'ajouts de points de livraison à la tournée.
 */
public class Tournee extends Observable {
    HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    private PointLivraison entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;

    /**
     * Constructeur
     *
     * @param entrepot
     * @param heureDeDepart
     */
    public Tournee(PointLivraison entrepot, double heureDeDepart) {
        this.entrepot = entrepot;
        this.heureDeDepart = heureDeDepart;
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public Tournee() {
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> getItinerairesMap() {
        return itinerairesMap;
    }

    public void setItinerairesMap(HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        this.itinerairesMap = itinerairesMap;
    }

    /**
     * Ajout d'un point de livraison
     *
     * @param pointLivraison
     */
    public void addPointLivraisons(PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }


    /**
     * Set
     *
     * @param heureDeDepart
     */
    public void setHeureDeDepart(double heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }


    /**
     * Set
     *
     * @param entrepot
     */
    public void setEntrepot(PointLivraison entrepot) {
        this.entrepot = entrepot;
    }


    /**
     * Set
     *
     * @return
     */
    public PointLivraison getEntrepot() {
        return entrepot;
    }

    /**
     * Get
     *
     * @return listePointLivraisons
     */
    public List<PointLivraison> getListePointLivraisons() {
        return listePointLivraisons;
    }

    /**
     * Get
     *
     * @return heureDeDepart
     */
    public double getHeureDeDepart() {
        return heureDeDepart;
    }


    public void setListePointLivraisons(List<PointLivraison> listePointLivraisons) {
        this.listePointLivraisons = listePointLivraisons;
    }

    public boolean updateHoraire(PointLivraison pointAModifier, Double plageDebut, Double plageFin) {
        double heureArrivee;
        double heureDepart;

        heureArrivee = pointAModifier.getHeureArrivee();
        if (heureArrivee < plageDebut) {
            heureArrivee = plageDebut;
        }
        heureDepart = heureArrivee + pointAModifier.getDuree();
        if (heureDepart > plageFin) {
            return false;
        } else if (heureDepart == pointAModifier.getHeureDepart()) {
            pointAModifier.setDebutPlage(plageDebut);
            pointAModifier.setFinPlage(plageFin);
            pointAModifier.setHeureArrivee(heureArrivee);
            return true;
        } else if (heureDepart < pointAModifier.getHeureDepart()) {
            if (avanceHoraire(listePointLivraisons.get(listePointLivraisons.indexOf(pointAModifier) + 1), pointAModifier.getHeureDepart() - heureDepart)) {
                pointAModifier.setDebutPlage(plageDebut);
                pointAModifier.setFinPlage(plageFin);
                pointAModifier.setHeureArrivee(heureArrivee);
                pointAModifier.setHeureDepart(heureDepart);
            }
            return true;
        } else {

        }
        return false;
    }

    public boolean avanceHoraire(PointLivraison nextPoint, double temps) {
        boolean changed = true;
        double heureArrivee;
        double heureDepart;
        heureArrivee = nextPoint.getHeureArrivee() - temps;
        if (heureArrivee < nextPoint.getDebutPlage()) heureArrivee = nextPoint.getDebutPlage();
        heureDepart = heureArrivee + nextPoint.getDuree();
        if (heureDepart == nextPoint.getHeureDepart()) {
            return true;
        } else if (avanceHoraire(listePointLivraisons.get(listePointLivraisons.indexOf(nextPoint) + 1), nextPoint.getHeureDepart() - heureDepart)) {
            nextPoint.setHeureArrivee(heureArrivee);
            nextPoint.setHeureDepart(heureDepart);
            return true;
        }
        return false;
    }




    /**
     * Signale la fin des ajouts de points de livraisons à la tournée
     */

    public void SignalerFinDajoutPointsLivraisons() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "itinerairesMap=" + itinerairesMap +
                ", entrepot=" + entrepot +
                ", listePointLivraisons=" + listePointLivraisons +
                ", heureDeDepart=" + heureDeDepart +
                '}';
    }
}
