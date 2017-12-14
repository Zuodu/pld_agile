package Test;

import Modele.Itineraire;
import Modele.PointLivraison;
import Modele.Tournee;
import org.junit.Test;

import java.util.AbstractMap;

public class TourneeTest3 {
    private Tournee tournee;

    public void InitTU() {
        tournee = new Tournee();
    }

    @Test //Ajout d'un PointLivraison
    public void TU1() {
        InitTU();

        assert (tournee.getListePointLivraisons().isEmpty());

        PointLivraison point = new PointLivraison(25495299L, 56584, 58632, 900);
        boolean pointPresent = false;
        tournee.addPointLivraisons(point);

        for (PointLivraison p : tournee.getListePointLivraisons()) {
            if (p.getId() == (long) point.getId() && point.getX() == point.getX() && point.getY() == point.getY() && point.getDuree() == point.getDuree())
                pointPresent = true;
        }

        assert (tournee.getListePointLivraisons().size() == 1);
        assert (pointPresent);
    }

    @Test //Ajout d'un Itineraire
    public void TU2() {
        InitTU();

        assert (tournee.getItinerairesMap().isEmpty());

        Itineraire itineraire = new Itineraire();
        PointLivraison point1 = new PointLivraison(25495299L, 56584, 58632, 900);
        PointLivraison point2 = new PointLivraison(54803121L, 195365, 250340, 300, 39600.0, 46800.0);
        tournee.addItineraire(new AbstractMap.SimpleEntry<>(point1, point2), itineraire);

        assert (tournee.getItinerairesMap().size() == 1);
        assert (tournee.getItinerairesMap().get(new AbstractMap.SimpleEntry<>(point1, point2)).equals(itineraire));
    }

    @Test //Test d'une contruction par copie
    public void TU3() {
        InitTU();

        assert (tournee.getItinerairesMap().isEmpty());

        PointLivraison point = new PointLivraison(25495299L, 56584, 58632, 900);
        PointLivraison entrepot = new PointLivraison(25395299L, 56484, 58672, 900);
        tournee.addPointLivraisons(entrepot);
        tournee.addPointLivraisons(point);
        tournee.addPointLivraisons(entrepot);
        tournee.setEntrepot(entrepot);
        Tournee tourneeCopie = new Tournee();
        tourneeCopie.clone(tournee, tourneeCopie);

        assert (tournee.getListePointLivraisons().size() == 3);
        assert (tourneeCopie.getListePointLivraisons().size() == 3);

        tournee.getListePointLivraisons().clear();

        assert (tourneeCopie.getListePointLivraisons().size() == 3);
        assert (tournee.getListePointLivraisons().size() == 0);
    }
}
