package TableAPI.Reponse;

import io.vertx.ext.web.Router;

public class ReponseAPI {

    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    private Router addGetRoutes(Router router) {
        router.get("/reponses").handler(ReponseHandler::getAllItems);
        return router;
    }

    private Router addPostRoutes(Router router) {
        router.post("/reponse").handler(ReponseHandler::addItem);
        return router;
    }


    private Router addDelRoutes(Router router) {
        router.delete("/reponse").handler(ReponseHandler::deleteItem);
        return router;
    }


    private Router addPutRoutes(Router router) {
        router.put("/reponse").handler(ReponseHandler::updateItem);
        return router;
    }

}
