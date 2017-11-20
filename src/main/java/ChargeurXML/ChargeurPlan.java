package ChargeurXML;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

import java.util.ArrayList;
import java.util.List;

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

    public void parse(String filePath) {

    }

    public Plan getPlan() {
        return plan;
    }
}
