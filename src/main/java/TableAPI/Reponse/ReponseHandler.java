package TableAPI.Reponse;

import TableToJson.Reponse.ReponseToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.reponse.Reponse;
import jdbc.tableClass.reponse.ReponseJDBC;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : ReponseHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe gestion des retours des requêtes REST
 */
public class ReponseHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReponseHandler.class);

    /**
     * Variables
     */
    private static ReponseJDBC reponseJDBC = new ReponseJDBC();
    private static ReponseToJson rtj = new ReponseToJson();

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
            List<Reponse> allReponse = reponseJDBC.getAllReponse();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allReponse);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(rtj.arrayToJson(obj_list)));
        }catch (Exception e){
            LOGGER.warn("[ReponseHandler] Exception error - getAllItems : "+e.getMessage());

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
            Reponse joueur = reponseJDBC.getReponseById(Integer.valueOf(routingContext.request().getParam("idreponse")));

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(rtj.toJson(joueur)));
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
            Reponse reponse = new Reponse(
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    !routingContext.request().params().contains("description_reponse") ? null : routingContext.request().getParam("description_reponse"),
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
            Reponse reponse = new Reponse(
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    !routingContext.request().params().contains("description_reponse") ? null : routingContext.request().getParam("description_reponse"),
                    Integer.parseInt(routingContext.request().getParam("idquestion"))
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

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }
}
