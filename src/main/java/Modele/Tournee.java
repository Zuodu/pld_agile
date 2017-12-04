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
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public Tournee() {
        listePointLivraisons = new ArrayList<PointLivraison>();
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
    }

    public void addItineraire(Map.Entry<PointLivraison, PointLivraison> entry, Itineraire itineraire) {
        itinerairesMap.put(entry, itineraire);
    }

    public HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> getItinerairesMap() {
        return itinerairesMap;
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


    public boolean supprimerPoint(PointLivraison pointASupprimer) {
        int index = listePointLivraisons.indexOf(pointASupprimer);
        return false;
    }

    public boolean updateHoraire(PointLivraison pointAModifier, Double plageDebut, Double plageFin) {
        double heureArrivee;
        double heureDepart;

        heureArrivee = pointAModifier.getHeureArrivee();
        heureDepart = heureArrivee + pointAModifier.getDuree();
        if (heureArrivee < plageDebut) {
            heureDepart = plageDebut + pointAModifier.getDuree();
        }
        if (heureDepart > plageFin) {
            return false;
        } else if (heureDepart == pointAModifier.getHeureDepart() || listePointLivraisons.indexOf(pointAModifier) == listePointLivraisons.size() - 1) {
            pointAModifier.setDebutPlage(plageDebut);
            pointAModifier.setFinPlage(plageFin);
            setChanged();
            notifyObservers();
            return true;
        } else if (heureDepart < pointAModifier.getHeureDepart()) {
            if (avanceHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, pointAModifier.getHeureDepart() - heureDepart)) {
                pointAModifier.setDebutPlage(plageDebut);
                pointAModifier.setFinPlage(plageFin);
                pointAModifier.setHeureDepart(heureDepart);
            }
            setChanged();
            notifyObservers();
            return true;
        } else if (retardeHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, heureDepart - pointAModifier.getHeureDepart())) {
            pointAModifier.setDebutPlage(plageDebut);
            pointAModifier.setFinPlage(plageFin);
            pointAModifier.setHeureDepart(heureDepart);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;

    }

    private boolean avanceHoraire(int index, double temps) {
        PointLivraison nextPoint = listePointLivraisons.get(index);
        double heureArrivee;
        double heureDepart;
        heureArrivee = nextPoint.getHeureArrivee() - temps;
        heureDepart = heureArrivee + nextPoint.getDuree();
        if (nextPoint.getDebutPlage() != null) {
            if (heureArrivee < nextPoint.getDebutPlage()) {
                heureDepart = nextPoint.getDebutPlage() + nextPoint.getDuree();
            }
        }
        if (heureDepart == nextPoint.getHeureDepart() || index == listePointLivraisons.size() - 1) {
            nextPoint.setHeureArrivee(heureArrivee);
            return true;
        } else if (avanceHoraire(index + 1, nextPoint.getHeureDepart() - heureDepart)) {
            nextPoint.setHeureArrivee(heureArrivee);
            nextPoint.setHeureDepart(heureDepart);
            return true;
        }
        return false;
    }

    private boolean retardeHoraire(int index, double temps) {
        PointLivraison nextPoint = listePointLivraisons.get(index);
        double heureArrivee;
        double heureDepart;
        heureArrivee = nextPoint.getHeureArrivee() + temps;
        heureDepart = heureArrivee + nextPoint.getDuree();
        if (nextPoint.getDebutPlage() != null) {
            if (heureArrivee < nextPoint.getDebutPlage()) {
                heureDepart = nextPoint.getDebutPlage() + nextPoint.getDuree();
            }
        }
        if (heureDepart == nextPoint.getHeureDepart() || index == listePointLivraisons.size() - 1) {
            nextPoint.setHeureArrivee(heureArrivee);
            return true;
        } else if (nextPoint.getFinPlage() != null) {
            if (heureDepart > nextPoint.getFinPlage()) {
                return false;
            }
        } else if (retardeHoraire(index + 1, heureDepart - nextPoint.getHeureDepart())) {
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
