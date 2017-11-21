package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by flavi on 2017/11/18.
 */
public class Tournee extends Observable {
    private List<Itineraire> listeItineraires;
    private Noeud entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;

    public Tournee(Noeud entrepot, double heureDeDepart) {
        this.entrepot = entrepot;
        this.heureDeDepart = heureDeDepart;
        listeItineraires=new ArrayList<Itineraire>();
        listePointLivraisons=new ArrayList<PointLivraison>();
    }

    public Tournee() {
        listeItineraires = new ArrayList<Itineraire>();
        listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public void addPointLivraisons (PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }

    public List<Itineraire> getListeItineraires() {
        return listeItineraires;
    }

    public void setHeureDeDepart(double heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }

    public void setEntrepot(Noeud entrepot) {
        this.entrepot = entrepot;
    }

    public Noeud getEntrepot() {
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
}
