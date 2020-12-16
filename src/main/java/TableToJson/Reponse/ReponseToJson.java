package TableToJson.Reponse;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import jdbc.tableClass.reponse.Reponse;
import java.util.List;
/**
 * Class    : ReponseToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON d'une Reponse
 */
public class ReponseToJson implements ToJsonInterface {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReponseToJson.class);

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
            if(reponse.getDescription_reponse() == null){
                result.putNull("description_reponse");
            }
            else {
                result.put("description_reponse", reponse.getDescription_reponse());
            }
            result.put("idquestion", reponse.getIdquestion());

            LOGGER.info("[ReponseToJson] Conversion Reponse to Json Object - IdReponse : "+reponse.getIdreponse());
        }
        else{
            LOGGER.warn("[ReponseToJson] Erreur : Le parametre de la fonction toJson n'est pas le bon - toJson(Reponse)");
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : arrayToJson
     * Params   : obj_list(List<Object>)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de Reponse en tableau JSON
     */
    @Override
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Reponse.class){
                results.add(toJson(obj));
            }
            else{
                LOGGER.warn("[ReponseToJson] Erreur : Le parametre de la fonction ArrayToJson n'est pas le bon - arrayToJson(List<Reponse>)");
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
