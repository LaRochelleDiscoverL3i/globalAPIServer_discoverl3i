package TableAPI.Reponse;

import io.vertx.ext.web.Router;

/**
 * Class    : ReponseAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des routes de l'objet Reponse
 */
public class ReponseAPI {

    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'objet Reponse
     *
     * @param routeur
     * @return
     */
    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    /**
     * ========== ---------- ==========
     *          Setter routes
     * ========== ---------- ==========
     */

    /**
     * Method   : addGetRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set la route de récupération
     *
     * @param router
     * @return
     */
    private Router addGetRoutes(Router router) {
        router.get("/reponses").handler(ReponseHandler::getAllItems);
        router.get("/reponse/:idreponse").handler(ReponseHandler::getItemById);
        return router;
    }

    /**
     * Method   : addPostRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set la route d'ajout
     *
     * @param router
     * @return
     */
    private Router addPostRoutes(Router router) {
        router.post("/reponse").handler(ReponseHandler::addItem);
        return router;
    }

    /**
     * Method   : addDelRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set la route de supression
     *
     * @param router
     * @return
     */
    private Router addDelRoutes(Router router) {
        router.delete("/reponse").handler(ReponseHandler::deleteItem);
        return router;
    }

    /**
     * Method   : addPutRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set la route de mise à jour
     *
     * @param router
     * @return
     */
    private Router addPutRoutes(Router router) {
        router.put("/reponse").handler(ReponseHandler::updateItem);
        return router;
    }

}
