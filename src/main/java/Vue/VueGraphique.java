package Vue;

import Modele.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
    private Plan plan;
    private Tournee tournee;
    int xmin = 9999999;
    int ymin = 9999999;
    int xmax = 0;
    int ymax = 0;
    double xScale;
    double yScale;
    private JTextPane textPane;
    private PointLivraison pointLivraisonChoisi;

    public void setPointLivraisonChoisi(PointLivraison pointLivraisonChoisi) {
        this.pointLivraisonChoisi = pointLivraisonChoisi;
    }

    public VueGraphique() {
        setLayout(null);

        //textPane=new JTextPane();
        //this.add(textPane);
        this.setBounds(LEFT_OFFSET, UP_OFFSET, VUEPLAN_WIDTH, VUEPLAN_LENGTH);

    }

    /**
     * Ajoute un plan
     *
     * @param plan
     */
    public void addPlan(Plan plan) {
        this.plan = plan;
        repaint();
    }

    /**
     * Ajoute une tournée
     *
     * @param tournee
     */
    public void addTournee(Tournee tournee) {
        this.tournee = tournee;
        //textPane.setText("");
        //for (Itineraire itineraire : tournee.getListeItineraires()) {
            //  textPane.setText(textPane.getText() + itineraire.getNoeudOrigine().getId()+" -> ");
        //}
        //textPane.setText(textPane.getText()+tournee.getEntrepot().getId());
        repaint();

    }

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


        if (plan != null) {
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

        if (tournee != null) {

            g2d.setColor(Color.blue);
            for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
                affichePointLivraison(pointLivraison, g2d);
            }
            afficheEntrepot(tournee.getEntrepot(), g2d);

            g2d.setColor(Color.magenta);
            for (Map.Entry<Map.Entry<PointLivraison,PointLivraison>,Itineraire> itineraireEntry:tournee.getItinerairesMap().entrySet()) {
                for (Troncon troncon : itineraireEntry.getValue().getListeTroncons()) {
                    afficheTroncon(troncon, g2d);
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
        }


    }

    public Plan getPlan() {
        return plan;
    }

    public Tournee getTournee() {
        return tournee;
    }

    public int getXmin() {
        return xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public double getxScale() {
        return xScale;
    }

    public double getyScale() {
        return yScale;
    }

    public void afficheTroncon(Troncon troncon, Graphics2D g2d) {

        int x1 = (int) ((troncon.getOrigine().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y1 = (int) ((troncon.getOrigine().getY() - ymin) / yScale) + UP_OFFSET;
        int x2 = (int) ((troncon.getDestination().getX() - xmin) / xScale) + LEFT_OFFSET;
        int y2 = (int) ((troncon.getDestination().getY() - ymin) / yScale) + UP_OFFSET;
        g2d.drawLine(x1, y1, x2, y2
        );
    }

    public void affichePointLivraison(PointLivraison pointLivraison, Graphics2D g2d) {
        g2d.fillOval((int) ((pointLivraison.getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_POINTLIVRAISON / 2,
                (int) ((pointLivraison.getY() - ymin) / yScale) + UP_OFFSET - RAYON_POINTLIVRAISON / 2,
                RAYON_POINTLIVRAISON,
                RAYON_POINTLIVRAISON);
    }

    public void afficheEntrepot(PointLivraison pointLivraison, Graphics2D g2d) {
        g2d.fillOval((int) ((tournee.getEntrepot().getX() - xmin) / xScale) + LEFT_OFFSET - RAYON_ENTREPOT / 2,
                (int) ((tournee.getEntrepot().getY() - ymin) / yScale) + UP_OFFSET - RAYON_ENTREPOT / 2,
                RAYON_ENTREPOT,
                RAYON_ENTREPOT);
    }


    public JTextPane getTextPane() {
        return textPane;
    }

    public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {

        double H = 10; // ç®­å¤´é«˜åº¦
        double L = 4; // åº•è¾¹çš„ä¸€å�Š
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // ç®­å¤´è§’åº¦
        double arraow_len = Math.sqrt(L * L + H * H); // ç®­å¤´çš„é•¿åº¦
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)æ˜¯ç¬¬ä¸€ç«¯ç‚¹
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)æ˜¯ç¬¬äºŒç«¯ç‚¹
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // ç”»çº¿
        g2.drawLine(sx, sy, ex, ey);
        //
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();
        //å®žå¿ƒç®­å¤´
        g2.fill(triangle);
        //é�žå®žå¿ƒç®­å¤´
        //g2.draw(triangle);

    }

    // è®¡ç®—
    public static double[] rotateVec(int px, int py, double ang,
                                     boolean isChLen, double newLen) {

        double mathstr[] = new double[2];
        // çŸ¢é‡�æ—‹è½¬å‡½æ•°ï¼Œå�‚æ•°å�«ä¹‰åˆ†åˆ«æ˜¯xåˆ†é‡�ã€�yåˆ†é‡�ã€�æ—‹è½¬è§’ã€�æ˜¯å�¦æ”¹å�˜é•¿åº¦ã€�æ–°é•¿åº¦
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
