package jdbc.tableClass.joueur;

import java.sql.*;

/**
 * Class    : Joueur
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour l'object Joueur
 */
public class Joueur {
    /**
     * Variables
     */
    private int idjoueur;//Id de l'objet joueur
    private int score;
    private Timestamp temps_test;
    private int level_game;

    /**
     * Method   : Joueur
     * Params   : idjoueur(Int), score(Int), temps_test(Timestamp), level_game(Int)
     * Return   : None
     * Def      : Init method
     */
    public Joueur(int idjoueur, int score, Timestamp temps_test, int level_game){
        this.idjoueur = idjoueur;
        this.score = score;
        this.temps_test = temps_test;
        this.level_game = level_game;
    }

    /**
     * Method   : insertQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour insert un joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public Boolean insertQuery(Connection con) throws SQLException {
        String query = "INSERT INTO joueur (idjoueur, score, temps_test, level_game)";
        query += " VALUES (?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, this.idjoueur);
        pst.setInt(2, this.score);
        pst.setTimestamp(3, this.temps_test);
        pst.setInt(4, this.level_game);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;

    }

    /**
     * Method   : updateQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour update un joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public Boolean updateQuery(Connection con) throws SQLException {
        String query = "UPDATE joueur SET score = ?, temps_test = ?, level_game = ?";
        query += " WHERE idjoueur = ?";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, this.score);
        pst.setTimestamp(2, this.temps_test);
        pst.setInt(3, this.level_game);
        pst.setInt(4, this.idjoueur);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : deleteQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour delete un joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public Boolean deleteQuery(Connection con) throws SQLException {
        String query = "DELETE FROM joueur WHERE idjoueur = ?";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, this.idjoueur);
        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * ===== Getter and Setter =====
     */

    /**
     * Method   : setScore
     * Params   : score(Int)
     * Return   : None
     *
     * Def      : Setter > score
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method   : setTemps_test
     * Params   : temps_test(Timestamp)
     * Return   : None
     *
     * Def      : Setter > temps_test
     *
     * @param temps_test
     */
    public void setTemps_test(Timestamp temps_test) {
        this.temps_test = temps_test;
    }

    /**
     * Method   : setLevel_game
     * Params   : level_game(Int)
     * Return   : None
     *
     * Def      : Setter > level_game
     *
     * @param level_game
     */
    public void setLevel_game(int level_game) {
        this.level_game = level_game;
    }

    /**
     * Method   : getIdjoueur
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > idjoueur
     *
     * @return
     */
    public int getIdjoueur() {
        return idjoueur;
    }

    /**
     * Method   : getScore
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Method   : getTemps_test
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > temps_test
     *
     * @return
     */
    public Timestamp getTemps_test() {
        return temps_test;
    }

    /**
     * Method   : getLevel_game
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > level_game
     *
     * @return
     */
    public int getLevel_game() {
        return level_game;
    }
}
