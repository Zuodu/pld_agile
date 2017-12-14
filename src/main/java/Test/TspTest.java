package Test;

import Algo.*;
import org.junit.Test;

/**
 * Created by siyingjiang on 2017/12/11.
 */
public class TspTest {
    private final TSP tsp1 = new TSP1();
    private final TSP tsp2 = new TSP2();
    private final TSP tsp3 = new TSP3();
    private final TSP tsp4 = new TSP4();
    private double coutInit;
    private double heureDeDepart;
    private int tpsLimite;
    private int nbSommets;
    private double[][] cout;
    private double[] duree = {0, 1, 1};
    private Double[] plageArrivee;
    private Double[] plageDepart;


    public void InitTU() {
        coutInit = Double.MAX_VALUE;
        heureDeDepart = 0;
        tpsLimite = 100000;
        nbSommets = 3;
        cout = new double[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            cout[i][i] = Double.MAX_VALUE;
        }
        cout[0][1] = 2;
        cout[0][2] = 3;
        cout[2][1] = 1;
        cout[2][0] = 4;
        cout[1][0] = 2;
        cout[1][2] = 3;
        plageArrivee = new Double[nbSommets];
        plageDepart = new Double[nbSommets];

    }

    @Test //Test de tsp1 sans TW
    public void TU1() {
        InitTU();

        tsp1.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp1.getMeilleureSolution(1) == 2);
        assert (tsp1.getMeilleureSolution(2) == 1);
        assert (tsp1.getCoutMeilleureSolution() == 8);

    }

    @Test //Test de tsp2 sans TW
    public void TU2() {
        InitTU();

        tsp2.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp2.getMeilleureSolution(1) == 2);
        assert (tsp2.getMeilleureSolution(2) == 1);
        assert (tsp2.getCoutMeilleureSolution() == 8);

    }

    @Test //Test de tsp3 sans TW
    public void TU3() {
        InitTU();

        tsp3.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp3.getMeilleureSolution(1) == 2);
        assert (tsp3.getMeilleureSolution(2) == 1);
        assert (tsp3.getCoutMeilleureSolution() == 8);

    }

    @Test //Test de tsp4 sans TW
    public void TU4() {
        InitTU();

        tsp4.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp4.getMeilleureSolution(1) == 2);
        assert (tsp4.getMeilleureSolution(2) == 1);
        assert (tsp4.getCoutMeilleureSolution() == 8);

    }

    @Test //Test de tsp1 avec TW
    public void TU5() {
        InitTU();
        plageArrivee[1] = 7D;
        plageDepart[1] = 9D;
        tsp1.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp1.getMeilleureSolution(1) == 2);
        assert (tsp1.getMeilleureSolution(2) == 1);
        assert (tsp1.getCoutMeilleureSolution() == 10);

    }

    @Test //Test de tsp2 avec TW
    public void TU6() {
        InitTU();
        plageArrivee[1] = 7D;
        plageDepart[1] = 9D;
        tsp2.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp2.getMeilleureSolution(1) == 2);
        assert (tsp2.getMeilleureSolution(2) == 1);
        assert (tsp2.getCoutMeilleureSolution() == 10);

    }

    @Test //Test de tsp3 avec TW
    public void TU7() {
        InitTU();
        plageArrivee[1] = 7D;
        plageDepart[1] = 9D;
        tsp3.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp3.getMeilleureSolution(1) == 2);
        assert (tsp3.getMeilleureSolution(2) == 1);
        assert (tsp3.getCoutMeilleureSolution() == 10);

    }

    @Test //Test de tsp4 avec TW
    public void TU8() {
        InitTU();
        plageArrivee[1] = 7D;
        plageDepart[1] = 9D;
        tsp4.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp4.getMeilleureSolution(1) == 2);
        assert (tsp4.getMeilleureSolution(2) == 1);
        assert (tsp4.getCoutMeilleureSolution() == 10);

    }

    @Test //Test de tsp1 avec TW
    public void TU9() {
        InitTU();
        plageArrivee[1] = 2D;
        plageDepart[1] = 3D;
        tsp1.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp1.getMeilleureSolution(1) == 1);
        assert (tsp1.getMeilleureSolution(2) == 2);
        assert (tsp1.getCoutMeilleureSolution() == 11);

    }

    @Test //Test de tsp2 avec TW
    public void TU10() {
        InitTU();
        plageArrivee[1] = 2D;
        plageDepart[1] = 3D;
        tsp2.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp2.getMeilleureSolution(1) == 1);
        assert (tsp2.getMeilleureSolution(2) == 2);
        assert (tsp2.getCoutMeilleureSolution() == 11);

    }

    @Test //Test de tsp3 avec TW
    public void TU11() {
        InitTU();
        plageArrivee[1] = 2D;
        plageDepart[1] = 3D;
        tsp3.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp3.getMeilleureSolution(1) == 1);
        assert (tsp3.getMeilleureSolution(2) == 2);
        assert (tsp3.getCoutMeilleureSolution() == 11);

    }

    @Test //Test de tsp4 avec TW
    public void TU12() {
        InitTU();
        plageArrivee[1] = 2D;
        plageDepart[1] = 3D;
        tsp4.chercheSolution(coutInit, heureDeDepart, tpsLimite, nbSommets, cout, duree, plageArrivee, plageDepart);
        assert (tsp4.getMeilleureSolution(1) == 1);
        assert (tsp4.getMeilleureSolution(2) == 2);
        assert (tsp4.getCoutMeilleureSolution() == 11);

    }
}
