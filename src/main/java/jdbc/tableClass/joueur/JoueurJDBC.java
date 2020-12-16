package jdbc.tableClass.joueur;

import jdbc.PostgresJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : JoueurJDBC
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Classes contenant les méthodes pour requêter la BDD sur la table Joueur
 */
public class JoueurJDBC {
    /**
     * Variable
     */
    Connection con = null;

    /**
     * Method   : JoueurJDBC
     * Params   : None
     * Return   : None
     * Def      : Init method
     */
    public JoueurJDBC(){
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
     * Method   : getAllJoueur
     * Params   : None
     * Return   : List<Joueur>
     *
     * Def      : Method qui permet de retourner tous les joueurs de la BDD
     *
     * @return
     * @throws SQLException
     */
    public List<Joueur> getAllJoueur() throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM joueur");

        List<Joueur> joueurs = new ArrayList<>();

        while(rs.next()){
            Joueur jo = new Joueur(
                    rs.getString("idjoueur"), rs.getInt("score"), rs.getTimestamp("temps_test"), rs.getInt("level_game")
            );

            joueurs.add(jo);
        }

        stm.close();
        return joueurs;
    }

    /**
     * Method   : getJoueurById
     * Params   : idjoueur(String)
     * Return   : Joueur
     *
     * Def      : Method qui permet de retourner un joueur par son id dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public Joueur getJoueurById(String idjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM joueur WHERE idjoueur = '"+idjoueur+"'";
        System.out.println(query);

        ResultSet rs = stm.executeQuery(query);

        Joueur result = null;

        while(rs.next()){
            result = new Joueur(
                    rs.getString("idjoueur"), rs.getInt("score"), rs.getTimestamp("temps_test"), rs.getInt("level_game")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : insertJoueur
     * Params   : joueur(Joueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet d'ajouter un joueur dans la BDD
     *
     * @param joueur
     * @return
     * @throws SQLException
     */
    public Boolean insertJoueur(Joueur joueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return joueur.insertQuery(con);
    }

    /**
     * Method   : updateJoueur
     * Params   : joueur(Joueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de mettre à jour un joueur dans la BDD
     *
     * @param joueur
     * @return
     * @throws SQLException
     */
    public Boolean updateJoueur(Joueur joueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return joueur.updateQuery(con);
    }

    /**
     * Method   : deleteJoueur
     * Params   : joueur(Joueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de supprimer un joueur dans la BDD
     *
     * @param joueur
     * @return
     * @throws SQLException
     */
    public Boolean deleteJoueur(Joueur joueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return joueur.deleteQuery(con);
    }

}
