package TableToJson.Question;

import TableToJson.Interfaces.ToJsonInterface;
import TableToJson.Question_Joueur.QuestionJoueurToJson;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import jdbc.tableClass.question.Question;
import java.util.List;

/**
 * Class    : QuestionToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON
 */
public class QuestionToJson implements ToJsonInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionToJson.class);

    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'une Question en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == Question.class){
            Question question = (Question) obj;

            result.put("idquestion", question.getIdquestion());
            result.put("indice", question.getIndice());
            result.put("positionreponse", question.getPositionreponse());
            result.put("description_question", question.getDescription_question());
            result.put("level_game", question.getLevel_game());
            result.put("idreponse", question.getIdreponse());

            LOGGER.info("[QuestionToJson] Conversion Question to Json Object - IdQuestion : "+question.getIdquestion());
        }
        else{
            LOGGER.warn("[QuestionToJson] Erreur : Le parametre de la fonction toJson n'est pas le bon - toJson(Question)");
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : arrayToJson
     * Params   : obj_list(List<Object>)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de Question en List JSON
     */
    @Override
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Question.class){
                results.add(toJson(obj));
            }
            else{
                LOGGER.warn("[QuestionJoueurToJson] Erreur : Le parametre de la fonction ArrayToJson n'est pas le bon - arrayToJson(List<Question>)");
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
