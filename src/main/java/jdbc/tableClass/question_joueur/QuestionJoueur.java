package jdbc.tableClass.question_joueur;

import jdbc.tableClass.Interfaces.TableInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class    : QuestionJoueur
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe pour l'object Question_Joueur
 */
public class QuestionJoueur implements TableInterface {
    /**
     * Variables
     */
    private int idquestion;
    private String idjoueur;
    private int nbre_tentative;
    private Boolean booleen;

    /**
     * Method   : QuestionJoueur
     * Params   : idquestion(Int), idjoueur(String), nbre_tentative(Int), boolean(Boolean)
     * Return   : None
     * Def      : Init method
     */
    public QuestionJoueur(int idquestion, String idjoueur, int nbre_tentative, Boolean booleen){
        this.idquestion = idquestion;
        this.idjoueur = idjoueur;
        this.nbre_tentative = nbre_tentative;
        this.booleen = booleen;
    }

    /**
     * Method   : insertQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour insert une question_joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean insertQuery(Connection con) throws SQLException {
        String query = "INSERT INTO question_joueur (idquestion, idjoueur, nbre_tentative, booleen)";
        query += " VALUES (?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.idquestion);
        pst.setString(2, this.idjoueur);
        pst.setInt(3, this.nbre_tentative);
        pst.setBoolean(4, this.booleen);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : updateQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour update une question_joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean updateQuery(Connection con) throws SQLException {
        String query = "UPDATE question_joueur SET nbre_tentative = ?, booleen = ?";
        query += " WHERE idquestion = ? AND idjoueur = ? ";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.nbre_tentative);
        pst.setBoolean(2, this.booleen);
        pst.setInt(3, this.idquestion);
        pst.setString(4, this.idjoueur);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : deleteQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour delete une question_joueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean deleteQuery(Connection con) throws SQLException {
        String query = "DELETE FROM question WHERE idquestion = ? AND idjoueur = ?";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.idquestion);
        pst.setString(2, this.idjoueur);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * ===== Getter and Setter =====
     */

    /**
     * Method   : getIdquestion
     * Params   : None
     * Return   : int
     *
     * Def      : Getter > idquestion
     *
     * @return
     */
    public int getIdquestion() {
        return idquestion;
    }

    /**
     * Method   : getIdjoueur
     * Params   : None
     * Return   : String
     *
     * Def      : Getter > idjoueur
     *
     * @return
     */
    public String getIdjoueur() {
        return idjoueur;
    }

    /**
     * Method   : getNbre_tentative
     * Params   : None
     * Return   : int
     *
     * Def      : Getter > nbre_tentative
     *
     * @return
     */
    public int getNbre_tentative() {
        return nbre_tentative;
    }

    /**
     * Method   : getBooleen
     * Params   : None
     * Return   : Boolean
     *
     * Def      : Getter > booleen
     *
     * @return
     */
    public Boolean getBooleen() {
        return booleen;
    }

    /**
     * Method   : setNbre_tentative
     * Params   : nbre_tentative(Int)
     * Return   : None
     *
     * Def      : Setter > nbre_tentative
     *
     * @param nbre_tentative
     */
    public void setNbre_tentative(int nbre_tentative) {
        this.nbre_tentative = nbre_tentative;
    }

    /**
     * Method   : setBooleen
     * Params   : booleen(Boolean)
     * Return   : None
     *
     * Def      : Setter > booleen
     *
     * @param booleen
     */
    public void setBooleen(Boolean booleen) {
        this.booleen = booleen;
    }
}
