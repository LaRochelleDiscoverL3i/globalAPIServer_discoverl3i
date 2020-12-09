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
- jdbc
- TableAPI
- TaleToJson

Each package has a specific role :
- example : Is an example
- jdbc : Controls the connection to the database as well as the conversion of database tables to Java object
- TableAPI : Builds routes and actions on a REST call as the return JSON result
- TableToJson : Allows conversion of the Java Class equivalent to a table to a JSON or a list of this class to a JSONArray

