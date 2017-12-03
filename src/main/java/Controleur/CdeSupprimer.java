package Controleur;

import Algo.Dijkstra;
import Modele.Itineraire;
import Modele.PointLivraison;
import Modele.Tournee;

import java.util.Iterator;

public class CdeSupprimer implements Commande {
	Tournee tournee;
	PointLivraison pointLivraison;

	public CdeSupprimer(PointLivraison newPointLivraison, Tournee newTournee) {
		tournee = newTournee;
		pointLivraison = newPointLivraison;
	}

	public void doCde() 
	{
		PointLivraison pPrecedant=null;
		PointLivraison pProchain=null;
		Iterator<PointLivraison> iterator=tournee.getListePointLivraisons().iterator();
		PointLivraison tmp1=null;
		PointLivraison tmp2=null;
		while (iterator.hasNext()) {
			tmp1=iterator.next();
			if (tmp1.equals(pointLivraison)) {
				pPrecedant=tmp2;
				pProchain=iterator.next();
				break;
			}
			tmp2=tmp1;
		}
		Dijkstra dijkstra=new Dijkstra();
		dijkstra.chercheDistanceMin(pPrecedant,pProchain);
		Itineraire result=dijkstra.getMeilleurItineraire();
	}
	public void undoCde()
	{
		
	}
}
