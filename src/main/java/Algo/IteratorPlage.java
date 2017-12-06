package Algo;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by siyingjiang on 2017/12/6.
 */
public class IteratorPlage implements Iterator<Integer> {
    private Integer[] candidats;
    private int nbCandidats;

    /**
     * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
     *
     * @param nonVus
     * @param sommetCrt
     */
    public IteratorPlage(Collection<Integer> nonVus, int sommetCrt, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart) {
        this.candidats = new Integer[nonVus.size()];
        nbCandidats = 0;
        for (Integer s : nonVus) {
            candidats[nbCandidats++] = s;
        }
        double[] cost1 = new double[cout.length];
        double[] cost2 = new double[cout.length];
        for (int i = 0; i < cost1.length; i++) {
            if (plageDepart[i] != null) {
                cost1[i] = plageDepart[i];
            } else {
                cost1[i] = 9999999;
            }
        }
        for(int i=0;i<cost2.length;i++){
            cost2[i]=cout[sommetCrt][i]+duree[i];
        }
        bubbleSort(cost1, cost2);

    }

    @Override
    public boolean hasNext() {
        return nbCandidats > 0;
    }

    @Override
    public Integer next() {
        return candidats[--nbCandidats];
    }

    @Override
    public void remove() {
    }

    public void bubbleSort(double[] cost1, double[] cost2) {
        int i, temp, len = candidats.length;
        boolean changed;
        do {
            changed = false;
            for (i = 0; i < len - 1; i++) {
                if (cost1[candidats[i]] < cost1[candidats[i + 1]]) {
                    temp = candidats[i];
                    candidats[i] = candidats[i + 1];
                    candidats[i + 1] = temp;
                    changed = true;
                } else if (cost1[candidats[i]] == cost1[candidats[i + 1]]) {
                    if (cost2[candidats[i]] < cost2[candidats[i + 1]]) {
                        temp = candidats[i];
                        candidats[i] = candidats[i + 1];
                        candidats[i + 1] = temp;
                        changed = true;
                    }
                }
            }
        } while (changed);
    }
}
