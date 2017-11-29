package Algo;

import Modele.Itineraire;
import Modele.PointLivraison;

import java.util.*;

public abstract class TemplateTSP implements Algo.TSP {

    private PointLivraison[] meilleureSolution;
    private double coutMeilleureSolution = 0;
    private Boolean tempsLimiteAtteint;

    public Boolean getTempsLimiteAtteint() {
        return tempsLimiteAtteint;
    }

    public void chercheSolution(int tpsLimite, double heureDepart, double vitesse, int nbSommets, ArrayList<PointLivraison> listPointLivraisons, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Integer.MAX_VALUE;
        meilleureSolution = new PointLivraison[nbSommets];
        ArrayList<PointLivraison> nonVus = new ArrayList<PointLivraison>();
        for (int i = 1; i < listPointLivraisons.size(); i++) nonVus.add(listPointLivraisons.get(i));
        ArrayList<PointLivraison> vus = new ArrayList<PointLivraison>(nbSommets);
        vus.add(listPointLivraisons.get(0)); // le premier sommet visite est 0
        branchAndBound(vitesse, heureDepart, listPointLivraisons.get(0), nonVus, vus, 0, itinerairesMap, System.currentTimeMillis(), tpsLimite);
    }

    public PointLivraison getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
            return null;
        return meilleureSolution[i];
    }

    public double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCourant
     * @param nonVus        : tableau des sommets restant a visiter
     * @return une borne inferieure du cout des permutations commencant par sommetCourant,
     * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
     */
    protected abstract int bound(PointLivraison sommetCourant, ArrayList<PointLivraison> nonVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap);

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
    void branchAndBound(double vitesse, double heureDepart, PointLivraison sommetCrt, ArrayList<PointLivraison> nonVus, ArrayList<PointLivraison> vus, double coutVus, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap, long tpsDebut, int tpsLimite) {
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, vus.get(0))).getLongueurTotale();
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
            }
        } else if (coutVus + bound(sommetCrt, nonVus, itinerairesMap) < coutMeilleureSolution) {
            Iterator<PointLivraison> it = iterator(sommetCrt, nonVus, itinerairesMap);
            while (it.hasNext()) {
                PointLivraison prochainSommet = it.next();
                double arrivee = coutVus + itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, prochainSommet)).getLongueurTotale() / vitesse + prochainSommet.getDuree() + heureDepart;
                double depart = arrivee + prochainSommet.getDuree();
                if (prochainSommet.getDebutPlage() != -1d && prochainSommet.getFinPlage() != -1d) {
                    if (depart > prochainSommet.getFinPlage() || arrivee < prochainSommet.getDebutPlage()) {
                        if (nonVus.indexOf(prochainSommet) >= 1) {
                            Collections.swap(nonVus, nonVus.indexOf(prochainSommet), nonVus.indexOf(prochainSommet) - 1);
                            prochainSommet = nonVus.get(nonVus.indexOf(prochainSommet) + 1);
                        }
                    }
                }
                vus.add(prochainSommet);
                nonVus.remove(prochainSommet);


                branchAndBound(vitesse, heureDepart, prochainSommet, nonVus, vus, coutVus + itinerairesMap.get(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(sommetCrt, prochainSommet)).getLongueurTotale() / vitesse + prochainSommet.getDuree(), itinerairesMap, tpsDebut, tpsLimite);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
            }
        }
    }
}

