package TableAPI.Reponse;

import TableToJson.Question_Joueur.QuestionJoueurToJson;
import TableToJson.Reponse.ReponseToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.question_joueur.QuestionJoueur;
import jdbc.tableClass.question_joueur.QuestionJoueurJDBC;
import jdbc.tableClass.reponse.Reponse;
import jdbc.tableClass.reponse.ReponseJDBC;

import java.util.ArrayList;
import java.util.List;

public class ReponseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReponseHandler.class);
    private static ReponseJDBC reponseJDBC = new ReponseJDBC();
    private static ReponseToJson rtj = new ReponseToJson();

    public static void getAllItems(RoutingContext routingContext) {
        try {
            List<Reponse> allReponse = reponseJDBC.getAllReponse();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allReponse);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(rtj.arrayToJson(obj_list)));
        }catch (Exception e){
            LOGGER.warn("[ReponseHandler] Exception error - getAllItems : "+e.getMessage());
        }
    }
    public static void addItem(RoutingContext routingContext) {
        try {
            Reponse reponse = new Reponse(
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    routingContext.request().getParam("description_reponse"),
                    Integer.parseInt(routingContext.request().getParam("idquestion"))
            );

            Boolean result = reponseJDBC.insertReponse(reponse);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(rtj.toJson(reponse)));
            }
            else{
                LOGGER.warn("[ReponseHandler] Error to insert new reponse");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ReponseHandler] Exception error - addItem : "+e.getMessage());
        }
    }
    public static void updateItem(RoutingContext routingContext) {
        try {
            Reponse reponse = new Reponse(
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    routingContext.request().getParam("description_reponse"),
                    Integer.parseInt(routingContext.request().getParam("idquestion"))
            );

            Boolean result = reponseJDBC.updateReponse(reponse);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(rtj.toJson(reponse)));
            }
            else{
                LOGGER.warn("[ReponseHandler] Error to update reponse");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ReponseHandler] Exception error - updateItem : "+e.getMessage());
        }
    }
    public static void deleteItem(RoutingContext routingContext) {
        try {
            Reponse reponse = new Reponse(
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    routingContext.request().getParam("description_reponse").isEmpty() ? null : routingContext.request().getParam("description_reponse"),
                    routingContext.request().getParam("idquestion").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("idquestion"))
            );

            Boolean result = reponseJDBC.deleteReponse(reponse);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(rtj.toJson(reponse)));
            }
            else{
                LOGGER.warn("[ReponseHandler] Error to delete reponse");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ReponseHandler] Exception error - deleteItem : "+e.getMessage());
        }
    }
}
