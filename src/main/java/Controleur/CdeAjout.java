package Controleur;

import Modele.*;

import javax.swing.*;

public class CdeAjout implements Commande {
	Tournee tournee;
	Tournee oldTournee;
	Tournee tourneeRedo;
	Double debut;
	Double fin;
	double duree;
	Noeud noeud;

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
	public void undoCde()
	{
		tourneeRedo.clone(tournee,tourneeRedo);
		tournee.clone(oldTournee,tournee);
	}

}
