package jdbc.tableClass.scan_joueur;

import com.sun.org.apache.xpath.internal.operations.Bool;
import jdbc.tableClass.Interfaces.TableInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ScanJoueur implements TableInterface {
    /**
     * Variables
     */
    private int idjoueur;
    private int idreponse;
    private int idquestion;
    private Boolean booleen_question;

    /**
     * Method   : ScanJoueur
     * Params   : idjoueur(Int), idreponse(Int), idquestion(Int), booleen_question(Boolean)
     * Return   : None
     * Def      : Init method
     */
    public ScanJoueur(int idjoueur, int idreponse, int idquestion, Boolean booleen_question){
        this.idjoueur = idjoueur;
        this.idreponse = idreponse;
        this.idquestion = idquestion;
        this.booleen_question = booleen_question;
    }

    /**
     * Method   : insertQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour insert un ScanJoueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean insertQuery(Connection con) throws SQLException {
        String query = "INSERT INTO scan_joueur (idjoueur, idreponse, idquestion, booleen_question)";
        query += " VALUES (?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.idjoueur);
        pst.setInt(2, this.idreponse);
        pst.setInt(3, this.idquestion);
        pst.setBoolean(4, this.booleen_question);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : updateQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour update un ScanJoueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean updateQuery(Connection con) throws SQLException {
        String query = "UPDATE scan_joueur SET booleen_question = ?";
        query += " WHERE idjoueur = ? AND idreponse = ? AND idquestion = ? ";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setBoolean(1, this.booleen_question);
        pst.setInt(2, this.idjoueur);
        pst.setInt(3, this.idreponse);
        pst.setInt(4, this.idquestion);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : deleteQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour delete un ScanJoueur et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean deleteQuery(Connection con) throws SQLException {
        return null;
    }

    /**
     * ===== Getter and Setter =====
     */

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
     * Method   : getBooleen_question
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > booleen_question
     *
     * @return
     */
    public Boolean getBooleen_question() {
        return booleen_question;
    }

    /**
     * Method   : setBooleen_question
     * Params   : booleen_question(Boolean)
     * Return   : None
     *
     * Def      : Setter > booleen_question
     *
     * @param booleen_question
     */
    public void setBooleen_question(Boolean booleen_question) {
        this.booleen_question = booleen_question;
    }
}
