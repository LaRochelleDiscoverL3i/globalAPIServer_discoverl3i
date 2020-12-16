package TableToJson.Question_Joueur;

import TableToJson.Interfaces.ToJsonInterface;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import jdbc.tableClass.question_joueur.QuestionJoueur;
import java.util.List;

/**
 * Class    : QuestionJoueurToJson
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour faire la conversion en JSON d'une Question_Joueur
 */
public class QuestionJoueurToJson implements ToJsonInterface {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionJoueurToJson.class);

    /**
     * Method   : toJson
     * Params   : obj(Object)
     * Return   : JsonObject
     * Def      : Function permettant la conversion d'une QuestionJoueur en JSON
     */
    @Override
    public JsonObject toJson(Object obj) throws Exception {
        JsonObject result = new JsonObject();

        if(obj.getClass() == QuestionJoueur.class) {
            QuestionJoueur questionJoueur = (QuestionJoueur) obj;

            result.put("idquestion", questionJoueur.getIdquestion());
            result.put("idjoueur", questionJoueur.getIdjoueur());
            if (questionJoueur.getNbre_tentative() == null) {
                result.putNull("getNbre_tentative");
            }else{
                result.put("nbre_tentative", questionJoueur.getNbre_tentative());
            }
            if (questionJoueur.getNbre_tentative() == null) {
                result.putNull("getNbre_tentative");
            }else {
                result.put("getNbre_tentative", questionJoueur.getBooleen());
            }

            LOGGER.info("[QuestionJoueurToJson] Conversion QuestionJoueur to Json Object - (IdQuestion, IdJoueur) : ("+questionJoueur.getIdquestion()+", "+questionJoueur.getIdjoueur()+")");
        }
        else{
            LOGGER.warn("[QuestionJoueurToJson] Erreur : Le parametre de la fonction toJson n'est pas le bon - toJson(QuestionJoueur)");
            throw new Exception("Not good class object !");
        }
        return result;
    }

    /**
     * Method   : arrayToJson
     * Params   : obj_list(List<Object>)
     * Return   : JsonArray
     * Def      : Function permettant la conversion d'une liste de QuestionJoueur en tableau JSON
     */
    @Override
    public JsonArray arrayToJson(List<Object> obj_list) throws Exception {
        JsonArray results = new JsonArray();

        for(Object obj : obj_list){
            if(obj.getClass() == QuestionJoueur.class){
                results.add(toJson(obj));
            }
            else{
                LOGGER.warn("[QuestionJoueurToJson] Erreur : Le parametre de la fonction ArrayToJson n'est pas le bon - arrayToJson(List<QuestionJoueur>)");
                throw new Exception("Not good class object !");
            }
        }

        return results;
    }
}
