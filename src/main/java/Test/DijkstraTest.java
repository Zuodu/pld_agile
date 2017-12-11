package Test;

import Algo.Dijkstra;
import Modele.Itineraire;
import Modele.Noeud;
import Modele.Troncon;
import org.junit.Test;

/**
 * Created by siyingjiang on 2017/12/11.
 */
public class DijkstraTest {

    private Noeud noeud0, noeud1, noeud2, noeud3, noeud4;

    public void InitTU() {
        noeud0 = new Noeud(0L, 1, 1);
        noeud1 = new Noeud(1L, 1, 1);
        noeud2 = new Noeud(2L, 1, 1);
        noeud3 = new Noeud(3L, 1, 1);
        noeud4 = new Noeud(4L, 1, 1);
        Troncon troncon01 = new Troncon(noeud0, noeud1, 100, "");
        Troncon troncon02 = new Troncon(noeud0, noeud2, 30, "");
        Troncon troncon04 = new Troncon(noeud0, noeud4, 10, "");
        Troncon troncon21 = new Troncon(noeud2, noeud1, 60, "");
        Troncon troncon23 = new Troncon(noeud2, noeud3, 60, "");
        Troncon troncon31 = new Troncon(noeud3, noeud1, 10, "");
        Troncon troncon43 = new Troncon(noeud4, noeud3, 50, "");
        noeud0.addNeighbor(troncon01);
        noeud0.addNeighbor(troncon02);
        noeud0.addNeighbor(troncon04);
        noeud2.addNeighbor(troncon21);
        noeud2.addNeighbor(troncon23);
        noeud3.addNeighbor(troncon31);
        noeud4.addNeighbor(troncon43);
    }

    @Test //Calcul du plus court chemin de 0 vers 1
    public void TU1() {
        InitTU();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.chercheDistanceMin(noeud0,noeud1);
        assert(dijkstra.getMeilleurItineraire().getLongueurTotale()==70);
    }

    @Test //Calcul du plus court chemin de 0 vers 2
    public void TU2() {
        InitTU();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.chercheDistanceMin(noeud0,noeud2);
        assert(dijkstra.getMeilleurItineraire().getLongueurTotale()==30);
    }

    @Test //Calcul du plus court chemin de 0 vers 3
    public void TU3() {
        InitTU();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.chercheDistanceMin(noeud0,noeud3);
        assert(dijkstra.getMeilleurItineraire().getLongueurTotale()==60);
    }

    @Test //Calcul du plus court chemin de 0 vers 4
    public void TU4() {
        InitTU();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.chercheDistanceMin(noeud0,noeud4);
        assert(dijkstra.getMeilleurItineraire().getLongueurTotale()==10);
    }


}


