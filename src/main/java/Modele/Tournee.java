package Modele;

import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class Tournee extends Observable {
    private List<Itineraire> listeItineraires;
    private PointLivraison entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;
    private HashMap<PointLivraison, Map.Entry<Double, Double>> horaireLivraison;
    private static final double vitesse = 4.16;

    public Tournee(PointLivraison entrepot, double heureDeDepart) {
        this.entrepot = entrepot;
        this.heureDeDepart = heureDeDepart;
        horaireLivraison = new HashMap<PointLivraison, Map.Entry<Double, Double>>();
        listeItineraires = new ArrayList<Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public Tournee() {
        horaireLivraison = new HashMap<PointLivraison, Map.Entry<Double, Double>>();
        listeItineraires = new ArrayList<Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
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

    public HashMap<PointLivraison, Map.Entry<Double, Double>> getHoraireLivraison() {
        return horaireLivraison;
    }

    public void addHoraireLivraison(PointLivraison pointLivraison, Map.Entry<Double, Double> horaire) {
        this.horaireLivraison.put(pointLivraison, horaire);
    }

    public void setEntrepot(PointLivraison entrepot) {
        this.entrepot = entrepot;
    }

    public static double getVitesse() {
        return vitesse;
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
        toReturn += horaireLivraison.toString();
        return toReturn;
    }
}
