package TableAPI.Question;

import TableToJson.Question.QuestionToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.question.Question;
import jdbc.tableClass.question.QuestionJDBC;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : QuestionHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe gestion des retours des requêtes REST d'une Question
 */
public class QuestionHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionHandler.class);

    /**
     * Variables
     */
    private static QuestionJDBC questionJDBC = new QuestionJDBC();
    private static QuestionToJson qtj = new QuestionToJson();

    /**
     * Method   : getAllItems
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête GET
     *
     * @param routingContext
     */
    public static void getAllItems(RoutingContext routingContext) {
        try {
            List<Question> allQuestion = questionJDBC.getAllQuestion();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allQuestion);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(qtj.arrayToJson(obj_list)));
        }catch (Exception e){
            LOGGER.warn("[QuestionHandler] Exception error - getAllItems : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }

    /**
     * Method   : getItemById
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête GET by ID
     *
     * @param routingContext
     */
    public static void getItemById(RoutingContext routingContext) {
        try {
            Question question = questionJDBC.getQuestionById(Integer.valueOf(routingContext.request().getParam("idquestion")));

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(qtj.toJson(question)));
        }catch (Exception e){
            LOGGER.warn("[JoueurHandler] Exception error - getAllItems : "+e.getMessage());
        }
    }

    /**
     * Method   : addItem
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête POST
     *
     * @param routingContext
     */
    public static void addItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    !routingContext.request().params().contains("indice") ? null : routingContext.request().getParam("indice"),
                    !routingContext.request().params().contains("positionreponse") ? null : Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    !routingContext.request().params().contains("description_question") ? null : routingContext.request().getParam("description_question"),
                    !routingContext.request().params().contains("level_game") ? null : Integer.parseInt(routingContext.request().getParam("level_game")),
                    !routingContext.request().params().contains("categorie_question") ? null : Question.Categorie_question.valueOf(routingContext.request().getParam("categorie_question")),
                    Integer.parseInt(routingContext.request().getParam("idreponse"))
            );

            Boolean result = questionJDBC.insertQuestion(question);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qtj.toJson(question)));
            }
            else{
                LOGGER.warn("[QuestionHandler] Error to insert new question");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionHandler] Exception error - addItem : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }

    /**
     * Method   : updateItem
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête PUT
     *
     * @param routingContext
     */
    public static void updateItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    routingContext.request().getParam("indice"),
                    Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    routingContext.request().getParam("description_question"),
                    Integer.parseInt(routingContext.request().getParam("level_game")),
                    Question.Categorie_question.valueOf(routingContext.request().getParam("categorie_question")),
                    Integer.parseInt(routingContext.request().getParam("idreponse"))
            );

            Boolean result = questionJDBC.updateQuestion(question);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qtj.toJson(question)));
            }
            else{
                LOGGER.warn("[QuestionHandler] Error to update question");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionHandler] Exception error - updateItem : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }

    /**
     * Method   : deleteItem
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête DEL
     *
     * @param routingContext
     */
    public static void deleteItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    !routingContext.request().params().contains("indice") ? null : routingContext.request().getParam("indice"),
                    !routingContext.request().params().contains("positionreponse") ? null : Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    !routingContext.request().params().contains("description_question") ? null : routingContext.request().getParam("description_question"),
                    !routingContext.request().params().contains("level_game") ? null : Integer.parseInt(routingContext.request().getParam("level_game")),
                    !routingContext.request().params().contains("categorie_question") ? null : Question.Categorie_question.valueOf(routingContext.request().getParam("categorie_question")),
                    !routingContext.request().params().contains("idreponse") ? null : Integer.parseInt(routingContext.request().getParam("idreponse"))
            );

            Boolean result = questionJDBC.deleteQuestion(question);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qtj.toJson(question)));
            }
            else{
                LOGGER.warn("[QuestionHandler] Error to delete question");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionHandler] Exception error - deleteItem : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }
}
