# GlobalAPIServer_discoverL3i

## Description
Java application to create an API to centralize queries

## Example
```java
    @Override
public void start() throws Exception {
        LOGGER.info("[example.mainAPIServer] Start server !");

        //Create Route for Vertx
        Router router = Router.router(vertx);
        //Define route
        router.get("/api/example/myname").handler(example.ExampleRoute::getAllName);
        router.get("/api/example/name/:name").handler(example.ExampleRoute::getOneName);
        //Start server
        vertx.createHttpServer()
        .requestHandler(router)
        .listen(8080);
        }
```

When you run the example, the server builds two GET routes.
- [http://localhost:8080/api/example/myname](http://localhost:8080/api/example/myname)
- [http://localhost:8080/api/name/:name](http://localhost:8080/api/example/:name)

For the second URL, if you specify the :name by your name, the display will change using this as a parameter.

## Struture

The project is divided into several packages:
- example
- config
- jdbc
- TableAPI
- TaleToJson

Each package has a specific role :
- example : Is an example
- config : Contains all configuration files
- jdbc : Controls the connection to the database as well as the conversion of database tables to Java object
- TableAPI : Builds routes and actions on a REST call as the return JSON result
- TableToJson : Allows conversion of the Java Class equivalent to a table to a JSON or a list of this class to a JSONArray

## Execution

> mvn clean compile exec:java

## Routes
### JDBC Routes

| Object          | URL                                          | Type | Action                       | Return    |
|-----------------|----------------------------------------------|------|------------------------------|-----------|
| Joueur          | /joueurs                                     | GET  | Return all joueurs           | JsonArray |
| Joueur          | /joueur/:idjoueur                            | GET  | Return Joueur by ID          | Json      |
| Joueur          | /joueur                                      | POST | Add new Joueur               | Json      |
| Joueur          | /joueur                                      | PUT  | Update Joueur                | Json      |
| Joueur          | /joueur                                      | DEL  | Delete Joueur                | Json      |
|                 |                                              |      |                              |           |
| Question        | /questions                                   | GET  | Return all questions         | JsonArray |
| Question        | /question/:idquestion                        | GET  | Return Question by ID        | Json      |
| Question        | /question                                    | POST | Add new Question             | Json      |
| Question        | /question                                    | PUT  | Update Question              | Json      |
| Question        | /question                                    | DEL  | Delete Question              | Json      |
|                 |                                              |      |                              |           |
| Question_Joueur | /questionsjoueurs                            | GET  | Return all questions_joueurs | JsonArray |
| Question_Joueur | /questionjoueur/:idjoueur/:idquestion        | GET  | Return Question_Joueur by id | Json      |
| Question_Joueur | /questionjoueur                              | POST | Add new Question_Joueur      | Json      |
| Question_Joueur | /questionjoueur                              | PUT  | Update Question_Joueur       | Json      |
| Question_Joueur | /questionjoueur                              | DEL  | Delete Question_Joueur       | Json      |
|                 |                                              |      |                              |           |
| Reponse         | /reponses                                    | GET  | Return all reponses          | JsonArray |
| Reponse         | /reponse/:idreponse                          | GET  | Return Reponse by ID         | Json      |
| Reponse         | /reponse                                     | POST | Add new Reponse              | Json      |
| Reponse         | /reponse                                     | PUT  | Update Reponse               | Json      |
| Reponse         | /reponse                                     | DEL  | Delete Reponse               | Json      |
|                 |                                              |      |                              |           |
| Scan_Joueur     | /scansjoueur                                 | GET  | Return all Scans Joueur      | JsonArray |
| Scan_Joueur     | /scanjoueur/:idjoueur/:idreponse/:idquestion | GET  | Return Scan_joueur by ID     | Json      |
| Scan_Joueur     | /scanjoueur                                  | POST | Add new Scan Joueur          | Json      |
| Scan_Joueur     | /scanjoueur                                  | PUT  | Update Scan Joueur           | Json      |
| Scan_Joueur     | /scanjoueur                                  | DEL  | Delete Scan Joueur           | Json      |

### Table Objects
#### Joueurs
```java
private String idjoueur;//Id de l'objet joueur
private Integer score;
private Timestamp temps_test;
private Integer level_game;
```

#### Questions
```java
/**
 * Enum
 */
public enum Categorie_question {
    collaboration,
    auteur,
    projet,
    creation
}

/**
 * Variables
 */
private Integer idquestion;//Id de l'objet question
private String indice;
private Integer positionreponse;
private String description_question;
private Integer level_game;
private Categorie_question categorie_question;
private Integer idreponse;
```

#### Questions Joueurs
```java
private Integer idquestion;
private String idjoueur;
private Integer nbre_tentative;
private Boolean booleen;
```

#### Reponses
```java
private Integer idreponse;
private String description_reponse;
private Integer idquestion;
```

#### Scans Joueurs
```java
private String idjoueur;
private Integer idreponse;
private Integer idquestion;
private Boolean booleen_question;
```

### Agents Routes
| URL                        | Type | Content                                                                                     | Action                                                                                                                                 |
|----------------------------|------|---------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| /api/curiosite/:idjoueur   | GET  | None                                                                                        | Performs a GET query on the observing agent and returns curiosity in response                                                          |
| /api/progression/:idjoueur | GET  | None                                                                                        | Performs a GET query on the observing agent and returns progession in response                                                         |
| /api/questionToAsk/:joueur | GET  | None                                                                                        | Allows to retrieve a question and send it to the phonegap client                                                                       |
|                            |      |                                                                                             |                                                                                                                                        |
| /api/create_user           | POST | ``` { "joueur" : "idjoueur" } ```                                                           | Phonegap request for creating a new player based on his name. If the creation is possible we send the information to the other agents. |
| /api/reponse               | POST | ``` { "joueur" : "idjoueur", "reponse" : "text reponse", "question" : "text question" } ``` | Gateway to the observing agent to send him a response                                                                                  |
| /api/curiosite             | POST | ``` { "idjoueur" : "idjoueur", "level" : "bas" } ```                                        | Transfer to the screenwriting agent curiosity                                                                                          |
| /api/progression           | POST | ``` { "idjoueur" : "idjoueur", "level" : "bas" } ```                                        | Transfer to the screenwriting agent the progression                                                                                    |