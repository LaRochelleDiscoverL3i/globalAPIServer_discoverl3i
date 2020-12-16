package TableToJson.Scan_Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import jdbc.tableClass.scan_joueur.ScanJoueur;
import java.util.List;

/**
 * Class    : ScanJoueurToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON de ScanJoueur
 */
public class ScanJoueurToJson implements ToJsonInterface {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanJoueurToJson.class);

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

            LOGGER.info("[ScanJoueurToJson] Conversion ScanJoueur to Json Object - (IdJoueur, IdReponse, IdQuestion) : ("+scanJoueur.getIdjoueur()+", "+scanJoueur.getIdreponse()+", "+scanJoueur.getIdquestion()+")");
        }
        else{
            LOGGER.warn("[ScanJoueurToJson] Erreur : Le parametre de la fonction toJson n'est pas le bon - toJson(ScanJoueur)");
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : arrayToJson
     * Params   : obj_list(List<Object>)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de ScanJoueur en tableau JSON
     */
    @Override
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == ScanJoueur.class){
                results.add(toJson(obj));
            }
            else{
                LOGGER.warn("[ScanJoueurToJson] Erreur : Le parametre de la fonction ArrayToJson n'est pas le bon - arrayToJson(List<ScanJoueur>)");
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}