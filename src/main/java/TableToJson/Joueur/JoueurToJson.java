package TableToJson.Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdbc.tableClass.joueur.Joueur;
import java.util.List;

/**
 * Class    : JoueurToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON
 */
public class JoueurToJson implements ToJsonInterface {
    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'un Joueur en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == Joueur.class){
            Joueur joueur = (Joueur) obj;

            result.put("idjoueur", joueur.getIdjoueur());
            result.put("score", joueur.getScore());
            result.put("temps_test", joueur.getTemps_test());
            result.put("level_game", joueur.getLevel_game());
        }
        else{
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : ArrayToJson
     * Params   : obj(Object)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de Joueur en List JSON
     */
    @Override
    public JsonArray ArrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Joueur.class){
                results.add(toJson(obj));
            }
            else{
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
