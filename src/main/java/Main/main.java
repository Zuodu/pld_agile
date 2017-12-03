package Main;

import Algo.IteratorMin;
import Controleur.Controleur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author H4401
 * Classe Main, lançant le projet
 */
public class main {
    public static void main(String args[]) {
        Controleur controleur = new Controleur();
     //   controleur.chargerPlan("planLyonGrand.xml");
      //  controleur.chargerLivraison("DLgrand15.xml");
       // controleur.calculerTournee();
    }
}
//Collection<Integer> nonVus, int sommetCrt, double[][] cout;
//        Collection<Integer> nonVus=new ArrayList<Integer>();
//        nonVus.add(1);
//        nonVus.add(2);
//        nonVus.add(3);
//        int sommet=0;
//        double[][] cout=new double[4][4];
//        cout[0][1]=2;
//        cout[0][2]=4;
//        cout[0][3]=1;
//
//        double [] duree=new double [4];
//        duree[1]=0;
//        duree[2]=1;
//        duree[3]=1;
//
//        Iterator<Integer> it=new IteratorMin(nonVus, sommet, cout, duree);
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }

//package Algo;
//
//        import java.util.*;
//
//public abstract class TemplateTSP implements Algo.TSP {
//
//    private Integer[] meilleureSolution;
//    private double coutMeilleureSolution = 0;
//    private Boolean tempsLimiteAtteint;
//    private ArrayList<Map.Entry<Double, Double>> horaireLivraison;
//
//    public Boolean getTempsLimiteAtteint() {
//        return tempsLimiteAtteint;
//    }
//
//    public void chercheSolution(double heureDeDepart, int tpsLimite, int nbSommets, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart) {
//        tempsLimiteAtteint = false;
//        horaireLivraison = new ArrayList<Map.Entry<Double, Double>>(nbSommets);
//        meilleureSolution = new Integer[nbSommets];
//        getSolutionGlouton( heureDeDepart,nbSommets, cout, duree);
//        ArrayList<Integer> nonVus = new ArrayList<Integer>();
//        ArrayList<Map.Entry<Double, Double>> heureLivraison = new ArrayList<Map.Entry<Double, Double>>();
//        for (int i = 1; i < nbSommets; i++) nonVus.add(i);
//        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
//        vus.add(0); // le premier sommet visite est 0
//        heureLivraison.add(new AbstractMap.SimpleEntry<Double, Double>(heureDeDepart, heureDeDepart));
//        branchAndBound(0, nonVus, vus, heureLivraison, heureDeDepart, cout, duree, plageArrivee, plageDepart, System.currentTimeMillis(), tpsLimite);
//    }
//
//    public Integer getMeilleureSolution(int i) {
//        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
//            return null;
//        return meilleureSolution[i];
//    }
//
//    private void getSolutionGlouton(double heureDeDepart,int nbSommets, double[][] cout, double[] duree) {
//        Glouton glouton = new Glouton(nbSommets);
//        List<Integer>res =glouton.chercheSolution(cout, duree);
//        coutMeilleureSolution=glouton.getCoutMeilleureSolution()+heureDeDepart;
//        res.toArray(meilleureSolution);
//    }
//
//    public double getCoutMeilleureSolution() {
//        return coutMeilleureSolution;
//    }
//
//    public ArrayList<Map.Entry<Double, Double>> getHoraireLivraison() {
//        return horaireLivraison;
//    }
//
//    /**
//     * Methode devant etre redefinie par les sous-classes de TemplateTSP
//     *
//     * @param sommetCourant
//     * @param nonVus        : tableau des sommets restant a visiter
//     * @param cout          : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
//     * @param duree         : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
//     * @return une borne inferieure du cout des permutations commencant par sommetCourant,
//     * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
//     */
//    protected abstract double bound(Integer sommetCourant, ArrayList<Integer> nonVus, double[][] cout, double[] duree);
//
//    /**
//     * Methode devant etre redefinie par les sous-classes de TemplateTSP
//     *
//     * @param sommetCrt
//     * @param nonVus    : tableau des sommets restant a visiter
//     * @param cout      : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
//     * @param duree     : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
//     * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
//     */
//    protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, double[][] cout, double[] duree);
//
//    /**
//     * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
//     *
//     * @param sommetCrt le dernier sommet visite
//     * @param nonVus    la liste des sommets qui n'ont pas encore ete visites
//     * @param vus       la liste des sommets visites (y compris sommetCrt)
//     * @param coutVus   la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
//     * @param cout      : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
//     * @param duree     : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
//     * @param tpsDebut  : moment ou la resolution a commence
//     * @param tpsLimite : limite de temps pour la resolution
//     */
//    void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, ArrayList<Map.Entry<Double, Double>> heureLivraison, double coutVus, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart, long tpsDebut, int tpsLimite) {
//        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
//            tempsLimiteAtteint = true;
////            return;
//        }
//        if (nonVus.size() == 0) { // tous les sommets ont ete visites
//            coutVus += cout[sommetCrt][0];
//            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
//                vus.toArray(meilleureSolution);
//                horaireLivraison = new ArrayList<Map.Entry<Double, Double>>(heureLivraison);
//                coutMeilleureSolution = coutVus;
//            }
//        } else if (coutVus + bound(sommetCrt, nonVus, cout, duree) < coutMeilleureSolution) {
//            Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
//            while (it.hasNext()) {
//                Integer prochainSommet = it.next();
//                double arrivee = coutVus + cout[sommetCrt][prochainSommet];
//                double depart = arrivee + duree[prochainSommet];
//                if (plageArrivee[prochainSommet] != null && plageDepart[prochainSommet] != null) {
//                    if (arrivee < plageArrivee[prochainSommet]) {
//                        arrivee = plageArrivee[prochainSommet];
//                        depart = arrivee + duree[prochainSommet];
//                    } else if (depart > plageDepart[prochainSommet]) {
//                        continue;
//                    }
//                }
//                Map.Entry<Double, Double> horaireEntry = new AbstractMap.SimpleEntry<Double, Double>(arrivee, depart);
//                heureLivraison.add(horaireEntry);
//                vus.add(prochainSommet);
//                nonVus.remove(prochainSommet);
//                branchAndBound(prochainSommet, nonVus, vus, heureLivraison, depart, cout, duree, plageArrivee, plageDepart, tpsDebut, tpsLimite);
//                vus.remove(prochainSommet);
//                nonVus.add(prochainSommet);
//                heureLivraison.remove(horaireEntry);
//            }
//        }
//    }
//}



