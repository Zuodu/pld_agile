package ChargeurXML;

import Modele.PointLivraison;

import java.util.ArrayList;
import java.util.List;

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

    public void parse (String filePath) {

    }
}
