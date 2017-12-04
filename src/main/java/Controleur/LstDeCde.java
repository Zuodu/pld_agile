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
		if(this.liste.get(i)!=null)
		{
			liste.remove(i);
		}
		liste.add(newCde);
	}

	public void undo() {
		if(i>=0)
			liste.get(i--).undoCde();
	}
	
	public void redo()
	{
		if(i<liste.size()-1)
			liste.get(++i).doCde();
	}
}
