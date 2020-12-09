package jdbc;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.sql.*;

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
    private String BDD = "LaRochelleDiscoverL3i";
    private String login = "LaRochelleDiscoverL3i";
    private String password = "LaRochelleDiscoverL3i";
    private String url = "localhost:5432/"; //Penser au / à la fin

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresJDBC.class);


    /**
     * Method   : PostgresJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public PostgresJDBC(){
        if(con == null) this.ConnectionBDD();
    }

    /**
     * Method   : ConnectionBDD
     * Params   : None
     * Return   : None
     * Def      : Methode pour la génération de la connection
     */
    public void ConnectionBDD(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://"+url+BDD,
                            login, password);
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
        if(con == null) this.ConnectionBDD();
        return con;
    }
}
