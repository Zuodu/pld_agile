package Controleur;

import Algo.Dijkstra;
import Main.main;
import Modele.Itineraire;
import Modele.PointLivraison;
import Modele.Tournee;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

public class CdeSupprimer implements Commande {
    Tournee tournee;
    Tournee oldTournee;
    PointLivraison pointLivraison;

    public CdeSupprimer(PointLivraison newPointLivraison, Tournee newTournee) {
        tournee = newTournee;
        pointLivraison = newPointLivraison;
        oldTournee = new Tournee(newTournee);
    }

    public void doCde() {
//		PointLivraison pPrecedant=null;
//		PointLivraison pProchain=null;
//		Iterator<PointLivraison> iterator=tournee.getListePointLivraisons().iterator();
//		PointLivraison tmp1=null;
//		PointLivraison tmp2=null;
//		while (iterator.hasNext()) {
//			tmp1=iterator.next();
//			if (tmp1.equals(pointLivraison)) {
//				pPrecedant=tmp2;
//				pProchain=iterator.next();
//				break;
//			}
//			tmp2=tmp1;
//		}
//		Dijkstra dijkstra=new Dijkstra();
//		dijkstra.chercheDistanceMin(pPrecedant,pProchain);
//		Itineraire result=dijkstra.getMeilleurItineraire();
//		tournee.getListePointLivraisons().remove(pointLivraison);
//		tournee.addItineraire(new AbstractMap.SimpleEntry(pPrecedant,pProchain),result);
//
//		tournee.getItinerairesMap().remove(new AbstractMap.SimpleEntry(pPrecedant,pointLivraison));
//		tournee.getItinerairesMap().remove(new AbstractMap.SimpleEntry(pointLivraison,pProchain));
//		pProchain.setHeureArrivee(pPrecedant.getHeureDepart()+result.getLongueurTotale()/ main.VITESSE);
//		tournee.SignalerFinDajoutPointsLivraisons();
        boolean res = tournee.supprimerPoint(pointLivraison);
    }

    public void undoCde() {
        tournee = oldTournee;
    }
}
