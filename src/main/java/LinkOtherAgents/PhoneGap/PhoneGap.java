package LinkOtherAgents.PhoneGap;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class    : PhoneGap
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe for agent PhoneGap
 */
public class PhoneGap {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneGap.class);

    /**
     * Variables
     */
    private static Properties agents_config = new Properties();

    /**
     * Method   : phonegab_post_question
     * Params   : content(JsonObject), joueurId(String)
     * Return   : JsonObject
     * Def      : Methode pour envoyer la question à PhoneGap
     *
     * @param content
     * @param joueurId
     * @return
     */
    public static JsonObject phonegab_post_question (JsonObject content, String joueurId){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("phonegap_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("phonegap_port"));
            String requestUrl = "/question/"+joueurId;

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
}
