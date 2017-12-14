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
        this.itinerairesMap = new HashMap<>();
        this.listePointLivraisons = new ArrayList<>();
    }

    /**
     * Constructeur par copie
     *
     * @param uneTournee
     */
    public Tournee(Tournee uneTournee) {
        this(new PointLivraison(uneTournee.getEntrepot()), uneTournee.getHeureDeDepart());
        for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> entry : uneTournee.getItinerairesMap().entrySet()) {
            this.addItineraire(new AbstractMap.SimpleEntry<>(new PointLivraison(entry.getKey().getKey()), new PointLivraison(entry.getKey().getValue())), new Itineraire(entry.getValue()));
        }
        for (PointLivraison pointLivraison : uneTournee.getListePointLivraisons()) {
            this.addPointLivraisons(new PointLivraison(pointLivraison));
        }

    }

    /**
     * Méthode réinitialisant la tournée
     */
    public void reinitialise() {
        this.itinerairesMap = new HashMap<>();
        this.listePointLivraisons = new ArrayList<>();
        setChanged();
        notifyObservers();
    }

    /**
     * Méthode clonant une tournée dans une autre (avec copie en profondeur)
     *
     * @param tournee
     * @param newTournee
     */
    public void clone(Tournee tournee, Tournee newTournee) {
        newTournee.reinitialise();
        HashMap<Long, PointLivraison> pointLivraisonHashMap = new HashMap<>();
        newTournee.setCharge(tournee.isCharge());
        newTournee.setHeureDeDepart(tournee.getHeureDeDepart());
        newTournee.setEntrepot(new PointLivraison(tournee.getEntrepot()));

        newTournee.addPointLivraisons(newTournee.getEntrepot());
        pointLivraisonHashMap.put(newTournee.getEntrepot().getId(), newTournee.getEntrepot());
        for (int i = 1; i < tournee.getListePointLivraisons().size() - 1; i++) {
            newTournee.addPointLivraisons(new PointLivraison(tournee.getListePointLivraisons().get(i)));
            pointLivraisonHashMap.put(newTournee.getListePointLivraisons().get(i).getId(), newTournee.getListePointLivraisons().get(i));
        }
        newTournee.addPointLivraisons(newTournee.getEntrepot());

        for (Map.Entry<Map.Entry<PointLivraison, PointLivraison>, Itineraire> entry : tournee.getItinerairesMap().entrySet()) {
            PointLivraison pointDepart = entry.getKey().getKey();
            PointLivraison pointArrivee = entry.getKey().getValue();
            newTournee.addItineraire(new AbstractMap.SimpleEntry<>(pointLivraisonHashMap.get(pointDepart.getId()), pointLivraisonHashMap.get(pointArrivee.getId())), new Itineraire(entry.getValue()));
        }
        newTournee.setChanged();
        newTournee.notifyObservers();
    }

    /**
     * Constructeur par défaut
     */
    public Tournee() {
        listePointLivraisons = new ArrayList<>();
        this.itinerairesMap = new HashMap<>();
    }

    /**
     * Méthode permettant d'ajouter un itinéraire
     *
     * @param entry
     * @param itineraire
     */
    public void addItineraire(Map.Entry<PointLivraison, PointLivraison> entry, Itineraire itineraire) {
        itinerairesMap.put(entry, itineraire);
    }

    /**
     * Get
     *
     * @return itinerairesMap
     */
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

    /**
     * Set
     *
     * @param listePointLivraisons
     */
    public void setListePointLivraisons(List<PointLivraison> listePointLivraisons) {
        this.listePointLivraisons = listePointLivraisons;
    }

    /**
     * Get
     *
     * @return isCharge
     */
    public boolean isCharge() {
        return isCharge;
    }

    /**
     * Set
     *
     * @param charge
     */
    public void setCharge(boolean charge) {
        isCharge = charge;
        setChanged();
        notifyObservers();
    }

    /**
     * Méthode permettant l'ajout d'un point de livraison à une tournée
     *
     * @param noeud
     * @param duree
     * @param finPlage
     * @param debutPlage
     * @return true si réussi, false si échec
     */
    public boolean ajouterPoint(Noeud noeud, double duree, Double finPlage, Double debutPlage) {
        PointLivraison pointAAjouter = null;
        if (finPlage != null && debutPlage != null) {
            pointAAjouter = new PointLivraison(noeud.getId(), noeud.getX(), noeud.getY(), duree, debutPlage, finPlage);
        } else {
            pointAAjouter = new PointLivraison(noeud.getId(), noeud.getX(), noeud.getY(), duree);
        }

        if (pointAAjouter != null) {
            pointAAjouter.setNeighbors(noeud.getNeighbors());
            HashMap<PointLivraison, Itineraire> itineraires = new HashMap<>();
            double tempsMin = Double.MAX_VALUE;
            PointLivraison pointPlusProche = null;
            for (int i = 1; i < listePointLivraisons.size(); i++) {
                PointLivraison pointLivraison = listePointLivraisons.get(i);
                Dijkstra dijkstra = new Dijkstra();
                dijkstra.chercheDistanceMin(pointAAjouter, pointLivraison);
                Itineraire result = dijkstra.getMeilleurItineraire();
                itineraires.put(pointLivraison, result);
                if (result.getTemps() < tempsMin) {
                    tempsMin = result.getTemps();
                    pointPlusProche = pointLivraison;
                }
            }

            int indexPointPlusProche = listePointLivraisons.indexOf(pointPlusProche);
            PointLivraison pointPro = listePointLivraisons.get(indexPointPlusProche + 1);
            PointLivraison pointPre = null;
            if (pointPlusProche == entrepot) {
                pointPre = listePointLivraisons.get(listePointLivraisons.size() - 2);
            } else {
                pointPre = listePointLivraisons.get(indexPointPlusProche - 1);
            }

            if (itineraires.get(pointPre).getTemps() < itineraires.get(pointPro).getTemps()) {
                if (insererPoint(pointAAjouter, pointPre, pointPlusProche)) {
                    setChanged();
                    notifyObservers();
                    return true;
                } else {
                    if (insererPoint(pointAAjouter, pointPlusProche, pointPro)) {
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
            } else {
                if (insererPoint(pointAAjouter, pointPlusProche, pointPro)) {
                    setChanged();
                    notifyObservers();
                    return true;
                } else {
                    if (insererPoint(pointAAjouter, pointPre, pointPlusProche)) {
                        listePointLivraisons.add(listePointLivraisons.indexOf(pointPlusProche), pointAAjouter);
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
            }
        }
        setChanged();
        notifyObservers();
        return false;
    }

    /**
     * Méthode permettant de //TODO
     *
     * @param pointLivraisonAInserer
     * @param pointLivraison1
     * @param pointLivraison2
     * @return true si réussi, false si échec
     */
    private boolean insererPoint(PointLivraison pointLivraisonAInserer, PointLivraison pointLivraison1, PointLivraison pointLivraison2) {
        Dijkstra dijkstra1 = new Dijkstra();
        dijkstra1.chercheDistanceMin(pointLivraison1, pointLivraisonAInserer);
        Itineraire itineraire1 = dijkstra1.getMeilleurItineraire();

        Dijkstra dijkstra2 = new Dijkstra();
        dijkstra2.chercheDistanceMin(pointLivraisonAInserer, pointLivraison2);
        Itineraire itineraire2 = dijkstra2.getMeilleurItineraire();

        int positionAInserer = listePointLivraisons.indexOf(pointLivraison2);

        double arrivee = pointLivraison1.getHeureDepart() + itineraire1.getTemps();
        double depart = arrivee + pointLivraisonAInserer.getDuree();
        if (pointLivraisonAInserer.getDebutPlage() != null && arrivee < pointLivraisonAInserer.getDebutPlage()) {
            depart = pointLivraisonAInserer.getDebutPlage() + pointLivraisonAInserer.getDuree();
        }
        if (pointLivraisonAInserer.getFinPlage() != null && depart > pointLivraisonAInserer.getFinPlage()) {
            return false;
        }

        if (positionAInserer == 0) {
            listePointLivraisons.add(listePointLivraisons.size() - 1, pointLivraisonAInserer);
            itinerairesMap.put(new AbstractMap.SimpleEntry<>(pointLivraison1, pointLivraisonAInserer), itineraire1);
            itinerairesMap.put(new AbstractMap.SimpleEntry<>(pointLivraisonAInserer, pointLivraison2), itineraire2);
            itinerairesMap.remove(new AbstractMap.SimpleEntry<>(pointLivraison1, pointLivraison2));
            pointLivraisonAInserer.setHeureDepart(depart);
            pointLivraisonAInserer.setHeureArrivee(arrivee);
            entrepot.setHeureArrivee(depart + itineraire2.getTemps());
            return true;
        }


        if (retardeHoraire(positionAInserer, depart + itineraire2.getTemps() - pointLivraison2.getHeureArrivee())) {
            listePointLivraisons.add(positionAInserer, pointLivraisonAInserer);
            itinerairesMap.put(new AbstractMap.SimpleEntry<>(pointLivraison1, pointLivraisonAInserer), itineraire1);
            itinerairesMap.put(new AbstractMap.SimpleEntry<>(pointLivraisonAInserer, pointLivraison2), itineraire2);
            itinerairesMap.remove(new AbstractMap.SimpleEntry<>(pointLivraison1, pointLivraison2));
            pointLivraisonAInserer.setHeureDepart(depart);
            pointLivraisonAInserer.setHeureArrivee(arrivee);
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant la suppression d'un point
     *
     * @param id
     * @return true si réussi, false si échec
     */
    public boolean supprimerPoint(Long id) {
        PointLivraison pointASupprimer = null;
        for (PointLivraison pointLivraison : listePointLivraisons) {
            if (pointLivraison.getId().equals(id)) {
                pointASupprimer = pointLivraison;
            }
        }
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
            itinerairesMap.remove(new AbstractMap.SimpleEntry<>(pPrecedant, pointASupprimer));
            itinerairesMap.remove(new AbstractMap.SimpleEntry<>(pointASupprimer, pProchain));
            itinerairesMap.put(new AbstractMap.SimpleEntry<>(pPrecedant, pProchain), result);

            setChanged();
            notifyObservers();
            return true;
        }
        setChanged();
        notifyObservers();
        return false;
    }

    /**
     * Méthode permettant de mettre à jour la durée d'un point de livraison, non utilisée
     *
     * @param id
     * @param duree
     * @return true si réussi, false si échec
     */
    public boolean updateDuree(Long id, double duree) {
        double heureArrivee;
        double heureDepart;

        PointLivraison pointAModifier = null;
        for (PointLivraison pointLivraison : listePointLivraisons) {
            if (pointLivraison.getId().equals(id)) {
                pointAModifier = pointLivraison;
            }
        }
        heureArrivee = pointAModifier.getHeureArrivee();
        heureDepart = heureArrivee + duree;

        if (pointAModifier.getFinPlage() != null && heureDepart > pointAModifier.getFinPlage()) {
            setChanged();
            notifyObservers();
            return false;
        } else if (heureDepart < pointAModifier.getHeureDepart()) {
            if (avanceHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, pointAModifier.getHeureDepart() - heureDepart)) {
                pointAModifier.setDuree(duree);
                pointAModifier.setHeureDepart(heureDepart);
            }

            setChanged();
            notifyObservers();
            return true;
        } else if (retardeHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, heureDepart - pointAModifier.getHeureDepart())) {
            pointAModifier.setDuree(duree);
            pointAModifier.setHeureDepart(heureDepart);

            setChanged();
            notifyObservers();
            return true;
        }
        setChanged();
        notifyObservers();
        return false;

    }

    /**
     * Permet de mettre à jour la plage horaire d'un point de livraison
     *
     * @param id
     * @param plageDebut
     * @param plageFin
     * @return true si réussi, false si échec
     */
    public boolean updateHoraire(Long id, Double plageDebut, Double plageFin) {
        double heureArrivee;
        double heureDepart;

        PointLivraison pointAModifier = null;

        for (PointLivraison pointLivraison : listePointLivraisons) {
            if (pointLivraison.getId().equals(id)) {
                pointAModifier = pointLivraison;
            }
        }

        heureArrivee = pointAModifier.getHeureArrivee();
        heureDepart = heureArrivee + pointAModifier.getDuree();

        if (plageDebut == null && plageFin == null) {
            if (heureDepart < pointAModifier.getHeureDepart()) {
                avanceHoraire(listePointLivraisons.indexOf(pointAModifier) + 1, pointAModifier.getHeureDepart() - heureDepart);
                pointAModifier.setHeureDepart(heureDepart);
            }

            pointAModifier.setDebutPlage(null);
            pointAModifier.setFinPlage(null);
            setChanged();
            notifyObservers();
            return true;
        }


        if (heureArrivee < plageDebut) {
            heureDepart = plageDebut + pointAModifier.getDuree();
        }
        if (heureDepart > plageFin) {
            setChanged();
            notifyObservers();
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
        setChanged();
        notifyObservers();
        return false;

    }

    /**
     * Permet d'avancer un horaire lors d'une modification d'horaire
     *
     * @param index
     * @param temps
     * @return true si réussi, false si échec
     */
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

    /**
     * Permet de retarder un horaire lors d'une modification d'horaire
     *
     * @param index
     * @param temps
     * @return true si réussi, false si échec
     */
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
        } else if (nextPoint.getFinPlage() != null && heureDepart > nextPoint.getFinPlage()) {
            return false;
        } else if (retardeHoraire(index + 1, heureDepart - nextPoint.getHeureDepart())) {
            nextPoint.setHeureArrivee(heureArrivee);
            nextPoint.setHeureDepart(heureDepart);
            return true;
        }

        return false;
    }


}
