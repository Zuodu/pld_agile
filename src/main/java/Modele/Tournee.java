package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by flavi on 2017/11/18.
 */
public class Tournee extends Observable {
    private static final double vitesse = 15 * 1000 / 60 / 60;
    private List<Itineraire> listeItineraires;
    private PointLivraison entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;

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

    public static double getVitesse() {
        return vitesse;
    }

    public void addPointLivraisons(PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }

    public List<Itineraire> getListeItineraires() {
        return listeItineraires;
    }

    public void addItineraire(Itineraire itineraire) {
        listeItineraires.add(itineraire);
    }

    public void setHeureDeDepart(double heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }

    public void setEntrepot(PointLivraison entrepot) {
        this.entrepot = entrepot;
    }

    public PointLivraison getEntrepot() {
        return entrepot;
    }

    public List<PointLivraison> getListePointLivraisons() {
        return listePointLivraisons;
    }

    public double getHeureDeDepart() {
        return heureDeDepart;
    }

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
