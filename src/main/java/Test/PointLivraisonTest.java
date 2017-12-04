package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Modele.PointLivraison;

public class PointLivraisonTest {
	PointLivraison pointLivraison;
	
	public void InitTU() {
		
	}

	@Test //Test d'une contruction par copie
	public void TU3() {
		InitTU();
		
		PointLivraison point = new PointLivraison(25495299L, 56584, 58632, 900);
		PointLivraison pointCopie = new PointLivraison(point);
		
		assert(point.getDuree() == 900);
		assert(pointCopie.getDuree() == 900);
		
		point.setDuree(0);
		
		assert(point.getDuree() == 0);
		assert(pointCopie.getDuree() == 900);
	}
}
