package Vue;

import Controleur.Controleur;
import Modele.PointLivraison;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

            double x = (evt.getX() - 25) * vueGraphique.getxScale() + vueGraphique.getXmin();
            double y = (evt.getY() - 15) * vueGraphique.getyScale() + vueGraphique.getYmin();
            System.out.println("clicked on " + x);
            System.out.println("clicked on " + y);
            for (PointLivraison p : vueGraphique.getTournee().getListePointLivraisons()) {
                if (x > p.getX() - 500 && x < p.getX() + 500) {
                    if (y > p.getY() - 500 && y < p.getY() + 500) {
                        System.out.println("clicked : " + p);
                        controleur.clickedOnPoint(p);
                    }
                }
            }

        }
    }

}
