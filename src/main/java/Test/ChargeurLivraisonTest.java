package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import ChargeurXML.ChargeurLivraison;
import ChargeurXML.ChargeurPlan;
import Modele.Plan;
import Modele.PointLivraison;
import Modele.Tournee;
import Modele.Troncon;

public class ChargeurLivraisonTest {
	private Plan petitPlan;
	private String planLyonPetit = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonPetit.xml";
	private Tournee petiteTournee;
	private String detailsLivraisonPetit = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\DLpetit5.xml";
	
	private Tournee moyenneTournee;
	private String planLyonMoyen = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonMoyen.xml";
	private Tournee grandeTournee;
	private String planLyonGrand = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonGrand.xml";

	public void InitTUp() {
		petitPlan = new Plan();
		ChargeurPlan.getInstance().parse(petitPlan, planLyonPetit);
		
		petiteTournee = new Tournee();
		ChargeurLivraison.getInstance().parse(petiteTournee, detailsLivraisonPetit);
	}
	
	@Test //Chargement de DLpetit5.xml
	public void TUp1() {
		InitTUp();
		
		assert(petiteTournee.getListePointLivraisons().size() == 4);
		assert(petiteTournee.getListeItineraires().isEmpty());
	}
	
	@Test //Veracité des PintLivraison de DLpetit5.xml
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
}
