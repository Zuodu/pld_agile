package Test;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;
import org.junit.Test;

public class PlanTest {
    private Plan plan;

    public void InitTU() {
        plan = new Plan();
    }

    @Test //Ajout d'un Noeud
    public void TU1() {
        InitTU();

        assert (plan.getListeNoeuds().isEmpty());

        Noeud noeud = new Noeud(868438829L, 22507, 37639);
        boolean noeudPresent = false;
        plan.addNoeud(noeud);

        for (Noeud n : plan.getListeNoeuds()) {
            if (n.getId() == (long) noeud.getId() && n.getX() == noeud.getX() && n.getY() == noeud.getY())
                noeudPresent = true;
        }

        assert (plan.getListeNoeuds().size() == 1);
        assert (noeudPresent);
    }

    @Test //Ajout d'un Troncon
    public void TU2() {
        InitTU();

        assert (plan.getListeTroncons().isEmpty());

        Troncon troncon = new Troncon(new Noeud(868438829L, 22507, 37639), new Noeud(4034132513L, 22156, 36546), 93.40485, "Place du Château");
        boolean tronconPresent = false;
        plan.addTroncon(troncon);

        for (Troncon t : plan.getListeTroncons()) {
            if (troncon.getLongueur() == t.getLongueur() && troncon.getNomRue().equals(t.getNomRue()))
                tronconPresent = true;
        }

        assert (plan.getListeTroncons().size() == 1);
        assert (tronconPresent);
    }
}
