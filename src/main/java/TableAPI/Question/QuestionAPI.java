package TableAPI.Question;

import io.vertx.ext.web.Router;

public class QuestionAPI {

    public Router getAllRoutes(Router routeur) {
        routeur = this.addGetRoutes(routeur);
        routeur = this.addPostRoutes(routeur);
        routeur = this.addPutRoutes(routeur);
        routeur = this.addDelRoutes(routeur);
        return routeur;
    }

    private Router addGetRoutes(Router router) {
        router.get("/questions").handler(QuestionHandler::getAllItems);
        return router;
    }

    private Router addPostRoutes(Router router) {
        router.post("/question").handler(QuestionHandler::addItem);
        return router;
    }


    private Router addDelRoutes(Router router) {
        router.delete("/question").handler(QuestionHandler::deleteItem);
        return router;
    }


    private Router addPutRoutes(Router router) {
        router.put("/question").handler(QuestionHandler::updateItem);
        return router;
    }

}
