package TableAPI.Joueur;

import io.vertx.ext.web.Router;

public class JoueurAPI {

    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    private Router addGetRoutes(Router router) {
        router.get("/joueurs").handler(JoueurHandler::getAllItems);
        return router;
    }

    private Router addPostRoutes(Router router) {
        router.post("/joueur").handler(JoueurHandler::addItem);
        return router;
    }


    private Router addDelRoutes(Router router) {
        router.delete("/joueur").handler(JoueurHandler::deleteItem);
        return router;
    }


    private Router addPutRoutes(Router router) {
        router.put("/joueur").handler(JoueurHandler::updateItem);
        return router;
    }

}
