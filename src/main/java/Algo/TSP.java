package Algo;

import Modele.Itineraire;
import Modele.PointLivraison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface TSP {

    /**
     * @return true si chercheSolution() s'est terminee parce que la limite de temps avait ete atteinte, avant d'avoir pu explorer tout l'espace de recherche,
     */
    public Boolean getTempsLimiteAtteint();

    /**
     * Cherche un circuit de duree minimale passant par chaque sommet (compris entre 0 et nbSommets-1)
     *
     * @param vitesse             : vitesse de livraison
     * @param tpsLimite           : limite (en millisecondes) sur le temps d'execution de chercheSolution
     * @param nbSommets           : nombre de sommets du graphe
     * @param itinerairesMap      : itinerairesMap.get(Map.Entry<point1,point2>) = duree pour aller de point1 à point2
     * @param listPointLivraisons : liste de points de livraison sachant que le premier concerne l'entrepôt de livraison
     */
    public void chercheSolution(int tpsLimite, double heureDepart, double vitesse, int nbSommets, ArrayList<PointLivraison> listPointLivraisons, HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap);

    /**
     * @param i
     * @return le sommet visite en i-eme position dans la solution calculee par chercheSolution
     */
    public PointLivraison getMeilleureSolution(int i);

    /**
     * @return la duree de la solution calculee par chercheSolution
     */
    public double getCoutMeilleureSolution();

    /**
     * @return l'horaire de chaque livraison
     */
    public ArrayList<Map.Entry<Double, Double>> getHoraireMeilleureSolution();
}
