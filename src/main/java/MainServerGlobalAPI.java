
import io.vertx.core.Vertx;

public class MainServerGlobalAPI{

    /**
     * Function  : main
     * @param args
     * Def      : run project
     */
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerClass());
    }
}
