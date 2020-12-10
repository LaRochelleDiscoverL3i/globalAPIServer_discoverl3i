package jdbc;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Class    : PostgresJDBC
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe de connection à la BDD postgres
 */
public class PostgresJDBC {
    /**
     * Variables
     */
    private Connection con = null;

    /**
     * Postgres Connection Constante
     */
    /*private String BDD = "LaRochelleDiscoverL3i";
    private String login = "LaRochelleDiscoverL3i";
    private String password = "LaRochelleDiscoverL3i";
    private String url = "localhost:5432/"; //Penser au / à la fin
     */

    private Properties postgres_config = new Properties();

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresJDBC.class);

    /**
     * Method   : PostgresJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public PostgresJDBC(){
        try {
            if (con == null) this.ConnectionBDD();
        }catch (Exception e){
            LOGGER.warn("[PostgresJDBC] BDD connexion error : "+e.getMessage());
        }
    }

    /**
     * Method   : ConnectionBDD
     * Params   : None
     * Return   : None
     * Def      : Methode pour la génération de la connection
     */
    public void ConnectionBDD() throws IOException {
        LOGGER.info("[PostgresJDBC] Load config for Postgres !");
        FileInputStream fis = new FileInputStream("src/main/java/config/postgres_config.properties");
        postgres_config.load(fis);

        String url = postgres_config.getProperty("url");
        String BDD = postgres_config.getProperty("BDD");
        String login = postgres_config.getProperty("login");
        String password = postgres_config.getProperty("password");

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://"+url+BDD,
                            login, password);

            LOGGER.info("[PostgresJDBC] BDD connexion OK !");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        //System.out.println("Opened database successfully");
        LOGGER.info("[PostgresJDBC] Opened database successfully");
    }

    /**
     * Methode  : getConnection
     * Params   : None
     * Return   : Connection
     * Def      : Getter > Retourne la connection
     *
     * @return
     */
    public Connection getConnection() {
        try {
            if (con == null) this.ConnectionBDD();
        }catch (Exception e){
            LOGGER.warn("[PostgresJDBC] BDD connexion error : "+e.getMessage());
        }
        return con;
    }
}
