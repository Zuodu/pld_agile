package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Modele.Itineraire;
import Modele.Noeud;
import Modele.Tournee;
import Modele.Troncon;

public class ItineraireTest {
	private Itineraire itineraire;
	
	public void InitTU() {
		itineraire = new Itineraire();
	}

	@Test //Ajout d'un Troncon
	public void TU1() {
		InitTU();
		
		assert(itineraire.getListeTroncons().isEmpty());
		
		Troncon troncon = new Troncon(new Noeud(868438829L, 22507, 37639), new Noeud(4034132513L, 22156, 36546), 93.40485, "Place du Château"); boolean tronconPresent = false;
		itineraire.addTroncon(troncon);
		
		for (Troncon t : itineraire.getListeTroncons()) {
			if(troncon.getLongueur() == t.getLongueur() && troncon.getNomRue().equals(t.getNomRue())) tronconPresent = true;
        }
		
		assert(itineraire.getListeTroncons().size() == 1);
		assert(itineraire.getLongueurTotale() == 93.40485);
		assert(tronconPresent);
	}

	@Test //Itineraire contenant plusieurs Troncon
	public void TU2() {
		InitTU();
		
		assert(itineraire.getListeTroncons().isEmpty());
		
		Troncon troncon1 = new Troncon(new Noeud(868438829L, 22507, 37639), new Noeud(4034132513L, 22156, 36546), 93.40485, "Place du Château");
		Troncon troncon2 = new Troncon(new Noeud(249725346L, 187600, 263570), new Noeud(249740340L, 186996, 262340), 116.68213, "Boulevard Honoré de Balzac");
		Troncon troncon3 = new Troncon(new Noeud(191134741L, 78678, 105478), new Noeud(25932598L, 78831, 104700), 62.674774, "Cours Émile Zola");
		itineraire.addTroncon(troncon1);
		itineraire.addTroncon(troncon2);
		itineraire.addTroncon(troncon3);
		
		assert(itineraire.getListeTroncons().size() == 3);
		assert(itineraire.getLongueurTotale() == 93.40485 + 116.68213 + 62.674774);
	}
	
	@Test //Noeuds de départ et d'arrivée
	public void TU3() {
		InitTU();
		
		assert(itineraire.getNoeudOrigine() == null);
		assert(itineraire.getNoeudDestination() == null);
		
		Noeud noeud1 = new Noeud(868438829L, 22507, 37639);
		Noeud noeud2 = new Noeud(4034132513L, 22156, 36546);
		itineraire.setNoeudOrigine(noeud1);
		itineraire.setNoeudDestination(noeud2);
		
		assert(itineraire.getNoeudOrigine().equals(noeud1));
		assert(itineraire.getNoeudDestination().equals(noeud2));
	}
}
