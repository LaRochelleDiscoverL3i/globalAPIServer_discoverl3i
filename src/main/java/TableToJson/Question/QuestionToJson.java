package TableToJson.Question;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
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
     * Def      : Function permettant la conversion d'une liste de Question en List JSON
     */
    @Override
    public JsonArray ArrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == Question.class){
                results.add(toJson(obj));
            }
            else{
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
