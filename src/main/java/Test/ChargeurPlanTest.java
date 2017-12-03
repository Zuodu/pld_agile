package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import ChargeurXML.ChargeurPlan;
import Modele.*;

public class ChargeurPlanTest {
	private Plan petitPlan;
	private String planLyonPetit = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonPetit.xml";
	private Plan moyenPlan;
	private String planLyonMoyen = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonMoyen.xml";
	private Plan grandPlan;
	private String planLyonGrand = "C:\\Users\\Cal\\Documents\\INFO\\AGILE\\fichiersXML\\planLyonGrand.xml";

	public void InitTUp() {
		petitPlan = new Plan();
		ChargeurPlan.getInstance().parse(petitPlan, planLyonPetit);
	}
	
	@Test //Chargement de planLyonPetit.xml
	public void TUp1() {
		InitTUp();
		
		assert(petitPlan.getListeNoeuds().size() == 217);
		assert(petitPlan.getListeTroncons().size() == 370);
	}
	
	@Test //Veracité des Noeuds de planLyonPetit.xml
	public void TUp2() {
		InitTUp();
		
		Noeud dernierNoeud = new Noeud(868438829L, 22507, 37639); boolean dernierNoeudPresent = false;
		Noeud noeudAleatoire = new Noeud(388568803L, 19085, 35264); boolean noeudAleatoirePresent = false;
		
		for (Noeud noeud : petitPlan.getListeNoeuds()) {
            if(noeud.getId() == (long) dernierNoeud.getId() && noeud.getX() == dernierNoeud.getX() && noeud.getY() == dernierNoeud.getY()) dernierNoeudPresent = true;
            if(noeud.getId() == (long) noeudAleatoire.getId() && noeud.getX() == noeudAleatoire.getX() && noeud.getY() == noeudAleatoire.getY()) noeudAleatoirePresent = true;
        }
		assert(dernierNoeudPresent);
		assert(noeudAleatoirePresent);
	}
	
	@Test //Veracité des Troncons de planLyonPetit.xml
	public void TUp3() {
		InitTUp();
		
		Troncon dernierTroncon = new Troncon(new Noeud(868438829L, 22507, 37639), new Noeud(4034132513L, 22156, 36546), 93.40485, "Place du Château");
		boolean dernierTronconPresent = false;
		
		for (Troncon troncon : petitPlan.getListeTroncons()) {
			if(dernierTroncon.getLongueur() == troncon.getLongueur() && dernierTroncon.getNomRue().equals(troncon.getNomRue())) dernierTronconPresent = true;
        }
		
		assert(dernierTronconPresent);
	}
	
	public void InitTUm() {
		moyenPlan = new Plan();
		ChargeurPlan.getInstance().parse(moyenPlan, planLyonMoyen);
	}
	
	@Test //Chargement de planLyonMoyen.xml
	public void TUm1() {
		InitTUm();
		
		assert(moyenPlan.getListeNoeuds().size() == 1909);
		assert(moyenPlan.getListeTroncons().size() == 4021);
	}
	
	@Test //Veracité des Noeuds de planLyonMoyen.xml
	public void TUm2() {
		InitTUm();
		
		Noeud dernierNoeud = new Noeud(984553654L, 43921, 66297); boolean dernierNoeudPresent = false;
		Noeud noeudAleatoire = new Noeud(26464258L, 56801, 97778); boolean noeudAleatoirePresent = false;
		
		for (Noeud noeud : moyenPlan.getListeNoeuds()) {
            if(noeud.getId() == (long) dernierNoeud.getId() && noeud.getX() == dernierNoeud.getX() && noeud.getY() == dernierNoeud.getY()) dernierNoeudPresent = true;
            if(noeud.getId() == (long) noeudAleatoire.getId() && noeud.getX() == noeudAleatoire.getX() && noeud.getY() == noeudAleatoire.getY()) noeudAleatoirePresent = true;
        }
		assert(dernierNoeudPresent);
		assert(noeudAleatoirePresent);
	}
	
	@Test //Veracité des Troncons de planLyonMoyen.xml
	public void TUm3() {
		InitTUm();
		
		Troncon dernierTroncon = new Troncon(new Noeud(191134741L, 78678, 105478), new Noeud(25932598L, 78831, 104700), 62.674774, "Cours Émile Zola");
		boolean dernierTronconPresent = false;
		
		for (Troncon troncon : moyenPlan.getListeTroncons()) {
			if(dernierTroncon.getLongueur() == troncon.getLongueur() && dernierTroncon.getNomRue().equals(troncon.getNomRue())) dernierTronconPresent = true;
        }
		
		assert(dernierTronconPresent);
	}
	
	public void InitTUg() {
		grandPlan = new Plan();
		ChargeurPlan.getInstance().parse(grandPlan, planLyonGrand);
	}
	
	@Test //Chargement de planLyonGrand.xml
	public void TUg1() {
		InitTUg();
		
		assert(grandPlan.getListeNoeuds().size() == 12165);
		assert(grandPlan.getListeTroncons().size() == 27395);
	}
	
	@Test //Veracité des Noeuds de planLyonGrand.xml
	public void TUg2() {
		InitTUg();
		
		Noeud dernierNoeud = new Noeud(998859048L, 204520, 193795); boolean dernierNoeudPresent = false;
		Noeud noeudAleatoire = new Noeud(429428854L, 143237, 233136); boolean noeudAleatoirePresent = false;
		
		for (Noeud noeud : grandPlan.getListeNoeuds()) {
            if(noeud.getId() == (long) dernierNoeud.getId() && noeud.getX() == dernierNoeud.getX() && noeud.getY() == dernierNoeud.getY()) dernierNoeudPresent = true;
            if(noeud.getId() == (long) noeudAleatoire.getId() && noeud.getX() == noeudAleatoire.getX() && noeud.getY() == noeudAleatoire.getY()) noeudAleatoirePresent = true;
        }
		assert(dernierNoeudPresent);
		assert(noeudAleatoirePresent);
	}
	
	@Test //Veracité des Troncons de planLyonGrand.xml
	public void TUg3() {
		InitTUg();
		
		Troncon dernierTroncon = new Troncon(new Noeud(249725346L, 187600, 263570), new Noeud(249740340L, 186996, 262340), 116.68213, "Boulevard Honoré de Balzac");
		boolean dernierTronconPresent = false;
		
		for (Troncon troncon : grandPlan.getListeTroncons()) {
			if(dernierTroncon.getLongueur() == troncon.getLongueur() && dernierTroncon.getNomRue().equals(troncon.getNomRue())) dernierTronconPresent = true;
        }
		
		assert(dernierTronconPresent);
	}
}
