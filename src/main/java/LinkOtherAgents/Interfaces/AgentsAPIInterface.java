package LinkOtherAgents.Interfaces;

import LinkOtherAgents.Observer.ObserverHandler;
import io.vertx.ext.web.Router;

public interface AgentsAPIInterface {
    public Router getAllRoutes(Router router);
    public Router path_get(Router router);
    public Router path_post(Router router);
    public Router path_put(Router router);
    public Router path_del(Router router);
}
