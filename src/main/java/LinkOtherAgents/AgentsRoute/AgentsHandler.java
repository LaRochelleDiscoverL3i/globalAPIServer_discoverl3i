package LinkOtherAgents.AgentsRoute;

import LinkOtherAgents.Observer.ObserverHandler;
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
            Joueur joueur = new Joueur(
                    routingContext.request().getParam("joueur"),
                     null,
                    null,
                    null
            );

            JoueurJDBC joueurJDBC = new JoueurJDBC();
            joueurJDBC.insertJoueur(joueur);

        }catch (Exception e){
            LOGGER.warn("[AgentsHandler] Method : observer_get_chercheur - Error message : "+e.getMessage());
        }
    }
}
