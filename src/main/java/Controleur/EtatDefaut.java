package Controleur;

import ChargeurXML.ChargeurPlan;
import Modele.*;
import Vue.FenetrePrincipale;

import javax.swing.*;

/**
 * @author H4401
 * Classe Etat par défaut, dont héritent  tous les autres états
 */
public class EtatDefaut implements Etat {

    /**
     * Méthode chargeant le plan depuis un fichier xml (par appel au package chargeur XML)
     * @param filePath Le chemin d'accès au fichier xml
     */
    public boolean chargerPlan (String filePath, Plan plan)
    {
        if(ChargeurPlan.getInstance().parse(plan, filePath))
            return true;
        JOptionPane.showMessageDialog(null,"Le chargement a échoué, veuillez réessayer");
        return false;
    }

    /**
     * Méthode chargeant les livraisons, affiche un message d'erreur par défaut
     * @param filePath
     * @param tournee
     * @return false
     */
    public boolean chargerLivraison(String filePath, Tournee tournee) {
        JOptionPane.showMessageDialog(null,"Veuillez charger un plan");
        return false;
    }

    /**
     * Méthode calculant la tournée, affiche un message d'erreur par défaut
     * @param plan
     * @param tournee
     */
    public void calculerTournee(Plan plan, Tournee tournee) {
        JOptionPane.showMessageDialog(null,"Veuillez charger un plan et des livraisons");
    }

    /**
     * Méthode permettant d'afficher les informations du point cliqué
     * @param pointLivraison
     * @param fenetrePrincipale
     */
    public void clickedOnPoint(PointLivraison pointLivraison, FenetrePrincipale fenetrePrincipale) {
        fenetrePrincipale.getVueTextuelle().clickedOnPoint(pointLivraison);
        fenetrePrincipale.getVueGraphique().setPointLivraisonChoisi(pointLivraison);
        fenetrePrincipale.getVueGraphique().repaint();
    }

    /**
     * Méthode permettant d'annuler la dernière modification affectuée (Ajout/Suppression/modification de plage horaire)
     * présente dans la liste de commande
     * @param l
     */
    public void undo(LstDeCde l) {
    	l.undo();
    }

    /**
     * Méthode permettant d'annuler le dernier "Undo" effectué
     * @param l
     */
    public void redo(LstDeCde l) {
    	l.redo();
    }

    /**
     * Ajouter un point de livraison n'est pas réalisable par défaut
     * @param noeud
     * @param debut
     * @param fin
     * @param duree
     * @param tournee
     * @param lstDeCde
     */
    public void cdeAjouterLivraison(Noeud noeud,Double debut, Double fin, double duree, Tournee tournee,LstDeCde lstDeCde) {
    }

    /**
     * Supprimer un point de livraison n'est pas réalisable par défaut
     * @param pointLivraison
     * @param tournee
     * @param lstDeCde
     */
    public void cdeSupprimerLivraison(PointLivraison pointLivraison,Tournee tournee,LstDeCde lstDeCde) {
    }

    /**
     * Modifier une plage horaire n'est pas réalisable par défaut
     * @param pointLivraison
     * @param tournee
     * @param debutPlage
     * @param finPlage
     * @param lstDeCde
     */
    public void cdeModifierPlageHoraire(PointLivraison pointLivraison,Tournee tournee, Double debutPlage, Double finPlage,LstDeCde lstDeCde) {
    }

    /**
     * Le clic droit ne provoque rien par défaut
     * @param noeud
     * @param controleur
     * @return
     */
    public boolean rightClickedOnPoint(Noeud noeud,Controleur controleur){return false;}
}
