package TableAPI.Question_Joueur;

import io.vertx.ext.web.Router;

public class QuestionJoueurAPI {

    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    private Router addGetRoutes(Router router) {
        router.get("/questionsjoueurs").handler(QuestionJoueurHandler::getAllItems);
        return router;
    }

    private Router addPostRoutes(Router router) {
        router.post("/questionjoueur").handler(QuestionJoueurHandler::addItem);
        return router;
    }


    private Router addDelRoutes(Router router) {
        router.delete("/questionjoueur").handler(QuestionJoueurHandler::deleteItem);
        return router;
    }


    private Router addPutRoutes(Router router) {
        router.put("/questionjoueur").handler(QuestionJoueurHandler::updateItem);
        return router;
    }

}
