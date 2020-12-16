package TableAPI.Joueur;

import io.vertx.ext.web.Router;

/**
 * Class    : JoueurAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des routes de l'objet Joueur
 */
public class JoueurAPI {

    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'objet Joueur
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
        router.get("/joueurs").handler(JoueurHandler::getAllItems);
        router.get("/joueur/:idjoueur").handler(JoueurHandler::getItemById);
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
        router.post("/joueur").handler(JoueurHandler::addItem);
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
        router.delete("/joueur").handler(JoueurHandler::deleteItem);
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
        router.put("/joueur").handler(JoueurHandler::updateItem);
        return router;
    }

}
