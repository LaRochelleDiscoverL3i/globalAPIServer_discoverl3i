package LinkOtherAgents.Analyste;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class    : Analyste
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour l'agent Analyste
 */
public class Analyste {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Analyste.class);

    /**
     * Variables
     */
    private static Properties agents_config = new Properties();

    /**
     * Method   : analyste_post_creation
     * Params   : content(JsonObject)
     * Return   : None
     * Def      : Request analyte to create Joueur
     *
     * @param content
     * @return
     */
    public static JsonObject analyste_post_creation(JsonObject content){
        try{
            //Read config file
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            //Set URL
            String url_observer = agents_config.getProperty("analyste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("analyste_port"));
            String requestUrl = "/analyste/creation";

            //Create WebClient for request
            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            //Request respond variable
            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(content).onSuccess(
                    response -> {
                        LOGGER.info("[Analyste] Method : anayliste_post_creation - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Analyste] Method : anayliste_post_creation - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Analyste] Method : anayliste_post_creation - Error message : "+e.getMessage());
            return null;
        }
    }

    public static JsonObject analyte_post_curiosite(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("analyste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("analyste_port"));
            String requestUrl = "/api/analyste/curiosite";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(content).onSuccess(
                    response -> {
                        LOGGER.info("[Analyste] Method : analyte_post_curiosite - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Analyste] Method : analyte_post_curiosite - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Analyste] Method : analyte_post_curiosite - Error message : "+e.getMessage());
            return null;
        }
    }

    public static JsonObject analyte_post_progression(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("analyste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("analyste_port"));
            String requestUrl = "/api/analyste/progression";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(content).onSuccess(
                    response -> {
                        LOGGER.info("[Analyste] Method : analyte_post_progression - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Analyste] Method : analyte_post_progression - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Analyste] Method : analyte_post_progression - Error message : "+e.getMessage());
            return null;
        }
    }
}
