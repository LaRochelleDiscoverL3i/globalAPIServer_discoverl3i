import TableAPI.Joueur.JoueurAPI;
import TableAPI.Question.QuestionAPI;
import TableAPI.Question_Joueur.QuestionJoueurAPI;
import TableAPI.Reponse.ReponseAPI;
import TableAPI.Scan_Joueur.ScanJoueurAPI;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class    : ServerClass
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe setup server
 */
public class ServerClass extends AbstractVerticle {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerClass.class);
    private static final int DEFAULT_PORT = 8080;

    /**
     * Variables
     */
    private Properties server_config = new Properties();

    /**
     * Method   : start
     * Params   : None
     * Return   : None
     * Def      : Methode pour lancer le serveur et init les routes
     *
     * Function : start
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void start() throws Exception {
        LOGGER.info("[ServerClass] Load config for server !");
        FileInputStream fis = new FileInputStream("src/main/java/config/server_config.properties");
        server_config.load(fis);

        int port_load = server_config.getProperty("port") == null ? 0 : Integer.parseInt(server_config.getProperty("port"));

        LOGGER.info("[ServerClass] Start server !");
        //Create Route for Vertx
        Router router = Router.router(vertx);
        //Define route
        JoueurAPI joueurAPI = new JoueurAPI();
        QuestionAPI questionAPI = new QuestionAPI();
        QuestionJoueurAPI questionJoueurAPI = new QuestionJoueurAPI();
        ReponseAPI reponseAPI = new ReponseAPI();
        ScanJoueurAPI scanJoueurAPI = new ScanJoueurAPI();

        router = joueurAPI.getAllRoutes(router);
        router = questionAPI.getAllRoutes(router);
        router = questionJoueurAPI.getAllRoutes(router);
        router = reponseAPI.getAllRoutes(router);
        router = scanJoueurAPI.getAllRoutes(router);

        //Start server
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(port_load != 0 ? port_load : DEFAULT_PORT);
        LOGGER.info("[ServerClass] Open server at port : "+(port_load != 0 ? port_load : DEFAULT_PORT));
    }

    /**
     * Method   : stop
     * Params   : None
     * Return   : None
     * Def      : Methode pour stopper le serveur
     *
     * Function : stop
     * @throws Exception
     * Def      : Override stop method of Vertx
     */
    @Override
    public void stop() throws Exception {
        LOGGER.info("[ServerClass] Stop server !");
    }
}
