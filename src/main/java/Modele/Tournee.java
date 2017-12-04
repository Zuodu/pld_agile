package Modele;

import Algo.Dijkstra;

import java.util.*;

/**
 * @author H4401
 *         Classe représentant une Tournée, extends Observable, contient des Set, des get, des add
 *         et une méthode indiquant à l'observeur la fin d'ajouts/mis à jour de points de livraison à la tournée.
 */
public class Tournee extends Observable {
    private HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap;
    private PointLivraison entrepot;
    private List<PointLivraison> listePointLivraisons;
    private double heureDeDepart;
    private boolean isCharge;

    /**
     * Constructeur
     *
     * @param entrepot
     * @param heureDeDepart
     */
    public Tournee(PointLivraison entrepot, double heureDeDepart) {
        this.isCharge = false;
        this.entrepot = entrepot;
        this.heureDeDepart = heureDeDepart;
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
        this.listePointLivraisons = new ArrayList<PointLivraison>();
    }

    public Tournee(Tournee uneTournee) {
        this(new PointLivraison(uneTournee.getEntrepot()), uneTournee.getHeureDeDepart());
        for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> entry : uneTournee.getItinerairesMap().entrySet()) {
            this.addItineraire(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(new PointLivraison(entry.getKey().getKey()),new PointLivraison(entry.getKey().getValue())),new Itineraire(entry.getValue()));
        }
        for(PointLivraison pointLivraison:uneTournee.getListePointLivraisons()){
            this.addPointLivraisons(new PointLivraison(pointLivraison));
        }

    }

    public void clone(Tournee tournee, Tournee newTournee) {
        newTournee.setCharge(tournee.isCharge());
        newTournee.setHeureDeDepart(tournee.getHeureDeDepart());
        newTournee.setEntrepot(new PointLivraison(tournee.getEntrepot()));
        for (PointLivraison pointLivraison : tournee.getListePointLivraisons()) {
            newTournee.addPointLivraisons(new PointLivraison(pointLivraison));
        }
        for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> entry : tournee.getItinerairesMap().entrySet()) {
            newTournee.addItineraire(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(new PointLivraison(entry.getKey().getKey()),new PointLivraison(entry.getKey().getValue())),new Itineraire(entry.getValue()));
        }
    }

    public Tournee() {
        listePointLivraisons = new ArrayList<PointLivraison>();
        this.itinerairesMap = new HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire>();
    }



    public void setItinerairesMap(HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> itinerairesMap) {
        this.itinerairesMap = itinerairesMap;
    }

    public void addItineraire(Map.Entry<PointLivraison, PointLivraison> entry, Itineraire itineraire) {
        itinerairesMap.put(entry, itineraire);
    }

    public HashMap<Map.Entry<PointLivraison, PointLivraison>, Itineraire> getItinerairesMap() {
        return itinerairesMap;
    }

    /**
     * Ajout d'un point de livraison
     *
     * @param pointLivraison
     */
    public void addPointLivraisons(PointLivraison pointLivraison) {
        listePointLivraisons.add(pointLivraison);
    }


    /**
     * Set
     *
     * @param heureDeDepart
     */
    public void setHeureDeDepart(double heureDeDepart) {
        this.heureDeDepart = heureDeDepart;
    }


    /**
     * Set
     *
     * @param entrepot
     */
    public void setEntrepot(PointLivraison entrepot) {
        this.entrepot = entrepot;
    }


    /**
     * Set
     *
     * @return
     */
    public PointLivraison getEntrepot() {
        return entrepot;
    }

    /**
     * Get
     *
     * @return listePointLivraisons
     */
    public List<PointLivraison> getListePointLivraisons() {
        return listePointLivraisons;
    }

    /**
     * Get
     *
     * @return heureDeDepart
     */
    public double getHeureDeDepart() {
        return heureDeDepart;
    }


    public void setListePointLivraisons(List<PointLivraison> listePointLivraisons) {
        this.listePointLivraisons = listePointLivraisons;
    }


    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
        setChanged();
        notifyObservers();
    }

    public boolean supprimerPoint(PointLivraison pointASupprimer) {
        int indexASupprimer = listePointLivraisons.indexOf(pointASupprimer);
        PointLivraison pPrecedant = listePointLivraisons.get(indexASupprimer - 1);
        PointLivraison pProchain = listePointLivraisons.get(indexASupprimer + 1);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.chercheDistanceMin(pPrecedant, pProchain);
        Itineraire result = dijkstra.getMeilleurItineraire();

        double departPointPrecedent = pPrecedant.getHeureDepart();
        double arriveePointProchain = departPointPrecedent + result.getTemps();

        boolean departNonModifiee = pProchain.getHeureArrivee().equals(arriveePointProchain);
        boolean departAvancee = arriveePointProchain < pProchain.getHeureArrivee() && avanceHoraire(indexASupprimer + 1, pProchain.getHeureArrivee() - arriveePointProchain);
        boolean departRetardee = arriveePointProchain > pProchain.getHeureArrivee() && retardeHoraire(indexASupprimer + 1, arriveePointProchain - pProchain.getHeureArrivee());
        if (departNonModifiee || departAvancee || departRetardee) {
            listePointLivraisons.remove(pointASupprimer);
            itinerairesMap.remove(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pPrecedant, pointASupprimer));
            itinerairesMap.remove(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pointASupprimer, pProchain));
            itinerairesMap.put(new AbstractMap.SimpleEntry<PointLivraison, PointLivraison>(pPrecedant, pProchain), result);

            setChanged();
            notifyObservers();
            return true;
        }
        setChanged();
        notifyObservers();
        return false;
    }


    public boolean updateHoraire(PointLivraison pointAModifier, Double plageDebut, Double plageFin) {
        double heureArrivee;
        double heureDepart;

        heureArrivee = pointAModifier.getHeureArrivee();
        heureDepart = heureArrivee + pointAModifier.getDuree();
        if (heureArrivee < plageDebut) {
            heureDepart = plageDebut + pointAModifier.getDuree();
        }
        if (heureDepart > plageFin) {
            return false;
        } else if (pointAModifier.getHeureDepart().equals(heureDepart)) {
            pointAModifier.setDebutPlage(plageDebut);
            pointAModifier.setFinPlage(plageFin);

            setChanged();
            notifyObservers();
            return true;
        } else if (heureDepart < pointAModifier.getHeureDepart()) {
            if (avanceHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, pointAModifier.getHeureDepart() - heureDepart)) {
                pointAModifier.setDebutPlage(plageDebut);
                pointAModifier.setFinPlage(plageFin);
                pointAModifier.setHeureDepart(heureDepart);
            }

            setChanged();
            notifyObservers();
            return true;
        } else if (retardeHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, heureDepart - pointAModifier.getHeureDepart())) {
            pointAModifier.setDebutPlage(plageDebut);
            pointAModifier.setFinPlage(plageFin);
            pointAModifier.setHeureDepart(heureDepart);

            setChanged();
            notifyObservers();
            return true;
        }
        return false;

    }

    private boolean avanceHoraire(int index, double temps) {
        PointLivraison nextPoint = listePointLivraisons.get(index);
        double heureArrivee;
        double heureDepart;

        heureArrivee = nextPoint.getHeureArrivee() - temps;
        heureDepart = heureArrivee + nextPoint.getDuree();
        if (nextPoint.getDebutPlage() != null) {
            if (heureArrivee < nextPoint.getDebutPlage()) {
                heureDepart = nextPoint.getDebutPlage() + nextPoint.getDuree();
            }
        }
        if (nextPoint.getHeureDepart().equals(heureDepart) || index == listePointLivraisons.size() - 1) {
            nextPoint.setHeureArrivee(heureArrivee);
            return true;
        } else if (avanceHoraire(index + 1, nextPoint.getHeureDepart() - heureDepart)) {
            nextPoint.setHeureArrivee(heureArrivee);
            nextPoint.setHeureDepart(heureDepart);
            return true;
        }
        return false;
    }

    private boolean retardeHoraire(int index, double temps) {
        PointLivraison nextPoint = listePointLivraisons.get(index);
        double heureArrivee;
        double heureDepart;
        heureArrivee = nextPoint.getHeureArrivee() + temps;
        heureDepart = heureArrivee + nextPoint.getDuree();
        if (nextPoint.getDebutPlage() != null) {
            if (heureArrivee < nextPoint.getDebutPlage()) {
                heureDepart = nextPoint.getDebutPlage() + nextPoint.getDuree();
            }
        }
        if (nextPoint.getHeureDepart().equals(heureDepart) || index == listePointLivraisons.size() - 1) {
            nextPoint.setHeureArrivee(heureArrivee);
            return true;
        } else if (nextPoint.getFinPlage() != null) {
            if (heureDepart > nextPoint.getFinPlage()) {
                return false;
            }
        }
        if (retardeHoraire(index + 1, heureDepart - nextPoint.getHeureDepart())) {
            nextPoint.setHeureArrivee(heureArrivee);
            nextPoint.setHeureDepart(heureDepart);
            return true;
        }

        return false;
    }


    /**
     * Signale la fin des ajouts de points de livraisons à la tournée
     */

    public void SignalerFinDajoutPointsLivraisons() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "itinerairesMap=" + itinerairesMap +
                ", entrepot=" + entrepot +
                ", listePointLivraisons=" + listePointLivraisons +
                ", heureDeDepart=" + heureDeDepart +
                '}';
    }
}
