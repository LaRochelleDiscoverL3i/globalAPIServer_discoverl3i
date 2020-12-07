package TableToJson.Reponse;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdbc.tableClass.reponse.Reponse;
import java.util.List;
/**
 * Class    : ReponseToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON
 */
public class ReponseToJson implements ToJsonInterface {
    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'une Reponse en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == Reponse.class){
            Reponse reponse = (Reponse) obj;

            result.put("idreponse", reponse.getIdreponse());
            result.put("description_reponse", reponse.getDescription_reponse());
            result.put("idquestion", reponse.getIdquestion());
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
     * Def      : Function permettant la conversion d'une liste de Reponse en List JSON
     */
    @Override
    public JsonArray ArrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Reponse.class){
                results.add(toJson(obj));
            }
            else{
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
