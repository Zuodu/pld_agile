package Controleur;

/**
 * @author H4401
 * Classe Etat par initiale, uniquement active au lancement du logiciel
 */

public class EtatInit extends EtatDefaut{
	
	/**
	 * Le undo n'est pas possible ici
	 * @param l
	 */
	public void undo(LstDeCde l)
	{
	}
	
	/**
	 * Le redo n'est pas possible ici
	 * @param l
	 */
	public void redo(LstDeCde l)
	{
	}
}
