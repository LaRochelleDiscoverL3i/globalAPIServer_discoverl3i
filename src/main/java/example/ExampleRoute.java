package example;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

/**
 * @author Justin Métayer
 * @version 1.0.0
 * Def      : Example class
 */
public class ExampleRoute {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleRoute.class);

    /**
     * Function : getAllName
     * @param routingContext
     * Def      : Method to get all name -> example to get with no parameter
     */
    public static void getAllName(RoutingContext routingContext) {
        LOGGER.info("[Example] Get my name...");
        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("my-name", "Justin");

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encodePrettily(jsonResponse));
    }

    /**
     * Function : getOneName
     * @param routingContext
     * Def      : Method to get a specific name -> example to use name (param)
     */
    public static void getOneName(RoutingContext routingContext) {
        LOGGER.info("[Example] Get a name...");

        final String name = routingContext.request().getParam("name");

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("my-name", name);

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encodePrettily(jsonResponse));
    }
}
