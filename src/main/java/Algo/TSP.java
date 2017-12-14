package Algo;

import java.util.ArrayList;
import java.util.Map;

public interface TSP {

    /**
     * @return true si chercheSolution() s'est terminee parce que la limite de temps avait ete atteinte, avant d'avoir pu explorer tout l'espace de recherche,
     */
    public Boolean getTempsLimiteAtteint();
    /**
     * Cherche un circuit de duree minimale passant par chaque sommet (compris entre 0 et nbSommets-1)
     * @param coutInit      : valeur initiale de la meilleure cout
     * @param heureDeDepart : heure de depart de la livraison
     * @param tpsLimite     : limite (en millisecondes) sur le temps d'execution de chercheSolution
     * @param nbSommets     : nombre de sommets du graphe
     * @param cout          : cout[i][j] = duree pour aller de i a j, avec 0 &lt;= i &lt; nbSommets et 0 &lt;= j &lt; nbSommets
     * @param duree         : duree[i] = duree pour visiter le sommet i, avec 0 &lt;= i &lt; nbSommets
     * @param plageArrivee  : plageArrivee[i] = debut plage de sommet i
     * @param plageDepart   : plageDepart[i] = fin plage de sommet i
     */
    public void chercheSolution(double coutInit, double heureDeDepart, int tpsLimite, int nbSommets, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart);

    /**
     * @param i
     * @return le sommet visite en i-eme position dans la solution calculee par chercheSolution
     */
    public Integer getMeilleureSolution(int i);

    public ArrayList<Map.Entry<Double, Double>> getHoraireLivraison();

    /**
     * @return la duree de la solution calculee par chercheSolution
     */
    public double getCoutMeilleureSolution();
}
