package jdbc.tableClass.reponse;

import jdbc.tableClass.Interfaces.TableInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Reponse implements TableInterface {
    /**
     * Variables
     */
    private int idreponse;
    private String description_reponse;
    private int idquestion;

    /**
     * Method   : Reponse
     * Params   : idreponse(Int), description_reponse(String), idquestion(Int)
     * Return   : None
     * Def      : Init method
     */
    public Reponse(int idreponse, String description_reponse, int idquestion){
        this.idreponse = idreponse;
        this.description_reponse = description_reponse;
        this.idquestion = idquestion;
    }

    /**
     * Method   : insertQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour insert une reponse et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean insertQuery(Connection con) throws SQLException {
        String query = "INSERT INTO reponse (idresponse, description_reponse, idquestion)";
        query += " VALUES (?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.idreponse);
        pst.setString(2, this.description_reponse);
        pst.setInt(3, this.idquestion);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : updateQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour update une reponse et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean updateQuery(Connection con) throws SQLException {
        String query = "UPDATE reponse SET description_reponse = ?";
        query += " WHERE idreponse = ? AND idquestion = ? ";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, this.description_reponse);
        pst.setInt(2, this.idreponse);
        pst.setInt(3, this.idquestion);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * Method   : deleteQuery
     * Params   : con(Connection)
     * Return   : Boolean
     *
     * Def      : Method pour delete une reponse et retourne un boolean en fonction de l'état de réussite
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean deleteQuery(Connection con) throws SQLException {
        String query = "DELETE FROM question WHERE idreponse = ? AND idquestion = ? ";

        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, this.idreponse);
        pst.setInt(2, this.idquestion);

        int rowAffected = pst.executeUpdate();

        return (rowAffected == 1) ? true : false;
    }

    /**
     * ===== Getter and Setter =====
     */

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
     * Method   : getDescription_reponse
     * Params   : None
     * Return   : None
     *
     * Def      : Getter > description_reponse
     *
     * @return
     */
    public String getDescription_reponse() {
        return description_reponse;
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
     * Method   : setDescription_reponse
     * Params   : description_reponse(String)
     * Return   : None
     *
     * Def      : Setter > description_reponse
     *
     * @param description_reponse
     */
    public void setDescription_reponse(String description_reponse) {
        this.description_reponse = description_reponse;
    }
}
