package TableAPI.Scan_Joueur;

import io.vertx.ext.web.Router;

/**
 * Class    : ScanJoueurAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des routes de l'objet ScanJoueur
 */
public class ScanJoueurAPI {

    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'objet ScanJoueur
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
        router.get("/scansjoueur").handler(ScanJoueurHandler::getAllItems);
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
        router.post("/scanjoueur").handler(ScanJoueurHandler::addItem);
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
        router.delete("/scanjoueur").handler(ScanJoueurHandler::deleteItem);
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
        router.put("/scanjoueur").handler(ScanJoueurHandler::updateItem);
        return router;
    }

}
