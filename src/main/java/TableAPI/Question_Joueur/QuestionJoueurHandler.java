package TableAPI.Question_Joueur;

import TableToJson.Question_Joueur.QuestionJoueurToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.question.Question;
import jdbc.tableClass.question_joueur.QuestionJoueur;
import jdbc.tableClass.question_joueur.QuestionJoueurJDBC;

import java.util.ArrayList;
import java.util.List;

public class QuestionJoueurHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionJoueurHandler.class);
    private static QuestionJoueurJDBC questionJoueurJDBC = new QuestionJoueurJDBC();
    private static QuestionJoueurToJson qjtj = new QuestionJoueurToJson();

    public static void getAllItems(RoutingContext routingContext) {
        try {
            List<QuestionJoueur> allQuestionJoueur = questionJoueurJDBC.getAllQuestionJoueur();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allQuestionJoueur);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(qjtj.arrayToJson(obj_list)));
        }catch (Exception e){
            LOGGER.warn("[QuestionJoueurHandler] Exception error - getAllItems : "+e.getMessage());
        }
    }
    public static void addItem(RoutingContext routingContext) {
        try {
            QuestionJoueur questionJoueur = new QuestionJoueur(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    Integer.parseInt(routingContext.request().getParam("nbre_tentative")),
                    Boolean.valueOf(routingContext.request().getParam("booleen"))
            );

            Boolean result = questionJoueurJDBC.insertQuestionJoueur(questionJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qjtj.toJson(questionJoueur)));
            }
            else{
                LOGGER.warn("[QuestionJoueurHandler] Error to insert new questionJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionJoueurHandler] Exception error - addItem : "+e.getMessage());
        }
    }
    public static void updateItem(RoutingContext routingContext) {
        try {
            QuestionJoueur questionJoueur = new QuestionJoueur(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    Integer.parseInt(routingContext.request().getParam("nbre_tentative")),
                    Boolean.valueOf(routingContext.request().getParam("booleen"))
            );

            Boolean result = questionJoueurJDBC.updateQuestionJoueur(questionJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qjtj.toJson(questionJoueur)));
            }
            else{
                LOGGER.warn("[QuestionJoueurHandler] Error to update questionJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionJoueurHandler] Exception error - updateItem : "+e.getMessage());
        }
    }
    public static void deleteItem(RoutingContext routingContext) {
        try {
            QuestionJoueur questionJoueur = new QuestionJoueur(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    routingContext.request().getParam("nbre_tentative").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("nbre_tentative")),
                    routingContext.request().getParam("booleen").isEmpty() ? null : Boolean.valueOf(routingContext.request().getParam("booleen"))
            );

            Boolean result = questionJoueurJDBC.deleteQuestionJoueur(questionJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(qjtj.toJson(questionJoueur)));
            }
            else{
                LOGGER.warn("[QuestionJoueurHandler] Error to delete questionJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[QuestionJoueurHandler] Exception error - deleteItem : "+e.getMessage());
        }
    }
}
