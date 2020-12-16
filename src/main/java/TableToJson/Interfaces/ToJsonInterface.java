package TableToJson.Interfaces;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.List;

/**
 * Class    : ToJsonInterface
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Interface permettant de donner les fonctions de bases pour la conversion en JSON
 */
public interface ToJsonInterface {
    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'un objet en JSON
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public JsonObject toJson(Object obj) throws Exception;

    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'une liste objet en tableau JSON
     *
     * @param obj_list
     * @return
     * @throws Exception
     */
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception;
}
