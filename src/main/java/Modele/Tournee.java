package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author H4401
 * Classe représentant une Tournée, extends Observable, contient des Set, des get, des add
 * et une méthode indiquant à l'observeur la fin d'ajouts de points de livraison à la tournée.
 */
public class Tournee extends Observable {
    private static final double vitesse = 15 * 1000 / 60 / 60;
    private List<Itineraire> listeItineraires;
    private PointLivraison entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;

    /**
     * Constructeur
     * @param entrepot
     * @param heureDeDepart
     */
    public Tournee(PointLivraison entrepot, double heureDeDepart) {
        this.entrepot = entrepot;
        this.heureDeDepart = heureDeDepart;
        listeItineraires = new ArrayList<Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public Tournee() {
        listeItineraires = new ArrayList<Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    /**
     * Get
     * @return vitesse
     */
    public static double getVitesse() {
        return vitesse;
    }

    /**
     * Ajout d'un point de livraison
     * @param pointLivraison
     */
    public void addPointLivraisons(PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }

    /**
     * Get
     * @return listeItinéraire
     */
    public List<Itineraire> getListeItineraires() {
        return listeItineraires;
    }

    /**
     * Ajout d'un itinéraire
     * @param itineraire
     */
    public void addItineraire(Itineraire itineraire) {
        listeItineraires.add(itineraire);
    }

    /**
     * Set
     * @param heureDeDepart
     */
    public void setHeureDeDepart(double heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }

    /**
     * Set
     * @param entrepot
     */
    public void setEntrepot(PointLivraison entrepot) {
        this.entrepot = entrepot;
    }

    /**
     * Set
     * @return
     */
    public PointLivraison getEntrepot() {
        return entrepot;
    }

    /**
     * Get
     * @return listePointLivraisons
     */
    public List<PointLivraison> getListePointLivraisons() {
        return listePointLivraisons;
    }

    /**
     * Get
     * @return heureDeDepart
     */
    public double getHeureDeDepart() {
        return heureDeDepart;
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
        String toReturn = "";
        for (Itineraire itineraire : listeItineraires) {
            toReturn += itineraire.toString();
        }
        return toReturn;
    }
}
