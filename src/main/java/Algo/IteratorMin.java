package Algo;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by flavi on 2017/12/2.
 */
public class IteratorMin implements Iterator<Integer> {
    private Integer[] candidats;
    private int nbCandidats;

    /**
     * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus du cout min au cout max
     *
     * @param nonVus
     * @param sommetCrt
     */
    public IteratorMin(Collection<Integer> nonVus, int sommetCrt, double[][] cout, double[] duree) {
        this.candidats = new Integer[nonVus.size()];
        nbCandidats = 0;
        for (Integer s : nonVus) {
            candidats[nbCandidats++] = s;
        }
        double[] cost = new double[cout.length];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = cout[sommetCrt][i] + duree[i];
        }
        bubbleSort(cost);

    }

    /**
     * Surcharge hasNext()
     * @return si il existe encore des candidats
     */
    @Override
    public boolean hasNext() {
        return nbCandidats > 0;
    }

    /**
     * Surcharge next()
     * @return le prochain candidat
     */
    @Override
    public Integer next() {
        return candidats[--nbCandidats];
    }

    @Override
    public void remove() {
    }

    /**
     * Trier un tableau de cout du plus haut au plus bas
     * @param cost le tableau de cout
     */
    private void bubbleSort(double[] cost) {
        int i, temp, len = candidats.length;
        boolean changed;
        do {
            changed = false;
            for (i = 0; i < len - 1; i++) {
                if (cost[candidats[i]] < cost[candidats[i + 1]]) {
                    temp = candidats[i];
                    candidats[i] = candidats[i + 1];
                    candidats[i + 1] = temp;
                    changed = true;
                }
            }
        } while (changed);
    }
}
