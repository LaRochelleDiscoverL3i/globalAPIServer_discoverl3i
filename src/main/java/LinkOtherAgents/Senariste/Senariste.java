package LinkOtherAgents.Senariste;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class Senariste {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Senariste.class);
    private static Properties agents_config = new Properties();

    public static JsonObject senariste_post_addjoueur(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("senariste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("senariste_port"));
            String requestUrl = "/addJoueur";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

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

    public static JsonObject senariste_post_curiosite(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("senariste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("senariste_port"));
            String requestUrl = "/curiosite";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

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
            LOGGER.warn("[Analyste] Method : senariste_post_curiosite - Error message : "+e.getMessage());
            return null;
        }
    }

    public static JsonObject senariste_post_pogression(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("senariste_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("senariste_port"));
            String requestUrl = "/progression";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

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
            LOGGER.warn("[Analyste] Method : senariste_post_pogression - Error message : "+e.getMessage());
            return null;
        }
    }
}
