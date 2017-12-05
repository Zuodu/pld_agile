package Controleur;

import java.util.LinkedList;

public class LstDeCde {
	private LinkedList<Commande> liste;
	private int i;

	public LstDeCde()
	{
		i = 0;
		liste = new LinkedList<Commande>();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void ajouterCommande(Commande newCde) {
		liste.add(newCde);
		if(this.liste.size()==i+1)
		{
			liste.remove(i-1);
		}
	}

	public void undo() {
		if(i>0) {
			liste.get(i - 1).undoCde();
			i--;
		}
	}
	
	public void redo()
	{
		if(i<liste.size()) {
			liste.get(i).doCde();
			i++;
		}
	}
}
