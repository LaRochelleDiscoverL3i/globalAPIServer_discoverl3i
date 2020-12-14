package LinkOtherAgents.Analyste;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class    : ObserverHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des handler des routes de l'agent Observer
 */
public class AnalysteHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysteHandler.class);
    private static Properties agents_config = new Properties();

    public static void analyste_get_curiosite(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("analyste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("analyste_port"));
            String joueur = routingContext.request().getParam("joueur");
            String requestUrl = "/api/analyste/curiosite/"+joueur;

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.get(port_observer, url_observer, requestUrl).send().onSuccess(
                    response -> {
                        LOGGER.info("[AnalysteHandler] Method : analyste_get_curiosite - onSuccess return");

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response.body().toJson()));
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[AnalysteHandler] Method : analyste_get_curiosite - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(jsonResponse));
                    }
            );

        }catch (Exception e){
            LOGGER.warn("[AnalysteHandler] Method : analyste_get_curiosite - Error message : "+e.getMessage());
        }
    }

    public static void analyste_post_creation(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("analyste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("analyste_port"));
            String requestUrl = "analyste/creation";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(
                    new JsonObject()
                            .put("joueur", routingContext.request().getParam("joueur"))
            ).onSuccess(
                    response -> {
                        LOGGER.info("[AnalysteHandler] Method : analyste_post_creation - onSuccess return");

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response.body().toJson()));
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[AnalysteHandler] Method : analyste_post_creation - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(jsonResponse));
                    }
            );

        }catch (Exception e){
            LOGGER.warn("[AnalysteHandler] Method : analyste_post_creation - Error message : "+e.getMessage());
        }
    }
}
