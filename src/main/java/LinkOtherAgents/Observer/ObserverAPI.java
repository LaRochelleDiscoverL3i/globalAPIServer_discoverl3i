package LinkOtherAgents.Observer;

import LinkOtherAgents.Interfaces.AgentsAPIInterface;
import io.vertx.ext.web.Router;

/**
 * Class    : ObserverAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des routes de l'agent Observer
 */
public class ObserverAPI implements AgentsAPIInterface {

    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'agent Observer
     *
     * @param router
     * @return
     */
    @Override
    public Router getAllRoutes(Router router) {
        router = this.path_get(router);
        return router;
    }

    /**
     * ========== ---------- ==========
     *          Setter routes
     * ========== ---------- ==========
     */

    /**
     * Method   : path_get
     * Params   : router(Router)
     * Return   : Router
     * Def      : Methode pour set les routes GET
     *
     * @param router
     * @return
     */
    @Override
    public Router path_get(Router router){
        router.get("/obs_chercheur/:joueur").handler(ObserverHandler::observer_get_chercheur);
        router.get("/obs_analyste/:joueur/:type").handler(ObserverHandler::observer_get_analyse);
        return router;
    }

    /**
     * Method   : path_post
     * Params   : router(Router)
     * Return   : Router
     * Def      : Methode pour set les routes POST
     *
     * @param router
     * @return
     */
    @Override
    public Router path_post(Router router){
        router.get("/obs_scan").handler(ObserverHandler::observer_post_scan);
        router.get("/obs_reponse").handler(ObserverHandler::observer_post_reponse);
        return router;
    }

    /**
     * Method   : path_put
     * Params   : router(Router)
     * Return   : Router
     * Def      : Methode pour set les routes PUT
     *
     * @param router
     * @return
     */
    @Override
    public Router path_put(Router router){
        return router;
    }

    /**
     * Method   : path_del
     * Params   : router(Router)
     * Return   : Router
     * Def      : Methode pour set les routes DEL
     *
     * @param router
     * @return
     */
    @Override
    public Router path_del(Router router){
        return router;
    }
}
