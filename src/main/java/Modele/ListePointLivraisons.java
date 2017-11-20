package Modele;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

/**
 * Created by flavi on 2017/11/20.
 */
public class ListePointLivraisons extends Observable {
    private Set<PointLivraison> pointLivraisons;

    public ListePointLivraisons() {
        pointLivraisons = new HashSet<PointLivraison>();
    }

    public void addPointLivraisons(PointLivraison pointLivraison) {
        pointLivraisons.add(pointLivraison);
    }

    public Set<PointLivraison> getPointLivraisons() {
        return pointLivraisons;
    }

    public void SignalerFin() {
        setChanged();
        notifyObservers();
    }

}
