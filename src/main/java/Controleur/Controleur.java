package Controleur;

import FeuilleDeRoute.FeuilleDeRoute;
import Modele.*;
import Vue.FenetrePrincipale;
import java.io.IOException;

/**
 * @author H4401
 * Classe Contrôleur du logiciel
 */
public class Controleur {
    private FenetrePrincipale fenetrePrincipale;
    private Plan plan;
    private Tournee tournee;
    private LstDeCde lstDeCde = new LstDeCde();
    private static final EtatInit etatInit = new EtatInit();
    private static final EtatChargerPlan etatChargerPlan = new EtatChargerPlan();
    private static final EtatChargerLivraison etatChargerLivraison = new EtatChargerLivraison();
    private static final EtatCalculerTournee etatCalculerTournee = new EtatCalculerTournee();
    private static Etat etatCourant;

    /**
     * Méthode parmettant de changer l'état courant
     * @param etat
     */
    private static void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }

    /**
     * Constructeur par défaut du contrôleur
     */
    public Controleur() {
        plan = new Plan();
        tournee = new Tournee();
        fenetrePrincipale = new FenetrePrincipale(plan, tournee, this);
        setEtatCourant(etatInit);
    }

    /**
     * Méthode chargeant le plan depuis un fichier xml (par appel au package chargeur XML)
     * L'état est actualisé si le chargement a réussi
     * @param filePath Le chemin d'accès au fichier xml
     */
    public void chargerPlan (String filePath)
    {
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(null);
        plan.reinitialise();
        tournee.reinitialise();
        if(etatCourant.chargerPlan(filePath, plan))
            setEtatCourant(etatChargerPlan);

    }

    /**
     * Méthode chargeant les points de livraison depuis un fichier xml (par appel au package ChargeurXML)
     * L'état est actualisé si le chargement a réussi
     * La liste de commande est réinitialisée
     * @param filePath Le chemin d'accès au fichier xml
     */
    public void chargerLivraison (String filePath){
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(null);
        tournee.reinitialise();
        if(etatCourant.chargerLivraison(filePath, tournee))
            setEtatCourant(etatChargerLivraison);
        lstDeCde = new LstDeCde();
    }

    /**
     * Méthode lançant le calcul de la tournée
     */
    public void calculerTournee () {
        etatCourant.calculerTournee(plan, tournee);
        setEtatCourant(etatCalculerTournee);
    }

    /**
     * Méthode générant la feuille de route
     * @param filePath Le chemin d'accès au fichier généré
     * @throws IOException
     */
    public void sortirFeuilleDeRoute(String filePath) throws IOException {
        FeuilleDeRoute.sortirFeuilleDeRoute(filePath, tournee);
    }


    /**
     * Méthode permettant de supprimer un point de livraison de la tournée
     * @param pointLivraison Point à supprimer
     */
    public void supprimerPoint(PointLivraison pointLivraison) {
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(null);
        etatCourant.cdeSupprimerLivraison(pointLivraison,tournee,lstDeCde);
    }

    /**
     * Méthode permettant de modifier la plage horaire d'un point de livraison
     * @param pointLivraison Point à modifier
     * @param debutPlage
     * @param finPlage
     */
    public void modifierPlageHoraire(PointLivraison pointLivraison,Double debutPlage,Double finPlage){
        etatCourant.cdeModifierPlageHoraire(pointLivraison,tournee,debutPlage,finPlage,lstDeCde);
    }

    /**
     * Méthode permettant d'annuler la dernière modification affectuée (Ajout/Suppression/modification de plage horaire)
     */
    public void undo()
    {
        etatCourant.undo(lstDeCde);
        fenetrePrincipale.getVueGraphique().repaint();
        fenetrePrincipale.getVueTextuelle().repaint();
    }

    /**
     * Méthode permettant d'annuler le dernier "Undo" effectué
     */
    public void redo()
    {
        etatCourant.redo(lstDeCde);
        fenetrePrincipale.getVueGraphique().repaint();
        fenetrePrincipale.getVueTextuelle().repaint();
    }

    /**
     * Méthode permettant d'afficher les informations du point cliqué
     * @param pointLivraison
     */
    public void clickedOnPoint(PointLivraison pointLivraison) {
        etatCourant.clickedOnPoint(pointLivraison, fenetrePrincipale);
    }

    /**
     * Méthode permettant de lancer la fenêtre d'ajout de point de livraison lors d'un clic droit sur un noeud
     * @param noeud
     */
    public void rightClickedOnPoint (Noeud noeud) {
        etatCourant.rightClickedOnPoint(noeud,this);
    }

    /**
     * Méthode ajoutant un noeud à la tournée
     * @param noeud
     * @param debut
     * @param fin
     * @param duree
     */
    public void addPointLivraison(Noeud noeud,Double debut, Double fin, Double duree) {
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(null);
        etatCourant.cdeAjouterLivraison(noeud,debut,fin,duree,tournee,lstDeCde);
    }

    /**
     * Méthode Get
     * @return tournee
     */
    public Tournee getTournee() {
        return tournee;
    }

}
