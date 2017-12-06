package Vue;

import Controleur.Controleur;
import Modele.Noeud;
import Modele.PointLivraison;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Vue.VueGraphique.LEFT_OFFSET;
import static Vue.VueGraphique.RAYON_POINTLIVRAISON;

/**
 * Created by flavi on 2017/12/2.
 */
public class MouseListener extends MouseAdapter {
    private VueGraphique vueGraphique;
    private Controleur controleur;

    public MouseListener(VueGraphique vueGraphique, Controleur controleur) {
        this.vueGraphique = vueGraphique;
        this.controleur = controleur;
    }

    public void mouseClicked(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int xClicked = evt.getX();
            int yClciked = evt.getY();
            boolean found=false;
            for (PointLivraison p : vueGraphique.getTournee().getListePointLivraisons()) {
                int x = (int) ((p.getX() - vueGraphique.getXmin()) / vueGraphique.getxScale()) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2;
                int y = (int) ((p.getY() - vueGraphique.getYmin()) / vueGraphique.getyScale()) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2;
                if (xClicked > x - 10 && xClicked < x + 10) {
                    if (yClciked > y - 10 && yClciked < y + 10) {
                        controleur.clickedOnPoint(p);
                        found=true;
                    }
                }
            }
            if (!found) {
                controleur.clickedOnPoint(null);
            }


        } else if (evt.getButton()==MouseEvent.BUTTON3) {
            int xClicked = evt.getX();
            int yClciked = evt.getY();

            for (Noeud noeud:vueGraphique.getPlan().getListeNoeuds()) {
                int x = (int) ((noeud.getX() - vueGraphique.getXmin()) / vueGraphique.getxScale()) + LEFT_OFFSET ;
                int y = (int) ((noeud.getY() - vueGraphique.getYmin()) / vueGraphique.getyScale()) + LEFT_OFFSET ;
                if (xClicked > x - 5 && xClicked < x + 5) {
                    if (yClciked > y - 5 && yClciked < y + 5) {
                        controleur.rightClickedOnPoint(noeud);
                        System.out.println("rightClicked");
                        break;
                    }
                }
            }
        }
    }

}
