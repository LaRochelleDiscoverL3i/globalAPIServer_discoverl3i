
import io.vertx.core.Vertx;

/**
 * Class    : MainServerGlobalAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Main class -> Start server
 */
public class MainServerGlobalAPI{

    /**
     * Method   : main
     * Params   : args(String[])
     * Return   : None
     * Def      : Methode pour set les routes pour l'objet Joueur
     *
     * Function  : main
     * @param args
     * Def      : run project
     */
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerClass());
    }
}
