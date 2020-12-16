package jdbc.tableClass.question;

import jdbc.PostgresJDBC;
import jdbc.tableClass.joueur.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : QuestionJDBC {
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classe JDBC pour l'objet Question
 */
public class QuestionJDBC {
    /**
     * Variable
     */
    Connection con = null;

    /**
     * Method   : QuestionJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public QuestionJDBC(){
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
     * Method   : getAllQuestion
     * Params   : None
     * Return   : List<Question>
     *
     * Def      : Method qui permet de retourner toutes les Questions de la BDD
     *
     * @return
     * @throws SQLException
     */
    public List<Question> getAllQuestion() throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM question");

        List<Question> questions = new ArrayList<>();

        while(rs.next()){

            Question question = new Question(
                    rs.getInt("idquestion"),
                    rs.getString("indice"),
                    rs.getInt("positionreponse"),
                    rs.getString("description_question"),
                    rs.getInt("level_game"),
                    Question.Categorie_question.valueOf(rs.getString("categorie_question")),
                    rs.getInt("idreponse")
            );

            questions.add(question);
        }

        stm.close();
        return questions;
    }

    /**
     * Method   : getQuestionById
     * Params   : idquestion(int)
     * Return   : Question
     *
     * Def      : Method qui permet de retourner une question par son id dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public Question getQuestionById(int idquestion) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM question WHERE idquestion = "+idquestion+"";

        ResultSet rs = stm.executeQuery(query);

        Question result = null;

        while(rs.next()) {
            result = new Question(
                    rs.getInt("idquestion"),
                    rs.getString("indice"),
                    rs.getInt("positionreponse"),
                    rs.getString("description_question"),
                    rs.getInt("level_game"),
                    Question.Categorie_question.valueOf(rs.getString("categorie_question")),
                    rs.getInt("idreponse")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : getQuestionByDescriptionQuestion
     * Params   : description_question(String)
     * Return   : Question
     *
     * Def      : Method qui permet de retourner une question par sa description_question dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public Question getQuestionByDescriptionQuestion(String description_question) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM question WHERE description_question = "+description_question+" LIMIT 1";

        ResultSet rs = stm.executeQuery(query);

        Question result = null;

        while(rs.next()) {
            result = new Question(
                    rs.getInt("idquestion"),
                    rs.getString("indice"),
                    rs.getInt("positionreponse"),
                    rs.getString("description_question"),
                    rs.getInt("level_game"),
                    Question.Categorie_question.valueOf(rs.getString("categorie_question")),
                    rs.getInt("idreponse")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : getQuestionByLevel
     * Params   : level_game(Integer)
     * Return   : Question
     *
     * Def      : Method qui permet de retourner une liste de question par rapport au level
     *
     * @return
     * @throws SQLException
     */
    public List<Question> getQuestionByLevel(Integer level_game) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM question WHERE level_game = "+level_game;

        ResultSet rs = stm.executeQuery(query);
        List<Question> resultQuestionList = new ArrayList<>();

        while(rs.next()) {
            Question question = new Question(
                    rs.getInt("idquestion"),
                    rs.getString("indice"),
                    rs.getInt("positionreponse"),
                    rs.getString("description_question"),
                    rs.getInt("level_game"),
                    Question.Categorie_question.valueOf(rs.getString("categorie_question")),
                    rs.getInt("idreponse")
            );

            resultQuestionList.add(question);
        }

        stm.close();
        return resultQuestionList;
    }

    /**
     * Method   : insertQuestion
     * Params   : question(Question)
     * Return   : Boolean
     *
     * Def      : Method qui permet d'ajouter une question dans la BDD
     *
     * @param question
     * @return
     * @throws SQLException
     */
    public Boolean insertQuestion(Question question) throws SQLException {
        if(con == null) this.connectionBDD();
        return question.insertQuery(con);
    }

    /**
     * Method   : updateQuestion
     * Params   : question(Question)
     * Return   : Boolean
     *
     * Def      : Method qui permet de mettre à jour une qestion dans la BDD
     *
     * @param question
     * @return
     * @throws SQLException
     */
    public Boolean updateQuestion(Question question) throws SQLException {
        if(con == null) this.connectionBDD();
        return question.updateQuery(con);
    }

    /**
     * Method   : deleteQuestion
     * Params   : question(Question)
     * Return   : Boolean
     *
     * Def      : Method qui permet de supprimer un joueur dans la BDD
     *
     * @param question
     * @return
     * @throws SQLException
     */
    public Boolean deleteQuestion(Question question) throws SQLException {
        if(con == null) this.connectionBDD();
        return question.deleteQuery(con);
    }

}
