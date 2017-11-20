package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Created by flavi on 2017/11/20.
 */
public class VuePlan extends JPanel {
    private Plan plan;
    private ListePointLivraisons pointLivraisons;

    public VuePlan() {

    }

    public void addPlan(Plan plan) {
        this.plan = plan;
        repaint();
    }

    public void addPointLivraisons(ListePointLivraisons listePointLivraisons) {
        this.pointLivraisons = listePointLivraisons;
        repaint();
    }

    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
/**
 for (Noeud noeud:plan.getListeNoeuds()) {
 g2d.drawOval((int)(noeud.getX()-12000)/15,
 (int)(noeud.getY()-20000)/40,
 3,
 3)
 ;
 }**/

        for (Troncon troncon : plan.getListeTroncons()) {
            g2d.drawLine((troncon.getOrigine().getX() - 12000) / 15,
                    (troncon.getOrigine().getY() - 20000) / 40,
                    (troncon.getDestination().getX() - 12000) / 15,
                    (troncon.getDestination().getY() - 20000) / 40

            );
        }

        if (pointLivraisons != null) {
            for (PointLivraison pointLivraison : pointLivraisons.getPointLivraisons()) {
                g2d.drawOval((int) (pointLivraison.getX() - 12000) / 15,
                        (int) (pointLivraison.getY() - 20000) / 40,
                        5,
                        5)
                ;
            }
        }

    }


}
