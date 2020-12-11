package LinkOtherAgents.Observer;

import LinkOtherAgents.Interfaces.AgentsAPIInterface;
import io.vertx.ext.web.Router;

public class ObserverAPI implements AgentsAPIInterface {
    @Override
    public Router getAllRoutes(Router router) {
        router = this.path_get(router);
        return router;
    }

    @Override
    public Router path_get(Router router){
        router.get("/obs_chercheur/:joueur").handler(ObserverHandler::observer_get_chercheur);
        router.get("/obs_analyste/:joueur/:type").handler(ObserverHandler::observer_get_analyse);
        return router;
    }

    @Override
    public Router path_post(Router router){
        router.get("/obs_scan").handler(ObserverHandler::observer_post_scan);
        router.get("/obs_reponse").handler(ObserverHandler::observer_post_reponse);
        return router;
    }

    @Override
    public Router path_put(Router router){
        return router;
    }

    @Override
    public Router path_del(Router router){
        return router;
    }
}
