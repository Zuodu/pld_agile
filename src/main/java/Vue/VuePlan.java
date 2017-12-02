package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Set;

/**
 * @autor H4401
 * Classe permettant l'affichage du plan, des points de livraison et de l'itinéraire sur l'interface,
 */
public class VuePlan extends JPanel {
    private Plan plan;
    private Tournee tournee;
    int xmin=9999999;
    int ymin=9999999;
    int xmax=0;
    int ymax=0;
    double xScale;
    double yScale;
    private JTextPane textPane;

    public VuePlan() {
        textPane=new JTextPane();
        this.add(textPane);

    }

    /**
     * Ajoute un plan
     * @param plan
     */
    public void addPlan(Plan plan) {
        this.plan = plan;
        repaint();
    }

    /**
     * Ajoute une tournée
     * @param tournee
     */
    public void addTournee(Tournee tournee) {
        this.tournee = tournee;
        textPane.setText("");
        for (Itineraire itineraire : tournee.getListeItineraires()) {
            textPane.setText(textPane.getText() + itineraire.getNoeudOrigine().getId()+" -> ");
        }
        textPane.setText(textPane.getText()+tournee.getEntrepot().getId());
        repaint();

    }

    /**
     * Méthode paint
     * @param g
     */
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
        if(plan!=null) {

            for (Noeud noeud:plan.getListeNoeuds()) {
                if (noeud.getX()>xmax)
                    xmax=noeud.getX();
                if(noeud.getY()>ymax)
                    ymax=noeud.getY();
                if (noeud.getX()<xmin)
                    xmin=noeud.getX();
                if(noeud.getY()<ymin)
                    ymin=noeud.getY();
            }
//            System.out.println("XMIN YMIN"+xmin+" "+ymin);
//            System.out.println("XMAX YMAX"+xmax+" "+ymax);
//            System.out.println("SCALE "+xScale+" "+yScale);

             xScale=(xmax-xmin)/1150;
             yScale=(ymax-ymin)/1150;

            for (Troncon troncon : plan.getListeTroncons()) {
                int x1=(int)((troncon.getOrigine().getX() - xmin) / xScale)+30;
                int y1=(int)((troncon.getOrigine().getY() - ymin) / yScale)+20;
                int x2=(int) ((troncon.getDestination().getX() - xmin) / xScale)+30;
                int y2=(int)((troncon.getDestination().getY() - ymin) / yScale)+20;
                g2d.drawLine(x1,y1,x2,y2
                );
            }
        }
        g2d.setColor(Color.GREEN);
        if (tournee != null) {
            for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
                g2d.fillOval((int) ((pointLivraison.getX() - xmin) / xScale )+25,
                        (int) ((pointLivraison.getY() - ymin) / yScale )+15,
                        10,
                        10);
                ;
            }
            g2d.fillOval((int)( (tournee.getEntrepot().getX() - xmin) / xScale) +20,
                    (int)((tournee.getEntrepot().getY() - ymin) / yScale )+10,
                    20,
                    20);
            g2d.setColor(Color.RED);
            for (Itineraire itineraire : tournee.getListeItineraires()) {


                for (Troncon troncon : itineraire.getListeTroncons()) {
                    int x1=(int)((troncon.getOrigine().getX() - xmin) / xScale)+30;
                    int y1=(int)((troncon.getOrigine().getY() - ymin) / yScale)+20;
                    int x2=(int) ((troncon.getDestination().getX() - xmin) / xScale)+30;
                    int y2=(int)((troncon.getDestination().getY() - ymin) / yScale)+20;
                    drawAL(x1,y1,x2,y2,g2d

                    );
                }
            }
        }

    }

    public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2)
    {

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
