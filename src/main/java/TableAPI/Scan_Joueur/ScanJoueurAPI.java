package TableAPI.Scan_Joueur;

import io.vertx.ext.web.Router;

public class ScanJoueurAPI {

    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    private Router addGetRoutes(Router router) {
        router.get("/scansjoueur").handler(ScanJoueurHandler::getAllItems);
        return router;
    }

    private Router addPostRoutes(Router router) {
        router.post("/scanjoueur").handler(ScanJoueurHandler::addItem);
        return router;
    }


    private Router addDelRoutes(Router router) {
        router.delete("/scanjoueur").handler(ScanJoueurHandler::deleteItem);
        return router;
    }


    private Router addPutRoutes(Router router) {
        router.put("/scanjoueur").handler(ScanJoueurHandler::updateItem);
        return router;
    }

}
