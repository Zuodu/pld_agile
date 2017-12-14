package Test;

import Algo.TSPFactory;
import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.Plan;
import Modele.Tournee;

import java.io.File;

public class AbstractGrapheTest {
    private TSPFactory graphePetit, grapheMoyen, grapheGrand;

    private Plan petitPlan;
    private String planLyonPetit = "." + File.separator + "fichiersXML" + File.separator + "planLyonPetit.xml";
    private Tournee petiteTournee;
    private String detailsLivraisonPetit = "." + File.separator + "fichiersXML" + File.separator + "DLpetit5.xml";

    private Plan moyenPlan;
    private String planLyonMoyen = "." + File.separator + "fichiersXML" + File.separator + "planLyonMoyen.xml";
    private Tournee moyenneTournee;
    private String detailsLivraisonMoyen = "." + File.separator + "fichiersXML" + File.separator + "DLmoyen10TW3.xml";

    private Plan grandPlan;
    private String planLyonGrand = "." + File.separator + "fichiersXML" + File.separator + "planLyonGrand.xml";
    private Tournee grandeTournee;
    private String detailsLivraisonGrand = "." + File.separator + "fichiersXML" + File.separator + "DLgrand20TW.xml";

    public void InitTUp() {
        petitPlan = new Plan();
        ChargeurPlan.getInstance().parse(petitPlan, planLyonPetit);

        petiteTournee = new Tournee();
        ChargeurLivraison.getInstance().parse(petiteTournee, detailsLivraisonPetit);

        TSPFactory graphePetit = new TSPFactory(petitPlan, petiteTournee);
    }

    //@Test //Test génération d'une Table de coûts pour un graphe petit
    public void TUp1() {
        InitTUp();
    }

    //@Test //Test génération d'une Table de durées pour un graphe petit
    public void TUp2() {
        InitTUp();

        double[] duree = graphePetit.getTableDuree();


    }

    //@Test //Test génération des Table de plages pour un graphe petit
    public void TUp3() {
        InitTUp();

        assert (graphePetit.getTablePlageDepart().length == 0);
        assert (graphePetit.getTablePlageArrivee().length == 0);
    }
}
