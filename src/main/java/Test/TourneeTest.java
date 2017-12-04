package Test;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.Map;

import org.junit.Test;

import Modele.Itineraire;
import Modele.PointLivraison;
import Modele.Tournee;

public class TourneeTest {
	private Tournee tournee;

	public void InitTU() {
		tournee = new Tournee();
	}
	
	@Test //Ajout d'un PointLivraison
	public void TU1() {
		InitTU();
		
		assert(tournee.getListePointLivraisons().isEmpty());
		
		PointLivraison point = new PointLivraison(25495299L, 56584, 58632, 900); boolean pointPresent = false;
		tournee.addPointLivraisons(point);
		
		for (PointLivraison p : tournee.getListePointLivraisons()) {
            if(p.getId() == (long) point.getId() && point.getX() == point.getX() && point.getY() == point.getY() && point.getDuree() == point.getDuree()) pointPresent = true;
		}
		
		assert(tournee.getListePointLivraisons().size() == 1);
		assert(pointPresent);
	}
	
	@Test //Ajout d'un Itineraire
	public void TU2() {
		InitTU();
		
		assert(tournee.getListeItineraires().isEmpty());
		
		Itineraire itineraire = new Itineraire(); boolean itinerairePresent = false;
		tournee.addItineraire(itineraire);
		
		for (Itineraire i : tournee.getListeItineraires()) {
            if(i.equals(itineraire)) itinerairePresent = true;
		}
		
		assert(tournee.getListeItineraires().size() == 1);
		assert(itinerairePresent);
	}
	
	@Test //Ajout d'un Horaire de Livraison
	public void TU3() {
		InitTU();
		
		assert(tournee.getHoraireLivraison().isEmpty());
		
		PointLivraison point = new PointLivraison(25495299L, 56584, 58632, 900);
		Map.Entry<Double, Double> horaire = new AbstractMap.SimpleEntry<Double, Double>(36000.0, 50400.0);
		tournee.addHoraireLivraison(point, horaire);
		
		assert(tournee.getHoraireLivraison().get(point).getKey().equals(new Double(36000.0)));
		assert(tournee.getHoraireLivraison().get(point).getValue() == (double) 50400.0);
	}
}
