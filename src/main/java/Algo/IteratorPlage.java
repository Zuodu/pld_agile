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
     * iterer d'abord les points avec plage horaire
     *
     * @param nonVus
     * @param sommetCrt
     */
    public IteratorPlage(Collection<Integer> nonVus, int sommetCrt, double[][] cout, double[] duree, Double[] plageArrivee, Double[] plageDepart, double coutVus) {
        this.candidats = new Integer[nonVus.size()];
        nbCandidats = 0;
        for (Integer s : nonVus) {
            candidats[nbCandidats++] = s;
        }

        double[] plageTable = new double[cout.length];
        double[] cost = new double[cout.length];
        for (int i = 0; i < plageTable.length; i++) {
            if (plageDepart[i] != null) {
                plageTable[i] = plageDepart[i];
            } else {
                plageTable[i] = 9999999;
            }
        }
        for (int i = 0; i < cost.length; i++) {
            cost[i] = cout[sommetCrt][i] + duree[i];
        }
        bubbleSort(plageTable, cost);

        boolean isLate = false;
        for (int i = nbCandidats - 1; i >= 0 && !isLate && plageDepart[candidats[i]] != null; i--) {
            Integer point = candidats[i];
            if (plageDepart[point] < coutVus + cout[sommetCrt][point] + duree[point]) {
                isLate = true;
            }
        }
        if (isLate) {
            nbCandidats = 0;
        }


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
