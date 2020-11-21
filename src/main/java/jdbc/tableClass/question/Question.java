package jdbc.tableClass.question;

import jdbc.tableClass.Interfaces.TableInterface;

import java.sql.*;

/**
 * Class    : Question
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour l'object Question
 */
public class Question implements TableInterface {
    /**
     * Variables
     */
    private int idquestion;//Id de l'objet question
    private String indice;
    private int positionreponse;
    private String description_question;
    private int level_game;
    private int idreponse;

    /**
     * Method   : Question
     * Params   : idquestion(Int), indice(String), positionreponse(Int), description_question(String), level_game(Int), idreponse(Int)
     * Return   : None
     * Def      : Init method
     */
    public Question(
            int idquestion,
            String indice,
            int positionreponse,
            String description_question,
            int level_game,
            int idreponse
    ){
        this.idquestion = idquestion;
        this.indice = indice;
        this.positionreponse = positionreponse;
        this.description_question = description_question;
        this.level_game = level_game;
        this.idreponse = idreponse;
    }

    /**
     * Method   : insertQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour insert une question et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean insertQuery(Connection con) throws SQLException {
        String query = "INSERT INTO question (idquestion, indice, positionreponse, description_question, level_game, idreponse)";
        query += " VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, this.idquestion);
        pst.setString(2, this.indice);
        pst.setInt(3, this.positionreponse);
        pst.setString(4, this.description_question);
        pst.setInt(5, this.level_game);
        pst.setInt(6, this.idreponse);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;

    }

    /**
     * Method   : updateQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour update une question et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean updateQuery(Connection con) throws SQLException {
        String query = "UPDATE question SET indice = ?, positionreponse = ?, description_question = ?, level_game = ?, idreponse = ?";
        query += " WHERE idquestion = ?";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, this.indice);
        pst.setInt(2, this.positionreponse);
        pst.setString(3, this.description_question);
        pst.setInt(4, this.level_game);
        pst.setInt(5, this.idreponse);
        pst.setInt(6, this.idquestion);


        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : deleteQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour delete une question et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean deleteQuery(Connection con) throws SQLException {
        String query = "DELETE FROM question WHERE idquestion = ?";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, this.idquestion);
        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * ===== Getter and Setter =====
     */

    /**
     * Method   : getIdquestion
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > idquestion
     *
     * @return
     */
    public int getIdquestion() {
        return idquestion;
    }

    /**
     * Method   : getIndice
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > indice
     *
     * @return
     */
    public String getIndice() {
        return indice;
    }

    /**
     * Method   : getPositionreponse
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > positionreponse
     *
     * @return
     */
    public int getPositionreponse() {
        return positionreponse;
    }

    /**
     * Method   : getDescription_question
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > description_question
     *
     * @return
     */
    public String getDescription_question() {
        return description_question;
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

    /**
     * Method   : getIdreponse
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > idreponse
     *
     * @return
     */
    public int getIdreponse() {
        return idreponse;
    }

    /**
     * Method   : setIndice
     * Params   : indice(String)
     * Return   : None
     *
     * Def      : Setter > indice
     *
     * @param indice
     */
    public void setIndice(String indice) {
        this.indice = indice;
    }

    /**
     * Method   : setPositionreponse
     * Params   : positionreponse(Int)
     * Return   : None
     *
     * Def      : Setter > positionreponse
     *
     * @param positionreponse
     */
    public void setPositionreponse(int positionreponse) {
        this.positionreponse = positionreponse;
    }

    /**
     * Method   : setDescription_question
     * Params   : description_question(String)
     * Return   : None
     *
     * Def      : Setter > description_question
     *
     * @param description_question
     */
    public void setDescription_question(String description_question) {
        this.description_question = description_question;
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
     * Method   : setIdreponse
     * Params   : idreponse(Int)
     * Return   : None
     *
     * Def      : Setter > idreponse
     *
     * @param idreponse
     */
    public void setIdreponse(int idreponse) {
        this.idreponse = idreponse;
    }
}

