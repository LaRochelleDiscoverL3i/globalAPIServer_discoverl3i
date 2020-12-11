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

public class ObserverHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObserverHandler.class);
    private static Properties agents_config = new Properties();

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

        }
    }
}
