package jdbc.tableClass.question_joueur;

import jdbc.PostgresJDBC;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.question.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionJoueurJDBC {
    /**
     * Variable
     */
    Connection con = null;

    /**
     * Method   : QuestionJoueurJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public QuestionJoueurJDBC(){
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
     * Method   : getAllQuestionJoueur
     * Params   : None
     * Return   : List<QuestionJoueur>
     *
     * Def      : Method qui permet de retourner toutes les QuestionsJoueurs de la BDD
     *
     * @return
     * @throws SQLException
     */
    public List<QuestionJoueur> getAllQuestionJoueur() throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM question_joueur");

        List<QuestionJoueur> questionsjoueurs = new ArrayList<>();

        while(rs.next()){

            QuestionJoueur questionjoueur = new QuestionJoueur(
                    rs.getInt("idquestion"),
                    rs.getString("idjoueur"),
                    rs.getInt("nbre_tentative"),
                    rs.getBoolean("booleen")
            );

            questionsjoueurs.add(questionjoueur);
        }

        stm.close();
        return questionsjoueurs;
    }

    /**
     * Method   : getQuestionJoueurById
     * Params   : idjoueur(String), idquestion(int)
     * Return   : QuestionJoueur
     *
     * Def      : Method qui permet de retourner une questionJoueur par son id dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public QuestionJoueur getQuestionJoueurById(String idjoueur, int idquestion) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM joueur WHERE idjoueur = '"+idjoueur+"' AND idquestion = "+idquestion;
        System.out.println(query);

        ResultSet rs = stm.executeQuery(query);

        QuestionJoueur result = null;

        while(rs.next()){
            result  = new QuestionJoueur(
                    rs.getInt("idquestion"),
                    rs.getString("idjoueur"),
                    rs.getInt("nbre_tentative"),
                    rs.getBoolean("booleen")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : insertQuestionJoueur
     * Params   : questionjoueur(QuestionJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet d'ajouter une QuestionsJoueurs dans la BDD
     *
     * @param questionjoueur
     * @return
     * @throws SQLException
     */
    public Boolean insertQuestionJoueur(QuestionJoueur questionjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return questionjoueur.insertQuery(con);
    }

    /**
     * Method   : updateQuestionJoueur
     * Params   : questionjoueur(QuestionJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de mettre à jour une QuestionsJoueurs dans la BDD
     *
     * @param questionjoueur
     * @return
     * @throws SQLException
     */
    public Boolean updateQuestionJoueur(QuestionJoueur questionjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return questionjoueur.updateQuery(con);
    }

    /**
     * Method   : deleteQuestionJoueur
     * Params   : questionJoueur(QuestionJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de supprimer une QuestionsJoueurs dans la BDD
     *
     * @param questionJoueur
     * @return
     * @throws SQLException
     */
    public Boolean deleteQuestionJoueur(QuestionJoueur questionJoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return questionJoueur.deleteQuery(con);
    }
}