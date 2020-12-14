package TableToJson.Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(JoueurToJson.class);

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
            if(joueur.getScore() == null){
                result.putNull("score");
            }else{
                result.put("score", joueur.getScore());
            }
            if(joueur.getTemps_test() == null){
                result.putNull("temps_test");
            }else {
                result.put("temps_test", joueur.getTemps_test().toString());
            }
            if(joueur.getLevel_game() == null){
                result.putNull("level_game");
            }else {
                result.put("level_game", joueur.getLevel_game());
            }

            LOGGER.info("[JoueurToJson] Conversion Joueur to Json Object - IdJoueur : "+joueur.getIdjoueur());
        }
        else{
            LOGGER.warn("[JoueurToJson] Erreur : Le parametre de la fonction toJson n'est pas le bon - toJson(Joueur)");
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : arrayToJson
     * Params   : obj_list(List<Object>)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de Joueur en List JSON
     */
    @Override
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Joueur.class){
                results.add(toJson(obj));
            }
            else{
                LOGGER.warn("[JoueurToJson] Erreur : Le parametre de la fonction ArrayToJson n'est pas le bon - arrayToJson(List<Joueur>)");
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
