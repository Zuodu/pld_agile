package Vue;

import Controleur.Controleur;
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

            for (PointLivraison p : vueGraphique.getTournee().getListePointLivraisons()) {
                int x = (int) ((p.getX() - vueGraphique.getXmin()) / vueGraphique.getxScale()) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2;
                int y = (int) ((p.getY() - vueGraphique.getYmin()) / vueGraphique.getyScale()) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2;
                if (xClicked > x - 10 && xClicked < x + 10) {
                    if (yClciked > y - 10 && yClciked < y + 10) {
                        controleur.clickedOnPoint(p);
                    }
                }
            }

        }
    }

}
