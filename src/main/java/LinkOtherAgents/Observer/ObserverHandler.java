package LinkOtherAgents.Observer;

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
public class ObserverHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObserverHandler.class);
    private static Properties agents_config = new Properties();

    /**
     * Method   : observer_get_chercheur
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête GET chercheur
     *
     * @param routingContext
     */
    public static void observer_get_chercheur(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String joueur = routingContext.request().getParam("joueur");
            String requestUrl = "/chercheur/"+joueur;

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.get(port_observer, url_observer, requestUrl).send().onSuccess(
              response -> {
                  LOGGER.info("[ObserverHandler] Method : observer_get_chercheur - onSuccess return");

                  routingContext.response()
                          .setStatusCode(200)
                          .putHeader("content-type", "application/json")
                          .end(Json.encodePrettily(response.body().toJson()));
              }
            ).onFailure(
                error -> {
                    LOGGER.warn("[ObserverHandler] Method : observer_get_chercheur - Error message : "+error.getMessage());

                    final JsonObject jsonResponse = new JsonObject();
                    jsonResponse.put("Error", error.getMessage());

                    routingContext.response()
                            .setStatusCode(500)
                            .putHeader("content-type", "application/json")
                            .end(Json.encodePrettily(jsonResponse));
                }
            );

        }catch (Exception e){
            LOGGER.warn("[ObserverHandler] Method : observer_get_chercheur - Error message : "+e.getMessage());
        }
    }

    /**
     * Method   : observer_get_analyse
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête GET analyse
     *
     * @param routingContext
     */
    public static void observer_get_analyse(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String joueur = routingContext.request().getParam("joueur");
            String type = routingContext.request().getParam("joueur");
            String requestUrl = "/analyste/"+joueur+"/"+type;

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.get(port_observer, url_observer, requestUrl).send().onSuccess(
                    response -> {
                        LOGGER.info("[ObserverHandler] Method : observer_get_analyse - onSuccess return");

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response.body().toJson()));
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(jsonResponse));
                    }
            );

        }catch (Exception e){
            LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+e.getMessage());
        }
    }

    /**
     * Method   : observer_post_scan
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête POST scan
     *
     * @param routingContext
     */
    public static void observer_post_scan(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String requestUrl = "/reponse";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(
                    new JsonObject()
                        .put("question", agents_config.getProperty("question"))
                        .put("chercheur", agents_config.getProperty("chercheur"))
                        .put("joueur", agents_config.getProperty("joueur"))
            ).onSuccess(
                    response -> {
                        LOGGER.info("[ObserverHandler] Method : observer_get_analyse - onSuccess return");

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response.body().toJson()));
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(jsonResponse));
                    }
            );

        }catch (Exception e){
            LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+e.getMessage());
        }
    }

    /**
     * Method   : observer_post_reponse
     * Params   : routingContext(RoutingContext)
     * Return   : None
     * Def      : Methode pour le retour de la requête POST reponse
     *
     * @param routingContext
     */
    public static void observer_post_reponse(RoutingContext routingContext){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String requestUrl = "/reponse ";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(
                    new JsonObject()
                        .put("question", agents_config.getProperty("question"))
                        .put("reponse", agents_config.getProperty("chercheur"))
                        .put("joueur", agents_config.getProperty("joueur"))
            ).onSuccess(
                    response -> {
                        LOGGER.info("[ObserverHandler] Method : observer_get_analyse - onSuccess return");

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response.body().toJson()));
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(jsonResponse));
                    }
            );

        }catch (Exception e){
            LOGGER.warn("[ObserverHandler] Method : observer_get_analyse - Error message : "+e.getMessage());
        }
    }
}
