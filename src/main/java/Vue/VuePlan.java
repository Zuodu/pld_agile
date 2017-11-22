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
    private Tournee tournee;

    public VuePlan() {

    }

    public void addPlan(Plan plan) {
        this.plan = plan;
        repaint();
    }

    public void addTournee(Tournee tournee) {
        this.tournee = tournee;
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
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
        g2d.setColor(Color.GREEN);
        if (tournee != null) {
            for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
                g2d.fillOval((int) (pointLivraison.getX() - 12000) / 15 - 5,
                        (int) (pointLivraison.getY() - 20000) / 40 - 5,
                        10,
                        10);
                ;
            }
            g2d.fillOval((int) (tournee.getEntrepot().getX() - 12000) / 15 - 10,
                    (int) (tournee.getEntrepot().getY() - 20000) / 40 - 10,
                    20,
                    20);
            g2d.setColor(Color.RED);
            for (Itineraire itineraire : tournee.getListeItineraires()) {
                for (Troncon troncon : itineraire.getListeTroncons()) {
                    g2d.drawLine((troncon.getOrigine().getX() - 12000) / 15,
                            (troncon.getOrigine().getY() - 20000) / 40,
                            (troncon.getDestination().getX() - 12000) / 15,
                            (troncon.getDestination().getY() - 20000) / 40

                    );
                }
            }
        }

    }


}
