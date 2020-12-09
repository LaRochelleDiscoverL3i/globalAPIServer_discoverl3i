package TableAPI.Scan_Joueur;

import TableToJson.Reponse.ReponseToJson;
import TableToJson.Scan_Joueur.ScanJoueurToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.reponse.Reponse;
import jdbc.tableClass.reponse.ReponseJDBC;
import jdbc.tableClass.scan_joueur.ScanJoueur;
import jdbc.tableClass.scan_joueur.ScanJoueurJDBC;

import java.util.ArrayList;
import java.util.List;

public class ScanJoueurHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanJoueurHandler.class);
    private static ScanJoueurJDBC scanJoueurJDBC = new ScanJoueurJDBC();
    private static ScanJoueurToJson cstj = new ScanJoueurToJson();

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
        }
    }
    public static void addItem(RoutingContext routingContext) {
        try {
            ScanJoueur scanJoueur = new ScanJoueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
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
        }
    }
    public static void updateItem(RoutingContext routingContext) {
        try {
            ScanJoueur scanJoueur = new ScanJoueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
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
        }
    }
    public static void deleteItem(RoutingContext routingContext) {
        try {
            ScanJoueur scanJoueur = new ScanJoueur(
                    Integer.parseInt(routingContext.request().getParam("idjoueur")),
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
        }
    }
}
