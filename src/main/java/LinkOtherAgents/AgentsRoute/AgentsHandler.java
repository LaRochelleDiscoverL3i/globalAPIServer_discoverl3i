package LinkOtherAgents.AgentsRoute;

import LinkOtherAgents.Analyste.Analyste;
import LinkOtherAgents.Observeur.Observeur;
import LinkOtherAgents.PhoneGap.PhoneGap;
import LinkOtherAgents.Senariste.Senariste;
import TableToJson.Joueur.JoueurToJson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.joueur.JoueurJDBC;
import jdbc.tableClass.question.Question;
import jdbc.tableClass.question.QuestionJDBC;
import jdbc.tableClass.reponse.Reponse;
import jdbc.tableClass.reponse.ReponseJDBC;
import java.util.List;

/**
 * Class    : AgentsHandler
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour la gestion des réponses de l'API REST des Agents
 */
public class AgentsHandler {
    /**
     * Constantes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentsHandler.class);

    /**
     * Variables JDBC
     */
    private static JoueurJDBC joueurJDBC = new JoueurJDBC();
    private static QuestionJDBC questionJDBC = new QuestionJDBC();
    private static ReponseJDBC reponseJDBC = new ReponseJDBC();

    /**
     * Variables converter JSON
     */
    private static JoueurToJson jtj = new JoueurToJson();

    /**
     * Variables Agents
     */
    private static Analyste analyste = new Analyste();
    private static Senariste senariste = new Senariste();
    private static PhoneGap phoneGap = new PhoneGap();
    private static Observeur observeur = new Observeur();

    public static void agents_get_curiosite(RoutingContext routingContext) {
        try {

            String idJoueur = routingContext.request().getParam("idjoueur");

            JsonObject response = observeur.observeur_get_curiosite(idJoueur);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(response));

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_get_curiosite - Error message : " + e.getMessage());
        }
    }

    public static void agents_get_progession(RoutingContext routingContext) {
        try {

            String idJoueur = routingContext.request().getParam("idjoueur");

            JsonObject response = observeur.observeur_get_progression(idJoueur);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(response));

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_get_curiosite - Error message : " + e.getMessage());
        }
    }

    public static void agents_get_questionToAsk(RoutingContext routingContext) {
        try {
            String joueurId = routingContext.request().getParam("joueur");

            if (joueurJDBC.getJoueurById(joueurId) == null) {
                LOGGER.warn("[AgentsHandler] Method : agents_get_questionToAsk - Error message : Joueur deos not exist !");
                JsonObject response = new JsonObject();
                response.put("Error", "Joueur does not exist !");

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(response));
            }
            else{
                Joueur joueur = joueurJDBC.getJoueurById(joueurId);
                //Valeur du level game par defaut ? 0 ?
                Integer level_game = (joueur.getLevel_game() == null) ? 0 : joueur.getLevel_game();

                try{
                    List<Question> questionList = questionJDBC.getQuestionByLevel(level_game);

                    if(questionList.size() == 0 || questionList == null){
                        //Erreur
                        LOGGER.warn("[AgentsHandler] Method : agents_get_questionToAsk - during get list question by level - Null or empty result !");
                        JsonObject response = new JsonObject();
                        response.put("Error", "Null or empty result !");

                        routingContext.response()
                                .setStatusCode(500)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response));
                    }
                    else{
                        //C'est arbitraire on verra apres !
                        Question question = questionList.get(0);

                        //Send to phonegab
                        JsonObject jo_phonegab_request = new JsonObject();
                        jo_phonegab_request.put("question", question.getDescription_question());
                        JsonObject responseRequest = phoneGap.phonegab_post_question(jo_phonegab_request, joueurId);

                        //Send response
                        JsonObject response = new JsonObject();
                        response.put("joueur", joueurId);
                        response.put("level_game", level_game);

                        routingContext.response()
                                .setStatusCode(200)
                                .putHeader("content-type", "application/json")
                                .end(Json.encodePrettily(response));
                    }

                }catch (Exception e){
                    LOGGER.warn("[AgentsHandler] Method : agents_get_questionToAsk - during get list question by level - Error message : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_get_questionToAsk - Error message : " + e.getMessage());
        }
    }

    public static void agents_post_progression(RoutingContext routingContext) {
        try{
            String idJoueur = routingContext.request().getParam("idjoueur");
            String level = routingContext.request().getParam("level");

            JsonObject scenariste_jo = new JsonObject();
            scenariste_jo.put("idjoueur", idJoueur);
            scenariste_jo.put("level", level);

            JsonObject response = senariste.senariste_post_pogression(scenariste_jo);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(response));

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_post_progression - Error message : " + e.getMessage());
        }
    }

    public static void agents_post_curiosite(RoutingContext routingContext) {
        try{
            String idJoueur = routingContext.request().getParam("idjoueur");
            String level = routingContext.request().getParam("level");

            JsonObject scenariste_jo = new JsonObject();
            scenariste_jo.put("idjoueur", idJoueur);
            scenariste_jo.put("level", level);

            JsonObject response = senariste.senariste_post_curiosite(scenariste_jo);

            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(response));
        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_post_curiosite - Error message : " + e.getMessage());
        }
    }

    public static void agents_post_createUser(RoutingContext routingContext) {
        try {
            String joueurId = routingContext.request().getParam("joueur");


            if (joueurJDBC.getJoueurById(joueurId) == null) {
                Joueur joueur = new Joueur(
                        joueurId,
                        null,
                        null,
                        null
                );

                joueurJDBC.insertJoueur(joueur);

                try {
                    //Check si Amina desire vraiment juste le nom joueur
                    JsonObject analyste_jo = new JsonObject();
                    analyste_jo.put("idjoueur", joueur.getIdjoueur());
                    analyste.analyste_post_creation(analyste_jo);
                    LOGGER.info("[AgentsHandler] Method : agents_post_createUser - send creation to analyste");
                } catch (Exception e) {
                    LOGGER.warn("[AgentsHandler] Method : agents_post_createUser - During send to analyste - Error message : " + e.getMessage());
                }

                try {
                    //Check si Didi desire vraiment juste le nom joueur
                    JsonObject analyste_jo = new JsonObject();
                    analyste_jo.put("joueur", joueur.getIdjoueur());
                    senariste.senariste_post_addjoueur(jtj.toJson(joueur));
                    LOGGER.info("[AgentsHandler] Method : agents_post_createUser - send creation to senariste");
                } catch (Exception e) {
                    LOGGER.warn("[AgentsHandler] Method : agents_post_createUser - During send to senariste - Error message : " + e.getMessage());
                }

                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(jtj.toJson(joueur)));
            } else {
                JsonObject response = new JsonObject();
                response.put("Error", "Pseudo already exist !");

                routingContext.response()
                        .setStatusCode(409)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(response));
            }

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_post_createUser - Error message : " + e.getMessage());

        }
    }

    public static void agents_post_reponse(RoutingContext routingContext) {
        try {
            String joueur = routingContext.request().getParam("joueur");
            String reponse = routingContext.request().getParam("reponse");
            String question = routingContext.request().getParam("question");

            Question questionItem = questionJDBC.getQuestionByDescriptionQuestion(question);

            if(questionItem != null){
                Reponse reponseItem = reponseJDBC.getReponseByIdQuestion(questionItem.getIdquestion());

                /*
                ScanJoueur scanJoueur = new ScanJoueur(
                        joueur,
                        reponseItem.getIdreponse(),
                        questionItem.getIdquestion(),
                        (reponseItem.getDescription_reponse() == reponse)
                );
                scanJoueurJDBC.insertScanJoueur(scanJoueur);
                */

                //Post vers /response observer

                JsonObject observeur_jo = new JsonObject();
                observeur_jo.put("question", questionItem.getDescription_question());
                observeur_jo.put("reponse_joueur", reponse);
                observeur_jo.put("joueur", joueur);
                observeur_jo.put("bonne_reponse", reponseItem.getDescription_reponse());

                observeur.observeur_post_reponse(observeur_jo);

                //Retourne un 200
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end();
            }
            else{
                //Erreur
                LOGGER.warn("[AgentsHandler] Method : agents_post_reponse - Error message : No question found !");

                JsonObject response = new JsonObject();
                response.put("Error", "No question found !");

                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(response));
            }

        } catch (Exception e) {
            LOGGER.warn("[AgentsHandler] Method : agents_post_reponse - Error message : " + e.getMessage());
        }
    }
}
