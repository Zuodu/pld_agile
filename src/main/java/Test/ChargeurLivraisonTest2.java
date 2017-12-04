package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Tournee;
import Modele.Troncon;

public class ChargeurLivraisonTest2 {
	private Plan petitPlan;
	private String planLyonPetit = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonPetit.xml";
	private Tournee petiteTournee;
	private String detailsLivraisonPetit = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\DLpetit5.xml";
	
	private Plan moyenPlan;
	private String planLyonMoyen = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonMoyen.xml";
	private Tournee moyenneTournee;
	private String detailsLivraisonMoyen = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\DLmoyen10TW3.xml";
	
	private Plan grandPlan;
	private String planLyonGrand = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonGrand.xml";
	private Tournee grandeTournee;
	private String detailsLivraisonGrand = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\DLgrand20TW.xml";

	public void InitTUp() {
		petitPlan = new Plan();
		ChargeurPlan.getInstance().parse(petitPlan, planLyonPetit);
		
		petiteTournee = new Tournee();
		ChargeurLivraison.getInstance().parse(petiteTournee, detailsLivraisonPetit);
	}
	
	@Test //Chargement de DLpetit5.xml
	public void TUp1() {
		InitTUp();
		
		assert(petiteTournee.getHeureDeDepart() == 28800.0);
		assert(petiteTournee.getListePointLivraisons().size() == 4);
		assert(petiteTournee.getItinerairesMap().isEmpty());
	}
	
	@Test //Présence des PointLivraison de DLpetit5.xml
	public void TUp2() {
		InitTUp();
		
		PointLivraison dernierPoint = new PointLivraison(1860559399L, 20437, 23772, 900); boolean dernierPointPresent = false;
		PointLivraison pointAleatoire = new PointLivraison(25303807L, 19933, 32576, 300); boolean pointAleatoirePresent = false;
		
		for (PointLivraison point : petiteTournee.getListePointLivraisons()) {
            if(point.getId() == (long) dernierPoint.getId() && point.getX() == dernierPoint.getX() && point.getY() == dernierPoint.getY()) dernierPointPresent = true;
            if(point.getId() == (long) pointAleatoire.getId() && point.getX() == pointAleatoire.getX() && point.getY() == pointAleatoire.getY()) pointAleatoirePresent = true;
        }
		
		assert(dernierPointPresent);
		assert(pointAleatoirePresent);
	}
	
	public void InitTUm() {
		moyenPlan = new Plan();
		ChargeurPlan.getInstance().parse(moyenPlan, planLyonMoyen);
		
		moyenneTournee = new Tournee();
		ChargeurLivraison.getInstance().parse(moyenneTournee, detailsLivraisonMoyen);
	}
	
	@Test //Chargement de DLmoyen10TW3.xml
	public void TUm1() {
		InitTUm();
		
		assert(moyenneTournee.getHeureDeDepart() == 28800.0);
		assert(moyenneTournee.getListePointLivraisons().size() == 9);
		assert(moyenneTournee.getItinerairesMap().isEmpty());
	}
	
	@Test //Présence des PointLivraison de DLmoyen10TW3.xml
	public void TUm2() {
		InitTUm();
		
		PointLivraison dernierPoint = new PointLivraison(25495299L, 56584, 58632, 900); boolean dernierPointPresent = false;
		PointLivraison pointAleatoire1 = new PointLivraison(25303839L, 61477, 88821, 900, 39600.0, 46800.0); boolean pointAleatoire1Present = false;
		PointLivraison pointAleatoire2 = new PointLivraison(194605312L, 48949, 107059, 900, 36000.0, 43200.0); boolean pointAleatoire2Present = false;
		
		for (PointLivraison point : moyenneTournee.getListePointLivraisons()) {
            if(point.getId() == (long) dernierPoint.getId() && point.getX() == dernierPoint.getX() && point.getY() == dernierPoint.getY() && point.getDuree() == dernierPoint.getDuree()) dernierPointPresent = true;
            if(point.getId() == (long) pointAleatoire1.getId() && point.getX() == pointAleatoire1.getX() && point.getY() == pointAleatoire1.getY() && point.getDebutPlage() == (double) pointAleatoire1.getDebutPlage()) pointAleatoire1Present = true;
            if(point.getId() == (long) pointAleatoire2.getId() && point.getX() == pointAleatoire2.getX() && point.getY() == pointAleatoire2.getY() && point.getDebutPlage() == (double) pointAleatoire2.getDebutPlage()) pointAleatoire2Present = true;
		}
		
		assert(dernierPointPresent);
		assert(pointAleatoire1Present);
		assert(pointAleatoire2Present);
	}
	
	public void InitTUg() {
		grandPlan = new Plan();
		ChargeurPlan.getInstance().parse(grandPlan, planLyonGrand);
		
		grandeTournee = new Tournee();
		ChargeurLivraison.getInstance().parse(grandeTournee, detailsLivraisonGrand);
	}
	
	@Test //Chargement de DLgrand20TW.xml
	public void TUg1() {
		InitTUg();
		
		assert(grandeTournee.getHeureDeDepart() == 28800.0);
		assert(grandeTournee.getListePointLivraisons().size() == 19);
		assert(grandeTournee.getItinerairesMap().isEmpty());
	}
	
	@Test //Présence des PointLivraison de DLgrand20TW.xml
	public void TUg2() {
		InitTUg();
		
		PointLivraison dernierPoint = new PointLivraison(984553654L, 163899, 237643, 300); boolean dernierPointPresent = false;
		PointLivraison pointAleatoire1 = new PointLivraison(54803121L, 195365, 250340, 300, 39600.0, 46800.0); boolean pointAleatoire1Present = false;
		PointLivraison pointAleatoire2 = new PointLivraison(4242803790L, 233559, 229311, 300, 46800.0, 50400.0); boolean pointAleatoire2Present = false;
		
		for (PointLivraison point : grandeTournee.getListePointLivraisons()) {
            if(point.getId() == (long) dernierPoint.getId() && point.getX() == dernierPoint.getX() && point.getY() == dernierPoint.getY() && point.getDuree() == dernierPoint.getDuree()) dernierPointPresent = true;
            if(point.getId() == (long) pointAleatoire1.getId() && point.getX() == pointAleatoire1.getX() && point.getY() == pointAleatoire1.getY() && point.getDebutPlage() == (double) pointAleatoire1.getDebutPlage()) pointAleatoire1Present = true;
            if(point.getId() == (long) pointAleatoire2.getId() && point.getX() == pointAleatoire2.getX() && point.getY() == pointAleatoire2.getY() && point.getDebutPlage() == (double) pointAleatoire2.getDebutPlage()) pointAleatoire2Present = true;
		}
		
		assert(dernierPointPresent);
		assert(pointAleatoire1Present);
		assert(pointAleatoire2Present);
	}
}
