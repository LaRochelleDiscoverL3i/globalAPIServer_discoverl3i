package TableAPI.Scan_Joueur;

import TableToJson.Scan_Joueur.ScanJoueurToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.scan_joueur.ScanJoueur;
import jdbc.tableClass.scan_joueur.ScanJoueurJDBC;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : ScanJoueurHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe gestion des retours des requêtes REST pour les ScanJoueur
 */
public class ScanJoueurHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanJoueurHandler.class);

    /**
     * Variales
     */
    private static ScanJoueurJDBC scanJoueurJDBC = new ScanJoueurJDBC();
    private static ScanJoueurToJson cstj = new ScanJoueurToJson();

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
            List<ScanJoueur> allReponse = scanJoueurJDBC.getAllScanJoueurs();
            List<Object> obj_list = new ArrayList<>();
            obj_list.addAll(allReponse);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(cstj.arrayToJson(obj_list)));
        }catch (Exception e){
            LOGGER.warn("[ScanJoueurHandler] Exception error - getAllItems : "+e.getMessage());

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
            ScanJoueur joueur = scanJoueurJDBC.getScanJoueurById(routingContext.request().getParam("idjoueur"), Integer.valueOf(routingContext.request().getParam("idreponse")), Integer.valueOf(routingContext.request().getParam("idquestion")));

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(cstj.toJson(joueur)));
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
            ScanJoueur scanJoueur = new ScanJoueur(
                    routingContext.request().getParam("idjoueur"),
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    Boolean.valueOf(routingContext.request().getParam("booleen_question"))
            );

            Boolean result = scanJoueurJDBC.insertScanJoueur(scanJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(cstj.toJson(scanJoueur)));
            }
            else{
                LOGGER.warn("[ScanJoueurHandler] Error to insert new scanJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ScanJoueurHandler] Exception error - addItem : "+e.getMessage());

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
            ScanJoueur scanJoueur = new ScanJoueur(
                    routingContext.request().getParam("idjoueur"),
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    Boolean.valueOf(routingContext.request().getParam("booleen_question"))
            );

            Boolean result = scanJoueurJDBC.updateScanJoueur(scanJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(cstj.toJson(scanJoueur)));
            }
            else{
                LOGGER.warn("[ScanJoueurHandler] Error to update scanJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ScanJoueurHandler] Exception error - updateItem : "+e.getMessage());

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
            ScanJoueur scanJoueur = new ScanJoueur(
                    routingContext.request().getParam("idjoueur"),
                    Integer.parseInt(routingContext.request().getParam("idreponse")),
                    Integer.parseInt(routingContext.request().getParam("idquestion")),
                    routingContext.request().getParam("booleen_question").isEmpty() ? null : Boolean.valueOf(routingContext.request().getParam("booleen_question"))
            );

            Boolean result = scanJoueurJDBC.deleteScanJoueur(scanJoueur);

            if(result) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(cstj.toJson(scanJoueur)));
            }
            else{
                LOGGER.warn("[ScanJoueurHandler] Error to delete scanJoueur");

                final JsonObject jsonResponse = new JsonObject();
                jsonResponse.put("Error", result.toString());

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jsonResponse));
            }
        }catch (Exception e){
            LOGGER.warn("[ScanJoueurHandler] Exception error - deleteItem : "+e.getMessage());

            final JsonObject jsonResponse = new JsonObject();
            jsonResponse.put("Error", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }
}
