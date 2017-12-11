package Vue;

import Algo.IteratorMin;
import Modele.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @autor H4401
 * Classe permettant l'affichage du plan, des points de livraison et de l'itinéraire sur l'interface,
 */
public class VueGraphique extends JPanel {

    public final static int RAYON_POINTLIVRAISON = 10;
    public final static int RAYON_ENTREPOT = 20;
    public final static float WIDTH_TRONCON = 2.0f;
    public final static int VUEPLAN_LENGTH = 700;
    public final static int VUEPLAN_WIDTH = 700;
    public final static int LEFT_OFFSET = 10;
    public final static int UP_OFFSET = 10;
    public final static int R_BG = 166;
    public final static int G_BG = 170;
    public final static int B_BG = 173;
    private Plan plan;
    private Tournee tournee;
    int xmin;
    int ymin;
    int xmax;
    int ymax;
    double xScale;
    double yScale;
    private PointLivraison pointLivraisonChoisi;

    /**
     * setter point livraison choisi
     * @param pointLivraisonChoisi
     */
    public void setPointLivraisonChoisi(PointLivraison pointLivraisonChoisi) {
        this.pointLivraisonChoisi = pointLivraisonChoisi;
    }

    /**
     * Constructeur
     *
     * @param plan
     * @param tournee
     */
    public VueGraphique(Plan plan, Tournee tournee) {
        setLayout(null);
        setBackground(new Color(R_BG, G_BG, B_BG));
        this.plan = plan;

        this.tournee = tournee;

        this.setBounds(LEFT_OFFSET, UP_OFFSET, VUEPLAN_WIDTH, VUEPLAN_LENGTH);

    }

    /**
     * Ajoute un plan
     *
     * @param plan
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
        repaint();
    }

    /**
     * Ajoute une tournée
     *
     * @param tournee
     */
    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
        repaint();

    }

    /**
     * getter pointLivraisonChoisi
     * @return
     */
    public PointLivraison getPointLivraisonChoisi() {
        return pointLivraisonChoisi;
    }

    /**
     * Méthode paint
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        xmin = 9999999;
        ymin = 9999999;
        xmax = 0;
        ymax = 0;


        if (plan.isCharge()) {
            g2d.setColor(Color.BLACK);
            for (Noeud noeud : plan.getListeNoeuds()) {
                if (noeud.getX() > xmax)
                    xmax = noeud.getX();
                if (noeud.getY() > ymax)
                    ymax = noeud.getY();
                if (noeud.getX() < xmin)
                    xmin = noeud.getX();
                if (noeud.getY() < ymin)
                    ymin = noeud.getY();
                xScale = (xmax - xmin) / VUEPLAN_LENGTH / 0.95;
                yScale = (ymax - ymin) / VUEPLAN_WIDTH / 0.95;


            }
            g2d.setColor(Color.white);
            g2d.setStroke(new BasicStroke(WIDTH_TRONCON));
            for (Troncon troncon : plan.getListeTroncons()) {
                afficheTroncon(troncon, g2d);
            }
        }

        if (tournee.isCharge()) {

            g2d.setColor(Color.blue);
            int count = 0;
            Iterator<PointLivraison> pointLivraisonIterator = tournee.getListePointLivraisons().iterator();
            while (pointLivraisonIterator.hasNext()) {
                PointLivraison tmp = pointLivraisonIterator.next();
                if (pointLivraisonIterator.hasNext()) {
                    affichePointLivraison(tmp, g2d, count);
                    count++;
                }
            }
            afficheEntrepot(tournee.getEntrepot(), g2d);

            g2d.setColor(Color.magenta);
            for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itineraireEntry : tournee.getItinerairesMap().entrySet()) {
                Iterator<Troncon> it = itineraireEntry.getValue().getListeTroncons().iterator();
                while (it.hasNext()) {
                    Troncon troncon = it.next();
                    if (it.hasNext()) {
                        afficheTroncon(troncon, g2d);

                    } else {
                        afficheDernierTroncon(troncon, g2d);
                    }
                }
            }
            if (pointLivraisonChoisi != null) {
                try {
                    Image image = ImageIO.read(new File("target.png"));
                    g2d.drawImage(image, (int) ((pointLivraisonChoisi.getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2,
                            (int) ((pointLivraisonChoisi.getY() - ymin) / yScale) + UP_OFFSET - RAYON_POINTLIVRAISON / 2, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int index = 0;
                for (int i = 1; i < tournee.getListePointLivraisons().size(); i++) {
                    if (Objects.equals(tournee.getListePointLivraisons().get(i).getId(), pointLivraisonChoisi.getId())) {
                        index = i;
                    }
                }
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(4.0f));
                for (Troncon t : tournee.getItinerairesMap().get(new AbstractMap.SimpleEntry<>(tournee.getListePointLivraisons().get(index - 1), tournee.getListePointLivraisons().get(index))).getListeTroncons()) {
                    afficheTroncon(t, g2d);
                }
            }


        }
    }

    /**
     * getter
     * @return
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * getter
     * @return
     */
    public Tournee getTournee() {
        return tournee;
    }

    /**
     * getter
     * @return
     */
    public int getXmin() {
        return xmin;
    }

    /**
     * getter
     * @return
     */
    public int getYmin() {
        return ymin;
    }

    /**
     * getter
     * @return
     */
    public double getxScale() {
        return xScale;
    }

    /**
     * getter
     * @return
     */
    public double getyScale() {
        return yScale;
    }

    /**
     * afficher un troncon sous forme d'une ligne
     * @param troncon :le troncon a afficher
     * @param g2d     :instance Graphics2D
     */
    private void afficheTroncon(Troncon troncon, Graphics2D g2d) {

        int x1 = (int) ((troncon.getOrigine().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y1 = (int) ((troncon.getOrigine().getY() - ymin) / yScale) + UP_OFFSET;
        int x2 = (int) ((troncon.getDestination().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y2 = (int) ((troncon.getDestination().getY() - ymin) / yScale) + UP_OFFSET;
        g2d.drawLine(x1, y1, x2, y2
        );
    }
    /**
     * afficher le dernier troncon d'un itineraire, afficher un fleche a la fin.
     * @param troncon :le troncon a afficher
     * @param g2d     :instance Graphics2D
     */
    private void afficheDernierTroncon(Troncon troncon, Graphics2D g2d) {
        int x1 = (int) ((troncon.getOrigine().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y1 = (int) ((troncon.getOrigine().getY() - ymin) / yScale) + UP_OFFSET;
        int x2 = (int) ((troncon.getDestination().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y2 = (int) ((troncon.getDestination().getY() - ymin) / yScale) + UP_OFFSET;
        drawAL(x1, y1, x2, y2, g2d);
    }

    /**
     * afficher un point de livraison avec un cercle et le numero de parcours dans la tournee
     * @param pointLivraison :le point a afficher
     * @param g2d            :instance de Grapics2D
     * @param count          :le numero dans la tournee
     */
    private void affichePointLivraison(PointLivraison pointLivraison, Graphics2D g2d, int count) {
        g2d.fillOval((int) ((pointLivraison.getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2,
                (int) ((pointLivraison.getY() - ymin) / yScale) + UP_OFFSET - RAYON_POINTLIVRAISON / 2,
                RAYON_POINTLIVRAISON,
                RAYON_POINTLIVRAISON);
        if (count != 0) {
            g2d.drawString("No. " + count,
                    (int) ((pointLivraison.getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2,
                    (int) ((pointLivraison.getY() - ymin) / yScale) + UP_OFFSET - RAYON_POINTLIVRAISON / 2);
        }
    }

    /**
     * afficher l'entrepot sous forme d'un cercle
     * @param pointLivraison
     * @param g2d
     */
    private void afficheEntrepot(PointLivraison pointLivraison, Graphics2D g2d) {
        g2d.fillOval((int) ((tournee.getEntrepot().getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_ENTREPOT / 2,
                (int) ((tournee.getEntrepot().getY() - ymin) / yScale) + UP_OFFSET - RAYON_ENTREPOT / 2,
                RAYON_ENTREPOT,
                RAYON_ENTREPOT);
    }


    /**
     * draw une ligne avec un fleche a la fin
     * @param sx :start x
     * @param sy :start y
     * @param ex :end x
     * @param ey :end y
     * @param g2 :instance Graphics2D
     */
    private static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {

        double H = 14;
        double L = 6;
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];

        Double X3 = x_3;
        x3 = X3.intValue();
        Double Y3 = y_3;
        y3 = Y3.intValue();
        Double X4 = x_4;
        x4 = X4.intValue();
        Double Y4 = y_4;
        y4 = Y4.intValue();
        g2.drawLine(sx, sy, ex, ey);
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();

        g2.fill(triangle);


    }

    /**
     * calculer l'angle de rotation pour le fleche a la fin d'une ligne
     * @param px
     * @param py
     * @param ang
     * @param isChLen
     * @param newLen
     * @return
     */
    private static double[] rotateVec(int px, int py, double ang,
                                     boolean isChLen, double newLen) {

        double mathstr[] = new double[2];
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }


}
