package ChargeurXML;

import Modele.Noeud;
import Modele.PointLivraison;
import Modele.Troncon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Created by flavi on 2017/11/18.
 */
public class ChargeurLivraison {
    private List<PointLivraison> pointLivraisons;
    private static ChargeurLivraison instance;

    public static ChargeurLivraison getInstance() {
        if (instance==null) {
            instance=new ChargeurLivraison();
        }
        return instance;
    }

    public ChargeurLivraison() {
        pointLivraisons=new ArrayList<PointLivraison>();
    }

    public List<PointLivraison> getPointLivraisons() {
        return pointLivraisons;
    }

    public boolean parse(String filePath) {
        if (ChargeurPlan.getInstance().getPlan().getListeNoeuds().size() == 0) return false;


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

            NodeList nodes = rootElement.getElementsByTagName("livraison");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node instanceof Element) {
                    Element child = (Element) node;
                    String idAtt = child.getAttribute("adresse");
                    String dureeAtt = child.getAttribute("duree");
                    String debutAtt = child.getAttribute("debutPlage");
                    String finAtt = child.getAttribute("finPlage");

                    double debutPlage = 0, finPlage = 0;
                    if (!debutAtt.isEmpty()) {
                        debutPlage = Double.parseDouble(debutAtt.substring(0, debutAtt.indexOf(':'))) * 3600
                                + Double.parseDouble(debutAtt.substring(debutAtt.indexOf(':') + 1, debutAtt.indexOf(':', debutAtt.indexOf(':') + 1))) * 60
                                + Double.parseDouble(debutAtt.substring(debutAtt.lastIndexOf(':') + 1, debutAtt.length()));
                    }
                    if (!finAtt.isEmpty()) {
                        finPlage = Double.parseDouble(finAtt.substring(0, finAtt.indexOf(':'))) * 3600
                                + Double.parseDouble(finAtt.substring(finAtt.indexOf(':') + 1, finAtt.indexOf(':', finAtt.indexOf(':') + 1))) * 60
                                + Double.parseDouble(finAtt.substring(finAtt.lastIndexOf(':') + 1, finAtt.length()));
                    }

                    int x = 0, y = 0;

                    HashSet<Noeud> noeuds = (HashSet<Noeud>) ChargeurPlan.getInstance().getPlan().getListeNoeuds();
                    Iterator iterator = noeuds.iterator();
                    while (iterator.hasNext()) {
                        Noeud n = (Noeud) iterator.next();
                        if (n.getId() == Long.parseLong(idAtt)) {
                            x = n.getX();
                            y = n.getY();
                        }
                    }

                    if (x == 0 && y == 0) return false;

                    System.out.println(debutPlage);
                    pointLivraisons.add(new PointLivraison(Long.parseLong(idAtt), x, y, Double.parseDouble(dureeAtt), debutPlage, finPlage));
                }
            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}