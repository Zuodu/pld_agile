package FeuilleDeRoute;

import Modele.Noeud;

/**
 * @author H4401
 *         Classe Util permettant de modéliser des vecteurs de noeuds pour le calcul des virages
 */
public class Vecteur {

    public int x, y;

    /**
     * Construit un Vecteur à partir des noeuds le définissant.
     *
     * @param origine noeud origine
     * @param desti   noeud destination
     */
    public Vecteur(Noeud origine, Noeud desti) {
        x = desti.getX() - origine.getX();
        y = desti.getY() - origine.getY();
    }

    public Vecteur(int xi, int yi) {
        x = xi;
        y = yi;
    }
}
