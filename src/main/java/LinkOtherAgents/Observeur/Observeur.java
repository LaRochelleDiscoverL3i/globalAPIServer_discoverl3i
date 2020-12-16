package LinkOtherAgents.Observeur;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class Observeur {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Observeur.class);
    private static Properties agents_config = new Properties();

    public static JsonObject observeur_post_reponse(JsonObject content){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String requestUrl = "/reponse";

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.post(port_observer, url_observer, requestUrl).sendJsonObject(content).onSuccess(
                    response -> {
                        LOGGER.info("[Observeur] Method : observeur_post_reponse - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Observeur] Method : observeur_post_reponse - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Observeur] Method : observeur_post_reponse - Error message : "+e.getMessage());
            return null;
        }
    }
}
