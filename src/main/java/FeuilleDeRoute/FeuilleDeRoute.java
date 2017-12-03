package FeuilleDeRoute;

import Modele.Itineraire;
import Modele.PointLivraison;
import Modele.Tournee;
import Modele.Troncon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static j2html.TagCreator.*;

/**
 * @author H4401
 * Classe utilisée pour générer une feuille de route après le calcul d'une tournée
 */
public class FeuilleDeRoute {

    /**
     * Génère une feuille de route HTML à partir d'un Path choisi et d'une Tournee.
     *
     * @param filePath Path choisie par l'utilisateur depuis la boîte de dialogue associée
     * @param tournee  la tournée calculée par le logiciel
     */
    public static void sortirFeuilleDeRoute(String filePath, Tournee tournee) throws IOException {
        List<Itineraire> listeOrdonneeItineraire = ordonnerItineraires(tournee);
        List<LigneFeuille> list = buildTableDeRoute(listeOrdonneeItineraire);
        String html = buildHtml(list);
        FileWriter fWriter = null;
        BufferedWriter writer = null;
        fWriter = new FileWriter(filePath + "\\FeuilleDeRoute.html");
        writer = new BufferedWriter(fWriter);
        writer.write(html);
        writer.close();
    }

    /**
     * Constuit le texte HTML sous la forme d'un string pour une tournée donnée.
     *
     * @param list Liste de listes contenant les champs à écrire
     * @return html le contenu html
     */
    private static String buildHtml(List<LigneFeuille> list) {
        String html = html(
                head(
                        meta().withCharset("ISO-8859-1"),
                        title("Feuille de route")
                ),
                body(
                        main(attrs("#main.content"),
                                h1("Feuille de route OptimodLyon")
                        ),
                        table(attrs("#table")),
                        tbody(
                                tr(
                                        th("Heure d'arrivée"),
                                        th("Heure de départ"),
                                        th("Indication"),
                                        th("Emplacement"),
                                        th("Longueur du trajet")
                                ),
                                each(list, row -> tr(
                                        td(row.gethDepart()),
                                        td(row.gethArrivee()),
                                        td(row.getIndication()),
                                        td(row.getRue()),
                                        td(row.getLongueur())
                                        )
                                )
                        )
                )
        ).render();
        return html;
    }

    /**
     * Créée une table afin d'extraire uniquement les données à afficher sur la feuille de route.
     *
     * @param tournee tournée calculée
     * @return la liste des lignes de la feuille de route à faire traiter par le module html
     */
    private static List<LigneFeuille> buildTableDeRoute(List<Itineraire> listeItineraire) {
        List<LigneFeuille> mainList = new ArrayList<LigneFeuille>();
        int livraisonId = 1;
        //boucle itinéraire
        for (Itineraire i : listeItineraire) {
            PileTroncon lastPile = null;
            //add depart
            LigneFeuille ligneDepart = new LigneFeuille(
                    ((PointLivraison) i.getNoeudOrigine()).getHeureArrivee(),
                    ((PointLivraison) i.getNoeudOrigine()).getHeureDepart(),
                    Indication.DebutItineraire.getTexte(),
                    Integer.toString(livraisonId),
                    0);
            mainList.add(ligneDepart);
            //foreach tronçon
            for (Troncon t : i.getListeTroncons()) {
                if ((lastPile == null) || !(t.getNomRue().equals(lastPile.getTroncon().getNomRue()))) {
                    //premier troncon
                    if (i.getListeTroncons().getFirst() == t) {
                        lastPile = new PileTroncon(t, Indication.Demarrage);
                    } else {
                        //calcul indication
                        Vecteur u = new Vecteur(lastPile.getTroncon().getOrigine(), lastPile.getTroncon().getDestination());
                        Vecteur v = new Vecteur(t.getOrigine(), t.getDestination());
                        Indication indic = calculerIndication(u, v);
                        //nouvelle ligne feuille de la derniere pile
                        LigneFeuille ligne = new LigneFeuille(
                                0,
                                0,
                                lastPile.getIndication().getTexte(),
                                lastPile.getTroncon().getNomRue(),
                                lastPile.getDistanceTotale());
                        //ajout ligne feuille
                        mainList.add(ligne);
                        //reset de la pile
                        lastPile = new PileTroncon(t, indic);
                    }
                } else {
                    lastPile.addTroncon(t);
                }
            }
            //add last pile
            LigneFeuille ligneDernierePile = new LigneFeuille(
                    0,
                    0,
                    lastPile.getIndication().getTexte(),
                    lastPile.getTroncon().getNomRue(),
                    lastPile.getDistanceTotale());
            //add arrivee itineraire
            LigneFeuille ligneArrivee = new LigneFeuille(
                    ((PointLivraison) i.getNoeudDestination()).getHeureArrivee(),
                    ((PointLivraison) i.getNoeudDestination()).getHeureDepart(),
                    Indication.FinItineraire.getTexte(),
                    Integer.toString(livraisonId++),
                    0);
            mainList.add(ligneDernierePile);
            mainList.add(ligneDepart);
        }

        return mainList;
    }

    /**
     * Calcule l'indication à prendre depuis une énumération Indication
     *
     * @param u Vecteur représentant le tronçon de départ
     * @param v Vecteur représentant le tronçon à prendre
     * @return Item de l'énumération Indication
     */
    private static Indication calculerIndication(Vecteur u, Vecteur v) {
        double angle = Math.toDegrees(Math.atan2((u.x * v.y) - (u.y * v.x), (u.x * v.x) + (u.y * v.y)));
        if ((angle >= 170 && angle <= 190) || (angle <= -170 && angle >= -190)) return Indication.ToutDroit;
        else if (angle > 0) return Indication.AGauche;
        else return Indication.ADroite;
    }

    /**
     * Réordonne les itinéraires d'une tournée par recherche dans la liste des points de livraison
     *
     * @param tournee la tournée calculée
     * @return la liste ordonnée des itinéraires
     */
    private static List<Itineraire> ordonnerItineraires(Tournee tournee) {
        List<PointLivraison> listePoints = tournee.getListePointLivraisons();
        List<Itineraire> listeOrdonnee = new ArrayList<>();
        for (int i = 1; i < listePoints.size(); i++) {
            Map.Entry<PointLivraison, PointLivraison> key = new AbstractMap.SimpleEntry<>(listePoints.get(i - 1), listePoints.get(i));
            listeOrdonnee.add(tournee.getItinerairesMap().get(key));
        }
        return listeOrdonnee;
    }
}
