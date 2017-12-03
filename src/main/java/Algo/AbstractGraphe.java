package Algo;

import Modele.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class AbstractGraphe {
    private double[][] cout;
    private double[] duree;
    private Double[] plageArrivee;
    private Double[] plageDepart;
    HashMap<Integer, PointLivraison> pointLivraisonMap;
    HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    Tournee tournee;
    int nbSommets;

    public AbstractGraphe(Plan plan, Tournee tournee) {
        this.tournee = tournee;
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
        this.pointLivraisonMap = new HashMap<Integer, PointLivraison>();
        int i = 0;
        pointLivraisonMap.put(i++, tournee.getEntrepot());
        //TODO:区分entrepot和pointlivraison
        for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
            pointLivraisonMap.put(i++, pointLivraison);
        }
        this.nbSommets = pointLivraisonMap.size();
        this.cout = new double[nbSommets][nbSommets];
        this.duree = new double[nbSommets];
        this.plageArrivee = new Double[nbSommets];
        this.plageDepart = new Double[nbSommets];
        generateTableCout();
        generateTableDuree();
        generateTablePlageDepart();
        generateTablePlageArrivee();
    }

    private void generateTableCout() {
        for (int m = 0; m < cout.length; m++) {
            for (int n = 0; n < cout[m].length; n++) {
                if (m == n) {
                    cout[m][n] = Double.MAX_VALUE;
                    continue;
                }
                Dijkstra dijkstra = new Dijkstra();
                dijkstra.chercheDistanceMin(pointLivraisonMap.get(m), pointLivraisonMap.get(n));
                itinerairesMap.put(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(m), pointLivraisonMap.get(n)), dijkstra.getMeilleurItineraire());
                cout[m][n] = dijkstra.getMeilleurItineraire().getLongueurTotale() / tournee.getVitesse();
            }
        }
    }

    private void generateTableDuree() {
        for (int i = 0; i < cout.length; i++) {
            duree[i] = pointLivraisonMap.get(i).getDuree();
        }
    }

    private void generateTablePlageDepart() {
        for (int i = 0; i < nbSommets; i++) {
            if(pointLivraisonMap.get(i).getFinPlage()!=null)
                plageDepart[i]=pointLivraisonMap.get(i).getFinPlage();
            else
                plageDepart[i]=null;
        }
    }

    private void generateTablePlageArrivee() {
        for (int i = 0; i < nbSommets; i++) {
            if(pointLivraisonMap.get(i).getDebutPlage()!=null)
                plageArrivee[i]=pointLivraisonMap.get(i).getDebutPlage();
            else
                plageArrivee[i]=null;
        }
    }

    public void getItineraire() {
        long tempsDebut=System.currentTimeMillis();
        TSP tsp = new TSP3();
        tsp.chercheSolution(tournee.getHeureDeDepart(),10, nbSommets, cout, duree,plageArrivee,plageDepart);
        List<PointLivraison> pointLivraisons=new ArrayList<PointLivraison>();
        for (int i = 0; i < nbSommets - 1; i++) {
            tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(tsp.getMeilleureSolution(i)), pointLivraisonMap.get(tsp.getMeilleureSolution(i + 1)))));
        }
        tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(tsp.getMeilleureSolution(nbSommets - 1)), pointLivraisonMap.get(0))));
        for (int i = 0; i < nbSommets; i++) {
            pointLivraisons.add(pointLivraisonMap.get(tsp.getMeilleureSolution(i)));
            pointLivraisonMap.get(tsp.getMeilleureSolution(i)).setHeureArrivee(tsp.getHoraireLivraison().get(i).getKey());
            pointLivraisonMap.get(tsp.getMeilleureSolution(i)).setHeureDepart(tsp.getHoraireLivraison().get(i).getValue());
        }
        tournee.setListePointLivraisons(pointLivraisons);
        System.out.println(tsp.getCoutMeilleureSolution());
        System.out.println(tournee);
        System.out.println(System.currentTimeMillis()-tempsDebut);


    }

//    public void getItineraireGlouton() {
//        Glouton glouton = new Glouton(nbSommets);
//        glouton.chercheSolution(cout, duree);
//        for (int i = 0; i < nbSommets - 1; i++) {
//            tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(glouton.getMeilleureSolution(i)), pointLivraisonMap.get(glouton.getMeilleureSolution(i + 1)))));
//        }
//        tournee.addItineraire(itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointLivraisonMap.get(glouton.getMeilleureSolution(nbSommets - 1)), pointLivraisonMap.get(0))));
//    }

    public Tournee getTournee() {
        return tournee;
    }
}
