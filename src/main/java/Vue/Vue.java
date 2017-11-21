package Vue;

import Algo.AbstractGraphe;
import Algo.Dijkstra;
import Algo.Edge;
import Algo.Vertex;
import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.*;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by flavi on 2017/11/18.
 */
public class Vue {
    public static void main(String[] args) {
        ChargeurPlan chargeurPlan = ChargeurPlan.getInstance();
        chargeurPlan.parse(new Plan(), "planLyonPetit.xml");
        ChargeurLivraison chargeurLivraison = ChargeurLivraison.getInstance();
        chargeurLivraison.parse(new Tournee(), "DLpetit3.xml");
        AbstractGraphe abstractGraphe = new AbstractGraphe(chargeurPlan.getPlan(), chargeurLivraison.getTournee());
        abstractGraphe.getItineraire();
        System.out.println(abstractGraphe.getTournee().getListeItineraires());
//        PointLivraison noeud1=new PointLivraison(1L,1,1,1D);
//        PointLivraison noeud2=new PointLivraison(2L,1,1,1D);
//        PointLivraison noeud3=new PointLivraison(3L,1,1,1D);
//        PointLivraison noeud0=new PointLivraison(0L,1,1,1D);
//        Noeud noeud4=new Noeud(4L,1,1);
//        noeud0.addNeighbor(new Troncon(noeud0,noeud1,100,""));
//        noeud0.addNeighbor(new Troncon(noeud0,noeud2,30,""));
//        noeud0.addNeighbor(new Troncon(noeud0,noeud4,10,""));
//        noeud2.addNeighbor(new Troncon(noeud2,noeud1,60,""));
//        noeud2.addNeighbor(new Troncon(noeud2,noeud3,60,""));
//        noeud3.addNeighbor(new Troncon(noeud3,noeud1,10,""));
//        noeud4.addNeighbor(new Troncon(noeud4,noeud3,50,""));
//        Dijkstra dijkstra=new Dijkstra();
//        dijkstra.chercheDistanceMin(noeud0,noeud2);
//        System.out.println(dijkstra.getMeilleurItineraire());
//        System.out.println(dijkstra.getMeilleurItineraire().getListeNoeuds());

    }

}
