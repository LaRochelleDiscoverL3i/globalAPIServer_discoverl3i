import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * @author Justin Métayer
 * @version 1.0.0
 * Def      : Class APIServer use for start or stop REST API
 */
public class APIServer extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(APIServer.class);

    /**
     * Function : start
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void start() throws Exception {
        LOGGER.info("[mainAPIServer] Start server !");

        //Create Route for Vertx
        Router router = Router.router(vertx);
        //Define route
        router.get("/api/example/myname").handler(example.ExampleRoute::getAllName);
        router.get("/api/example/name/:name").handler(example.ExampleRoute::getOneName);
        //Start server
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    /**
     * Function : stop
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void stop() throws Exception {
        LOGGER.info("[mainAPIServer] Stop server !");
    }
}
