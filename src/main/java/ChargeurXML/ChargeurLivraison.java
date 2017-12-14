package ChargeurXML;

import Modele.Noeud;
import Modele.PointLivraison;
import Modele.Tournee;
import Modele.Troncon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author H4401
 *         Classe permettant le chargement du fichier xml contenant les livraisons
 */
public class ChargeurLivraison {
    private Tournee tournee;
    private static ChargeurLivraison instance;

    /**
     * Méthode Get pour l'instance
     *
     * @return instance l'instance de chargeur
     */
    public static ChargeurLivraison getInstance() {
        if (instance == null) {
            instance = new ChargeurLivraison();
        }
        return instance;
    }

    /**
     * Constructeur par défaut vide
     */
    public ChargeurLivraison() {

    }

    /**
     * Méthode parsant le fichier xml
     *
     * @param tournee  La tournée à laquelle on ajoute les points de livraison
     * @param filePath Le chemin d'accès au fichier xml
     * @return return true si l'execution se déroule bien
     */
    public boolean parse(Tournee tournee, String filePath) {
        this.tournee = tournee;
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

            parseEntrepot(rootElement);
            NodeList nodes = rootElement.getElementsByTagName("livraison");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node instanceof Element) {
                    Element child = (Element) node;
                    String idAtt = child.getAttribute("adresse");
                    String dureeAtt = child.getAttribute("duree");
                    String debutAtt = child.getAttribute("debutPlage");
                    String finAtt = child.getAttribute("finPlage");
                    //System.out.println(debutAtt + " " + finAtt);
                    double debutPlage = -1, finPlage = -1;
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
                    Set<Troncon> neighbors = null;

                    Set<Noeud> noeuds = ChargeurPlan.getInstance().getPlan().getListeNoeuds();
                    for (Noeud n : noeuds) {
                        if (n.getId() == Long.parseLong(idAtt)) {
                            x = n.getX();
                            y = n.getY();
                            neighbors = n.getNeighbors();
                        }
                    }

                    if (x == 0 && y == 0) return false;

                    if (debutPlage != -1 && finPlage != -1) {
                        PointLivraison pointLivraison = new PointLivraison(Long.parseLong(idAtt), x, y, Double.parseDouble(dureeAtt), debutPlage, finPlage);
                        pointLivraison.setNeighbors(neighbors);
                        tournee.getListePointLivraisons().add(pointLivraison);
                    } else {
                        PointLivraison pointLivraison = new PointLivraison(Long.parseLong(idAtt), x, y, Double.parseDouble(dureeAtt));
                        pointLivraison.setNeighbors(neighbors);
                        tournee.getListePointLivraisons().add(pointLivraison);
                    }
                }
            }

            tournee.setCharge(true);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Get tournée
     *
     * @return tournee
     */
    public Tournee getTournee() {
        return tournee;
    }

    /**
     * Méthode qui parse l'entrepôt à part des autres points de livraison
     *
     * @param rootElement
     */
    private void parseEntrepot(Element rootElement) {
        NodeList entrepotNodeListe = rootElement.getElementsByTagName("entrepot");

        Node nodeEntrepot = entrepotNodeListe.item(0);
        Element nodeChild = (Element) nodeEntrepot;
        String adr = nodeChild.getAttribute("adresse");
        String hd = nodeChild.getAttribute("heureDepart");
        int x = 0;
        int y = 0;
        Double heuredepart = Double.parseDouble(hd.substring(0, hd.indexOf(':'))) * 3600
                + Double.parseDouble(hd.substring(hd.indexOf(':') + 1, hd.indexOf(':', hd.indexOf(':') + 1))) * 60
                + Double.parseDouble(hd.substring(hd.lastIndexOf(':') + 1, hd.length()));


        Set<Noeud> noeuds = ChargeurPlan.getInstance().getPlan().getListeNoeuds();
        Set<Troncon> neighbors = null;
        for (Noeud noeud : noeuds) {
            if (noeud.getId() == Long.parseLong(adr)) {
                x = noeud.getX();
                y = noeud.getY();
                neighbors = noeud.getNeighbors();
                break;
            }

        }
        PointLivraison pointLivraison = new PointLivraison(Long.parseLong(adr), x, y, 0D);
        pointLivraison.setNeighbors(neighbors);
        tournee.setEntrepot(pointLivraison);

        tournee.setHeureDeDepart(heuredepart);

    }
}