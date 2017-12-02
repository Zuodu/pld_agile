package Algo;

import Modele.Itineraire;
import Modele.Noeud;
import Modele.PointLivraison;
import Modele.Troncon;
import jdk.nashorn.internal.objects.annotations.Function;

import java.util.*;

/**
 * @author H4401
 * Classe implémentant les méthodes nécessaires à l'application de l'algorythme de Dijkstra adapté à notre plan.
 */
@SuppressWarnings("ALL")
public class Dijkstra {
    private HashMap<Noeud, Map.Entry<Noeud, Troncon>> parent;
    private HashMap<Noeud, Double> distance;
    private Itineraire meilleurItineraire;

    public Dijkstra() {
        this.parent = new HashMap<Noeud, Map.Entry<Noeud, Troncon>>();
        this.distance = new HashMap<Noeud, Double>();
        this.meilleurItineraire = new Itineraire();
    }

    /**
     * Méthode cherchant la distance minimale entre 2 noeuds
     * @param src Noeud source
     * @param target Noeud cible
     */
    public void chercheDistanceMin(Noeud src, Noeud target) {

        meilleurItineraire.setNoeudOrigine(src);
        meilleurItineraire.setNoeudDestination(target);

        parent.put(src, new AbstractMap.SimpleEntry<Noeud, Troncon>(src, null));

        distance.put(src, 0d);

        Set<Noeud> x = new HashSet<Noeud>();

        Queue<Map.Entry<Double, Noeud>> y = new PriorityQueue<Map.Entry<Double, Noeud>>(10, (Comparator<Map.Entry<Double, Noeud>>) longueurComparator);

        y.add(new AbstractMap.SimpleEntry<Double, Noeud>(0D, src));
        x.add(src);

        while (!y.isEmpty()) {
            Map.Entry<Double, Noeud> entry = y.remove();
            Noeud noeud = entry.getValue();
            if (noeud.getId().equals(target.getId())) {
                while (noeud != src) {
                    meilleurItineraire.addTroncon(parent.get(noeud).getValue());
                    noeud = parent.get(noeud).getKey();
                }
                return;
            }

            x.add(noeud);

            for (Troncon troncon : noeud.getNeighbors()) {
                if (x.contains(troncon.getDestination())) {
                    continue;
                }
                if (getDistance(troncon.getDestination()) > distance.get(noeud) + troncon.getLongueur()) {
                    distance.replace(troncon.getDestination(), distance.get(noeud) + troncon.getLongueur());
                    if (parent.containsKey(troncon.getDestination())) {
                        parent.replace(troncon.getDestination(), new AbstractMap.SimpleEntry<Noeud, Troncon>(noeud, troncon));
                    } else {
                        parent.put(troncon.getDestination(), new AbstractMap.SimpleEntry<Noeud, Troncon>(noeud, troncon));
                    }
                    y.add(new AbstractMap.SimpleEntry<Double, Noeud>(distance.get(troncon.getDestination()), troncon.getDestination()));
                }
            }
        }


    }

    /**
     * Get
     * @return meilleurItineraire
     */
    public Itineraire getMeilleurItineraire() {
        //System.out.println(meilleurItineraire);
        return meilleurItineraire;
    }

    /**
     * Renvoie la distance pour un noeud
     * @param noeud
     * @return distance
     */
    private Double getDistance(Noeud noeud) {
        if (distance.containsKey(noeud)) {
            return distance.get(noeud);
        }
        distance.put(noeud, Double.MAX_VALUE);
        return Double.MAX_VALUE;
    }

    public static Comparator<Map.Entry<Double, Noeud>> longueurComparator = new Comparator<Map.Entry<Double, Noeud>>() {
        public int compare(Map.Entry<Double, Noeud> o1, Map.Entry<Double, Noeud> o2) {
            return (int) (o1.getKey() - o2.getKey());
        }
    };
}
