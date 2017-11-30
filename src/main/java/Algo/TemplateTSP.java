package Algo;

import Modele.Itineraire;
import Modele.PointLivraison;

import java.util.*;

public abstract class TemplateTSP implements Algo.TSP {

    private PointLivraison[] meilleureSolution;
    private double coutMeilleureSolution;
    private Boolean tempsLimiteAtteint;
    private ArrayList<Map.Entry<Double, Double>> horaireMeilleureSolution;

    public Boolean getTempsLimiteAtteint() {
        return tempsLimiteAtteint;
    }

    public void chercheSolution(int tpsLimite, double heureDepart, double vitesse, int nbSommets, ArrayList<PointLivraison> listPointLivraisons, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Integer.MAX_VALUE;
        meilleureSolution = new PointLivraison[nbSommets];
        horaireMeilleureSolution = null;
        ArrayList<PointLivraison> nonVus = new ArrayList<PointLivraison>();
        for (int i = 1; i < listPointLivraisons.size(); i++) nonVus.add(listPointLivraisons.get(i));
        ArrayList<PointLivraison> vus = new ArrayList<PointLivraison>(nbSommets);
        ArrayList<Map.Entry<Double, Double>> heureLivraison = new ArrayList<Map.Entry<Double, Double>>();
        vus.add(listPointLivraisons.get(0)); // le premier sommet visite est 0
        heureLivraison.add(new AbstractMap.SimpleEntry<Double, Double>(heureDepart, heureDepart));
        branchAndBound(vitesse, listPointLivraisons.get(0), nonVus, vus, heureLivraison, heureDepart, itinerairesMap, System.currentTimeMillis(), tpsLimite);
    }

    public PointLivraison getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
            return null;
        return meilleureSolution[i];
    }

    public double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    public ArrayList<Map.Entry<Double, Double>> getHoraireMeilleureSolution() {
        return horaireMeilleureSolution;
    }

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCourant
     * @param nonVus        : tableau des sommets restant a visiter
     * @return une borne inferieure du cout des permutations commencant par sommetCourant,
     * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
     */
    protected abstract double bound(double vitesse, PointLivraison sommetCourant, ArrayList<PointLivraison> nonVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap);

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCrt
     * @param nonVus    : tableau des sommets restant a visiter
     * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
     */
    protected abstract Iterator<PointLivraison> iterator(PointLivraison sommetCrt, ArrayList<PointLivraison> nonVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap);

    /**
     * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
     *
     * @param sommetCrt      le dernier sommet visite
     * @param nonVus         la liste des sommets qui n'ont pas encore ete visites
     * @param vus            la liste des sommets visites (y compris sommetCrt)
     * @param coutVus        la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
     * @param itinerairesMap : itinerairesMap.get(Map.Entry<point1,point2>) = duree pour aller de point1 à point2
     * @param tpsDebut       : moment ou la resolution a commence
     * @param tpsLimite      : limite de temps pour la resolution
     */
    void branchAndBound(double vitesse, PointLivraison sommetCrt, ArrayList<PointLivraison> nonVus, ArrayList<PointLivraison> vus, ArrayList<Map.Entry<Double, Double>> heureLivraison, double coutVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap, long tpsDebut, int tpsLimite) {
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, vus.get(0))).getLongueurTotale();
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                vus.toArray(meilleureSolution);
                horaireMeilleureSolution = new ArrayList<Map.Entry<Double, Double>>(heureLivraison);
                coutMeilleureSolution = coutVus + itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, vus.get(0))).getLongueurTotale() / vitesse;
            }
        } else if (coutVus + bound(vitesse, sommetCrt, nonVus, itinerairesMap) < coutMeilleureSolution) {
            Iterator<PointLivraison> it = iterator(sommetCrt, nonVus, itinerairesMap);
            while (it.hasNext()) {
                PointLivraison prochainSommet = it.next();
                double arrivee = coutVus + itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, prochainSommet)).getLongueurTotale() / vitesse + prochainSommet.getDuree();
                double depart = arrivee + prochainSommet.getDuree();
//                if (prochainSommet.getDebutPlage() != -1d && prochainSommet.getFinPlage() != -1d) {
//                    if (arrivee < prochainSommet.getDebutPlage()) {
//                        arrivee = prochainSommet.getDebutPlage();
//                    }
//                    depart = arrivee + prochainSommet.getDuree();
//                    if (depart > prochainSommet.getFinPlage()) {
//                        continue;
//                    }
//                }
                AbstractMap.SimpleEntry<Double, Double> entry = new AbstractMap.SimpleEntry<Double, Double>(arrivee, depart);
                heureLivraison.add(entry);
                vus.add(prochainSommet);
                nonVus.remove(prochainSommet);
                branchAndBound(vitesse, prochainSommet, nonVus, vus, heureLivraison, depart, itinerairesMap, tpsDebut, tpsLimite);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
                heureLivraison.remove(entry);
            }
        }
    }
}


