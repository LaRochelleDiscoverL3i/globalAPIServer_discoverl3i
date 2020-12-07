package TableToJson.Question_Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdbc.tableClass.question_joueur.QuestionJoueur;
import java.util.List;

/**
 * Class    : QuestionJoueurToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON
 */
public class QuestionJoueurToJson implements ToJsonInterface {
    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'une QuestionJoueur en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == QuestionJoueur.class){
            QuestionJoueur questionJoueur = (QuestionJoueur) obj;

            result.put("idquestion", questionJoueur.getIdquestion());
            result.put("idjoueur", questionJoueur.getIdjoueur());
            result.put("nbre_tentative", questionJoueur.getNbre_tentative());
            result.put("booleen", questionJoueur.getBooleen());
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
     * Def      : Function permettant la conversion d'une liste de QuestionJoueur en List JSON
     */
    @Override
    public JsonArray ArrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == QuestionJoueur.class){
                results.add(toJson(obj));
            }
            else{
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
