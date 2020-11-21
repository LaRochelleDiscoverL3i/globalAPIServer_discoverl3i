package jdbc.tableClass.question;

import jdbc.PostgresJDBC;

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
                    rs.getInt("idreponse")
            );

            questions.add(question);
        }

        stm.close();
        return questions;
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
