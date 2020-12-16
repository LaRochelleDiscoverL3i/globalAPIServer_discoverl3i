package LinkOtherAgents.Observeur;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class    : Observeur
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe for agent Observeur
 */
public class Observeur {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Observeur.class);

    /**
     * Variables
     */
    private static Properties agents_config = new Properties();

    /**
     * Method   : observeur_get_curiosite
     * Params   : idJoueur(String)
     * Return   : JsonObject
     * Def      : Methode pour récupérer la curiosite sur l'agent observeur
     *
     * @param idJoueur
     * @return
     */
    public static JsonObject observeur_get_curiosite(String idJoueur){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String requestUrl = "/analyste/curiosite/"+idJoueur;

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.get(port_observer, url_observer, requestUrl).send().onSuccess(
                    response -> {
                        LOGGER.info("[Observeur] Method : observeur_get_curiosite - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Observeur] Method : observeur_get_curiosite - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Observeur] Method : observeur_get_curiosite - Error message : "+e.getMessage());
            return null;
        }
    }

    /**
     * Method   : observeur_get_progression
     * Params   : idJoueur(String)
     * Return   : JsonObject
     * Def      : Methode pour récupérer la progression sur l'agent observeur
     *
     * @param idJoueur
     * @return
     */
    public static JsonObject observeur_get_progression(String idJoueur){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
            agents_config.load(fis);

            String url_observer = agents_config.getProperty("observer_url");
            int port_observer = Integer.valueOf(agents_config.getProperty("observer_port"));
            String requestUrl = "/analyste/progression/"+idJoueur;

            final Vertx vertx = Vertx.vertx();
            WebClient client = WebClient.create(vertx);

            AtomicReference<JsonObject> result = new AtomicReference<>(new JsonObject());

            client.get(port_observer, url_observer, requestUrl).send().onSuccess(
                    response -> {
                        LOGGER.info("[Observeur] Method : observeur_get_progression - onSuccess return");
                        result.set(response.bodyAsJsonObject());
                    }
            ).onFailure(
                    error -> {
                        LOGGER.warn("[Observeur] Method : observeur_get_progression - Error message : "+error.getMessage());

                        final JsonObject jsonResponse = new JsonObject();
                        jsonResponse.put("Error", error.getMessage());
                        result.set(jsonResponse);
                    }
            );

            return result.get();

        }catch (Exception e){
            LOGGER.warn("[Observeur] Method : observeur_get_progression - Error message : "+e.getMessage());
            return null;
        }
    }

    /**
     * Method   : observeur_post_reponse
     * Params   : idJoueur(String)
     * Return   : JsonObject
     * Def      : Methode pour envoyer la reponse sur l'agent observeur
     *
     * @param content
     * @return
     */
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
