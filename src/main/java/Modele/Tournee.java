package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flavi on 2017/11/18.
 */
public class Tournee {
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

    public void addPointLivraisons (PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }


}
