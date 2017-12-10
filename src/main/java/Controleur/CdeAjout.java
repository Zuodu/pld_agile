package Controleur;

import Modele.*;

import javax.swing.*;

/**
 * @author H4401
 * Classe de Commande d'ajout de point de livraison
 */
public class CdeAjout implements Commande {
	Tournee tournee;
	Tournee oldTournee;
	Tournee tourneeRedo;
	Double debut;
	Double fin;
	double duree;
	Noeud noeud;

	/**
	 * Constructeur prenant en paramètre toutes les informations nécessaires à l'ajout d'un point de livraison
	 * @param noeud
	 * @param debut
	 * @param fin
	 * @param duree
	 * @param newTournee
	 * @param lstDeCde
	 */
	public CdeAjout (Noeud noeud,Double debut, Double fin, double duree, Tournee newTournee,LstDeCde lstDeCde)
	{
		tournee = newTournee;
		oldTournee = new Tournee(newTournee);
		oldTournee.clone(tournee,oldTournee);
		tourneeRedo = new Tournee();
		if (debut!=null) {
			this.debut=debut;
		} else {
			debut=null;
		}
		if (fin!=null) {
			this.fin=fin;
		} else {
			fin=null;
		}
		this.duree=duree;
		this.noeud=noeud;
	}

	/**
	 * Méthode permettant l'ajout (ou le "Redo")
	 */
	public void doCde()
	{
		if(tourneeRedo.getListePointLivraisons().size()==0) {
			if(tournee.ajouterPoint(noeud,duree,fin,debut)) {
				JOptionPane.showMessageDialog(null, "Ajout Reussi");
			} else {
				JOptionPane.showMessageDialog(null,"Ajout Echoue");
			}
		}
		else
		{
			tournee.clone(tourneeRedo,tournee);
		}
	}

	/**
	 * Méthode permettant de revenir à l'état d'avant modification (Undo)
	 */
	public void undoCde()
	{
		tourneeRedo.clone(tournee,tourneeRedo);
		tournee.clone(oldTournee,tournee);
	}

}
