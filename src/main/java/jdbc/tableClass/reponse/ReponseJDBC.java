package jdbc.tableClass.reponse;

import jdbc.PostgresJDBC;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.question_joueur.QuestionJoueur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReponseJDBC {
    /**
     * Variable
     */
    Connection con = null;

    /**
     * Method   : ReponseJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public ReponseJDBC(){
        if(con == null) this.connectionBDD();
    }

    /**
     * Method   : connectionBDD
     * Params   : None
     * Return   : None
     *
     * Def      : Method pour faire la connection avec la BDD
     */
    public void connectionBDD(){
        if(con == null){
            PostgresJDBC pjdbc = new PostgresJDBC();
            con = pjdbc.getConnection();
        }
    }

    /**
     * Method   : getAllReponse
     * Params   : None
     * Return   : List<Reponse>
     *
     * Def      : Method qui permet de retourner toutes les reponses de la BDD
     *
     * @return
     * @throws SQLException
     */
    public List<Reponse> getAllReponse() throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM reponse");

        List<Reponse> reponses = new ArrayList<>();

        while(rs.next()){

            Reponse reponse = new Reponse(
                    rs.getInt("idreponse"),
                    rs.getString("description_reponse"),
                    rs.getInt("idquestion")
            );

            reponses.add(reponse);
        }

        stm.close();
        return reponses;
    }

    /**
     * Method   : getReponseById
     * Params   : idjoueur(String)
     * Return   : Joueur
     *
     * Def      : Method qui permet de retourner un joueur par son id dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public Reponse getReponseById(int idreponse) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM reponse WHERE idreponse = "+idreponse+"";
        System.out.println(query);

        ResultSet rs = stm.executeQuery(query);

        Reponse result = null;

        while(rs.next()){
            result =  new Reponse(
                    rs.getInt("idreponse"),
                    rs.getString("description_reponse"),
                    rs.getInt("idquestion")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : getReponseByIdQuestion
     * Params   : idquestion(Integer)
     * Return   : Joueur
     *
     * Def      : Method qui permet de retourner une reponse par son idquestion dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public Reponse getReponseByIdQuestion(Integer idquestion) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM reponse WHERE idquestion = "+idquestion+"";
        System.out.println(query);

        ResultSet rs = stm.executeQuery(query);

        Reponse result = null;

        while(rs.next()){
            result =  new Reponse(
                    rs.getInt("idreponse"),
                    rs.getString("description_reponse"),
                    rs.getInt("idquestion")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : insertReponse
     * Params   : reponse(Reponse)
     * Return   : Boolean
     *
     * Def      : Method qui permet d'ajouter une reponse dans la BDD
     *
     * @param reponse
     * @return
     * @throws SQLException
     */
    public Boolean insertReponse(Reponse reponse) throws SQLException {
        if(con == null) this.connectionBDD();
        return reponse.insertQuery(con);
    }

    /**
     * Method   : updateReponse
     * Params   : reponse(Reponse)
     * Return   : Boolean
     *
     * Def      : Method qui permet de mettre à jour une reponse dans la BDD
     *
     * @param reponse
     * @return
     * @throws SQLException
     */
    public Boolean updateReponse(Reponse reponse) throws SQLException {
        if(con == null) this.connectionBDD();
        return reponse.updateQuery(con);
    }

    /**
     * Method   : deleteReponse
     * Params   : reponse(Reponse)
     * Return   : Boolean
     *
     * Def      : Method qui permet de supprimer une reponse dans la BDD
     *
     * @param reponse
     * @return
     * @throws SQLException
     */
    public Boolean deleteReponse(Reponse reponse) throws SQLException {
        if(con == null) this.connectionBDD();
        return reponse.deleteQuery(con);
    }
}