package ChargeurXML;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Created by flavi on 2017/11/18.
 */
public class ChargeurPlan {
    private Plan plan;
    private static ChargeurPlan instance;

    public static ChargeurPlan getInstance() {
        if (instance==null) {
            instance=new ChargeurPlan();
        }
        return instance;
    }

    public ChargeurPlan() {

    }

    public void parse(Plan plan, String filePath) {
        this.plan = plan;
        File xmlPlan = new File(filePath);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            Document documentPlan = builder.parse(xmlPlan);
            Element rootElement = documentPlan.getDocumentElement();

            NodeList nodes = rootElement.getElementsByTagName("noeud");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node instanceof Element) {
                    Element child = (Element) node;
                    String idAtt = child.getAttribute("id");
                    String xAtt = child.getAttribute("x");
                    String yAtt = child.getAttribute("y");

                    plan.addNoeud(new Noeud(Long.parseLong(idAtt), Integer.parseInt(xAtt), Integer.parseInt(yAtt)));
                }
            }

            NodeList vertexes = rootElement.getElementsByTagName("troncon");

            for (int i = 0; i < vertexes.getLength(); i++) {
                Node node = vertexes.item(i);

                if (node instanceof Element) {
                    Element child = (Element) node;
                    String destinationAtt = child.getAttribute("destination");
                    String longueurAtt = child.getAttribute("longueur");
                    String rueAtt = child.getAttribute("nomRue");
                    String origineAtt = child.getAttribute("origine");

                    Noeud noeudOrigine = null, noeudDestination = null;

                    HashSet<Noeud> noeuds = (HashSet<Noeud>) plan.getListeNoeuds();

                    Iterator iterator = noeuds.iterator();
                    while (iterator.hasNext()) {
                        Noeud n = (Noeud) iterator.next();
                        if (n.getId() == Long.parseLong(origineAtt)) {
                            noeudOrigine = n;
                        }
                        if (n.getId() == Long.parseLong(destinationAtt)) {
                            noeudDestination = n;
                        }
                        if (noeudOrigine != null && noeudDestination != null) break;
                    }
                    Troncon troncon=new Troncon(noeudOrigine, noeudDestination, Double.parseDouble(longueurAtt), rueAtt);
                    noeudOrigine.addNeighbor(troncon);
                    plan.addTroncon(troncon);
                }
            }
            plan.signalerFin();

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Plan getPlan() {
        return plan;
    }
}