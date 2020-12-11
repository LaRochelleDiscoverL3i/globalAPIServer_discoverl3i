package LinkOtherAgents.Observer;

import io.vertx.ext.web.Router;

public class ObserverAPI {
    public Router getAllRoutes(Router routeur) {
        return routeur;
    }

    public Router path_get(Router router){
        router.get("/obs_scan/:joueur").handler(ObserverHandler::observer_get_chercheur);
        return router;
    }
}
