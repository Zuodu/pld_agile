package Algo;

import Modele.Itineraire;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Tournee;

import java.util.*;

/**
 * Created by flavi on 2017/11/18.
 */
public class ChercherSolution {
    private double[][] cout;
    private double[] duree;
    private Double[] plageArrivee;
    private Double[] plageDepart;
    HashMap<Integer, PointLivraison> pointLivraisonMap;
    HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    Tournee tournee;
    int nbSommets;
    TSP tsp;

    public ChercherSolution(Plan plan, Tournee tournee) {
        this.tournee = tournee;
        this.itinerairesMap = new HashMap<>();
        this.pointLivraisonMap = new HashMap<>();
        int i = 0;
        pointLivraisonMap.put(i++, tournee.getEntrepot());
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
                itinerairesMap.put(new AbstractMap.SimpleEntry<>(pointLivraisonMap.get(m), pointLivraisonMap.get(n)), dijkstra.getMeilleurItineraire());
                cout[m][n] = dijkstra.getMeilleurItineraire().getTemps();
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
            if (pointLivraisonMap.get(i).getFinPlage() != null)
                plageDepart[i] = pointLivraisonMap.get(i).getFinPlage();
            else
                plageDepart[i] = null;
        }
    }

    private void generateTablePlageArrivee() {
        for (int i = 0; i < nbSommets; i++) {
            if (pointLivraisonMap.get(i).getDebutPlage() != null)
                plageArrivee[i] = pointLivraisonMap.get(i).getDebutPlage();
            else
                plageArrivee[i] = null;
        }
    }

//    public double getPremiereSolution(double heureDeDepart, int tpsLimite, int nbSommets, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart){
//        TSP tsp4 = new TSP4();
//        tsp4.chercheSolution(Double.MAX_VALUE,heureDeDepart,tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
//        return tsp4.getCoutMeilleureSolution();
//    }

    public void chercheSolution() {
        long tempsDebut = System.currentTimeMillis();
        List<PointLivraison> pointLivraisons = new ArrayList<>();

        tsp.chercheSolution(Double.MAX_VALUE, tournee.getHeureDeDepart(), 30000, nbSommets, cout, duree, plageArrivee, plageDepart);

        for (int i = 0; i < nbSommets - 1; i++) {
            Map.Entry<PointLivraison, PointLivraison> entry = new AbstractMap.SimpleEntry<>(pointLivraisonMap.get(tsp.getMeilleureSolution(i)), pointLivraisonMap.get(tsp.getMeilleureSolution(i + 1)));
            tournee.addItineraire(entry, itinerairesMap.get(entry));
        }
        Map.Entry<PointLivraison, PointLivraison> entry = new AbstractMap.SimpleEntry<>(pointLivraisonMap.get(tsp.getMeilleureSolution(nbSommets - 1)), pointLivraisonMap.get(0));
        tournee.addItineraire(entry, itinerairesMap.get(entry));


        for (int i = 0; i < nbSommets; i++) {
            pointLivraisons.add(pointLivraisonMap.get(tsp.getMeilleureSolution(i)));
            pointLivraisonMap.get(tsp.getMeilleureSolution(i)).setHeureArrivee(tsp.getHoraireLivraison().get(i).getKey());
            pointLivraisonMap.get(tsp.getMeilleureSolution(i)).setHeureDepart(tsp.getHoraireLivraison().get(i).getValue());
        }
        pointLivraisons.add(tournee.getEntrepot());
        tournee.getEntrepot().setHeureArrivee(tsp.getHoraireLivraison().get(nbSommets).getKey());
        tournee.getEntrepot().setHeureDepart(tournee.getHeureDeDepart());
        tournee.setListePointLivraisons(pointLivraisons);


        if (tsp.getTempsLimiteAtteint()) System.out.println("Temps limite atteint");
        System.out.println(tsp.getCoutMeilleureSolution());
        System.out.println(tournee);
        System.out.println(System.currentTimeMillis() - tempsDebut);
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

    public Itineraire getItineraire(PointLivraison pointDepart, PointLivraison pointArrivee) {
        return itinerairesMap.get(new AbstractMap.SimpleEntry<>(pointDepart, pointArrivee));
    }

    public void setTsp(TSP tsp) {
        this.tsp = tsp;
    }

    /**
     * @return the cout
     */
    public double[][] getTableCout() {
        return cout;
    }

    /**
     * @return the duree
     */
    public double[] getTableDuree() {
        return duree;
    }

    /**
     * @return the plageArrivee
     */
    public Double[] getTablePlageArrivee() {
        return plageArrivee;
    }

    /**
     * @return the plageDepart
     */
    public Double[] getTablePlageDepart() {
        return plageDepart;
    }
}
