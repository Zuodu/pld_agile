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
    Tournee tourneeRedo;
    PointLivraison pointLivraison;

    public CdeSupprimer(PointLivraison pointLivraison, Tournee tournee) {
        this.tournee = tournee;
        this.pointLivraison = pointLivraison;
        oldTournee = new Tournee();
        oldTournee.clone(tournee,oldTournee);
        tourneeRedo = new Tournee();
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
        if(tourneeRedo.getListePointLivraisons().size()==0) {
            boolean res = tournee.supprimerPoint(pointLivraison);
        }
        else{
            tournee.clone(tourneeRedo,tournee);
            tournee.SignalerFinDajoutPointsLivraisons();
        }
    }

    public void undoCde() {
        tourneeRedo.clone(tournee,tourneeRedo);
        tournee.clone(oldTournee,tournee);
        tournee.SignalerFinDajoutPointsLivraisons();
    }
}
