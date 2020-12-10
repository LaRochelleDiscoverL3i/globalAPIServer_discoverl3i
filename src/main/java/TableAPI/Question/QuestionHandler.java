package TableAPI.Question;

import TableToJson.Joueur.JoueurToJson;
import TableToJson.Question.QuestionToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.joueur.JoueurJDBC;
import jdbc.tableClass.question.Question;
import jdbc.tableClass.question.QuestionJDBC;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QuestionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionHandler.class);
    private static QuestionJDBC questionJDBC = new QuestionJDBC();
    private static QuestionToJson qtj = new QuestionToJson();

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
    public static void addItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    routingContext.request().getParam("indice"),
                    Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    routingContext.request().getParam("description_question"),
                    Integer.parseInt(routingContext.request().getParam("level_game")),
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
    public static void updateItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    routingContext.request().getParam("indice"),
                    Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    routingContext.request().getParam("description_question"),
                    Integer.parseInt(routingContext.request().getParam("level_game")),
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
    public static void deleteItem(RoutingContext routingContext) {
        try {
            Question question = new Question(
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    routingContext.request().getParam("indice").isEmpty() ? null : routingContext.request().getParam("indice"),
                    routingContext.request().getParam("positionreponse").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("positionreponse")),
                    routingContext.request().getParam("description_question").isEmpty() ? null : routingContext.request().getParam("description_question"),
                    routingContext.request().getParam("level_game").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("level_game")),
                    routingContext.request().getParam("idreponse").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("idreponse"))
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
