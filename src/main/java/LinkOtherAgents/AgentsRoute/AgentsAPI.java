package LinkOtherAgents.AgentsRoute;

import io.vertx.ext.web.Router;

public class AgentsAPI {

    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'agent Observer
     *
     * @param router
     * @return
     */
    public Router getAllRoutes(Router router) {
        router = this.path_get(router);
        router = this.path_post(router);
        router = this.path_put(router);
        router = this.path_del(router);
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
    public Router path_get(Router router){
        router.get("/api/curiosite/:idjoueur").handler(AgentsHandler::agents_get_curiosite);
        router.get("/api/progression/:idjoueur").handler(AgentsHandler::agents_get_progession);
        router.get("/api/questionToAsk/:joueur").handler(AgentsHandler::agents_get_questionToAsk);
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
    public Router path_post(Router router){
        router.post("/api/create_user").handler(AgentsHandler::agents_post_createUser);
        router.post("/api/reponse").handler(AgentsHandler::agents_post_reponse);
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
    public Router path_del(Router router){
        return router;
    }
}
