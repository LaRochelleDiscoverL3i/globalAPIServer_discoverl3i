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
    public JsonObject toJson(Object obj) throws Exception;
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception;
}
