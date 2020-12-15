package LinkOtherAgents.AgentsRoute;

import LinkOtherAgents.Analyste.Analyste;
import LinkOtherAgents.Observer.ObserverHandler;
import TableToJson.Joueur.JoueurToJson;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.joueur.JoueurJDBC;

import java.io.FileInputStream;
import java.util.Properties;

public class AgentsHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentsHandler.class);

    public static void agents_post_createUser(RoutingContext routingContext){
        try{
            String joueurId = routingContext.request().getParam("joueur");
            JoueurJDBC joueurJDBC = new JoueurJDBC();
            JoueurToJson jtj = new JoueurToJson();


            if(joueurJDBC.getJoueurById(joueurId) == null){
                Joueur joueur = new Joueur(
                        joueurId,
                        null,
                        null,
                        null
                );

                joueurJDBC.insertJoueur(joueur);

                try{
                    //Check si Amina desire vraiment juste le nom joueur
                    Analyste analyste = new Analyste();
                    analyste.anayliste_post_creation(jtj.toJson(joueur));
                    LOGGER.info("[AgentsHandler] Method : agents_post_createUser - send creation to analyste");
                }catch (Exception e){
                    LOGGER.warn("[AgentsHandler] Method : agents_post_createUser - During send to analyste - Error message : "+e.getMessage());
                }

                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jtj.toJson(joueur)));
            }
            else{
                JsonObject response = new JsonObject();
                response.put("Error", "Pseudo already exist !");

                routingContext.response()
                        .setStatusCode(409)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(response));
            }

        }catch (Exception e){
            LOGGER.warn("[AgentsHandler] Method : agents_post_createUser - Error message : "+e.getMessage());

        }
    }
}
