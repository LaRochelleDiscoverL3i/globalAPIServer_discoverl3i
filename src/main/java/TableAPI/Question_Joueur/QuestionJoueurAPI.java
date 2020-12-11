package TableAPI.Question_Joueur;

import io.vertx.ext.web.Router;

/**
 * Class    : QuestionJoueurAPI
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de gestion des routes de l'objet QuestionJoueur
 */
public class QuestionJoueurAPI {
    /**
     * Method   : getAllRoutes
     * Params   : routeur(Router)
     * Return   : Router
     * Def      : Methode pour set les routes pour l'objet QuestionJoueur
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
        router.get("/questionsjoueurs").handler(QuestionJoueurHandler::getAllItems);
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
        router.post("/questionjoueur").handler(QuestionJoueurHandler::addItem);
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
        router.delete("/questionjoueur").handler(QuestionJoueurHandler::deleteItem);
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
        router.put("/questionjoueur").handler(QuestionJoueurHandler::updateItem);
        return router;
    }

}
