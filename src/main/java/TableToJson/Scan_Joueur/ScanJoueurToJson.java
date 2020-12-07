package TableToJson.Scan_Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdbc.tableClass.scan_joueur.ScanJoueur;
import java.util.List;

/**
 * Class    : ScanJoueurToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON
 */
public class ScanJoueurToJson implements ToJsonInterface {
    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'un ScanJoueur en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == ScanJoueur.class){
            ScanJoueur scanJoueur = (ScanJoueur) obj;

            result.put("idjoueur", scanJoueur.getIdjoueur());
            result.put("idreponse", scanJoueur.getIdreponse());
            result.put("idquestion", scanJoueur.getIdquestion());
            result.put("booleen_question", scanJoueur.getBooleen_question());
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
     * Def      : Function permettant la conversion d'une liste de ScanJoueur en List JSON
     */
    @Override
    public JsonArray ArrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == ScanJoueur.class){
                results.add(toJson(obj));
            }
            else{
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}