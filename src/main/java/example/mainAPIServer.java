package example;

import io.vertx.core.Vertx;

/**
 * @author Justin Métayer
 * @version 1.0.0
 * Def      : Main class of project for run it
 */
public class mainAPIServer {
    /**
     * Function  : main
     * @param args
     * Def      : run project
     */
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new APIServer());
    }
}
