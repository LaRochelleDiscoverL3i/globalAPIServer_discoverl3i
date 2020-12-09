import TableAPI.Joueur.JoueurAPI;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

public class ServerClass extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerClass.class);

    /**
     * Function : start
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void start() throws Exception {
        LOGGER.info("[ServerClass] Start server !");

        //Create Route for Vertx
        Router router = Router.router(vertx);
        //Define route
        JoueurAPI joueurAPI = new JoueurAPI();

        router = joueurAPI.getAllRoutes(router);
        //Start server
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8000);
    }

    /**
     * Function : stop
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void stop() throws Exception {
        LOGGER.info("[ServerClass] Stop server !");
    }
}
