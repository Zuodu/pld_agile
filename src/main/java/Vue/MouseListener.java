package Vue;

import Controleur.Controleur;
import Modele.PointLivraison;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by flavi on 2017/12/2.
 */
public class MouseListener extends MouseAdapter {
    private VuePlan vuePlan;
    private Controleur controleur;

    public MouseListener(VuePlan vuePlan, Controleur controleur) {
        this.vuePlan = vuePlan;
        this.controleur = controleur;
    }

    public void mouseClicked(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {

            double x = (evt.getX() - 25) * vuePlan.getxScale() + vuePlan.getXmin();
            double y = (evt.getY() - 15) * vuePlan.getyScale() + vuePlan.getYmin();
            System.out.println("clicked on " + x);
            System.out.println("clicked on " + y);
            for (PointLivraison p : vuePlan.getTournee().getListePointLivraisons()) {
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
