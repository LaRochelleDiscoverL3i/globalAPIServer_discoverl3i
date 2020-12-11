package TableAPI.Joueur;

import TableToJson.Joueur.JoueurToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.joueur.JoueurJDBC;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : JoueurHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe gestion des retours des requêtes REST
 */
public class JoueurHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JoueurHandler.class);

    /**
     * Variables
     */
    private static JoueurJDBC joueurJDBC = new JoueurJDBC();
    private static JoueurToJson jtj = new JoueurToJson();

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
            List<Joueur> allJoueur = joueurJDBC.getAllJoueur();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allJoueur);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jtj.arrayToJson(obj_list)));
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
            Joueur joueur = new Joueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    Integer.parseInt(routingContext.request().getParam("score")),
                    Timestamp.valueOf(routingContext.request().getParam("temps_test")),
                    Integer.parseInt(routingContext.request().getParam("level_game"))
            );

            Boolean result = joueurJDBC.insertJoueur(joueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jtj.toJson(joueur)));
            }
            else{
                LOGGER.warn("[JoueurHandler] Error to insert new joueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[JoueurHandler] Exception error - addItem : "+e.getMessage());

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
            Joueur joueur = new Joueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    Integer.parseInt(routingContext.request().getParam("score")),
                    Timestamp.valueOf(routingContext.request().getParam("temps_test")),
                    Integer.parseInt(routingContext.request().getParam("level_game"))
            );

            Boolean result = joueurJDBC.updateJoueur(joueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jtj.toJson(joueur)));
            }
            else{
                LOGGER.warn("[JoueurHandler] Error to update joueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[JoueurHandler] Exception error - updateItem : "+e.getMessage());

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
            Joueur joueur = new Joueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
                    routingContext.request().getParam("score").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("score") ),
                    routingContext.request().getParam("temps_test").isEmpty() ? null : Timestamp.valueOf(routingContext.request().getParam("temps_test")),
                    routingContext.request().getParam("level_game").isEmpty() ? null : Integer.parseInt(routingContext.request().getParam("level_game"))
            );

            Boolean result = joueurJDBC.deleteJoueur(joueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jtj.toJson(joueur)));
            }
            else{
                LOGGER.warn("[JoueurHandler] Error to delete joueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[JoueurHandler] Exception error - deleteItem : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }
}
