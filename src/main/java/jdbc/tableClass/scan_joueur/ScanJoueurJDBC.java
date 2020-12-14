package jdbc.tableClass.scan_joueur;

import jdbc.PostgresJDBC;
import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.reponse.Reponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScanJoueurJDBC {
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
    public ScanJoueurJDBC(){
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
     * Method   : getAllScanJoueurs
     * Params   : None
     * Return   : List<ScanJoueur>
     *
     * Def      : Method qui permet de retourner tous les ScanJoueurs de la BDD
     *
     * @return
     * @throws SQLException
     */
    public List<ScanJoueur> getAllScanJoueurs() throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM scan_joueur");

        List<ScanJoueur> scanjoueurs = new ArrayList<>();

        while(rs.next()){

            ScanJoueur scanjoueur = new ScanJoueur(
                    rs.getString("idjoueur"),
                    rs.getInt("idreponse"),
                    rs.getInt("idquestion"),
                    rs.getBoolean("booleen_question")
            );

            scanjoueurs.add(scanjoueur);
        }

        stm.close();
        return scanjoueurs;
    }

    /**
     * Method   : getScanJoueurById
     * Params   : idjoueur(String)
     * Return   : Joueur
     *
     * Def      : Method qui permet de retourner un joueur par son id dans la BDD
     *
     * @return
     * @throws SQLException
     */
    public ScanJoueur getScanJoueurById(String idjoueur, int idreponse, int idquestion) throws SQLException {
        if(con == null) this.connectionBDD();
        Statement stm = con.createStatement();

        String query = "SELECT * FROM scan_joueur WHERE idjoueur = '"+idjoueur+"' AND idreponse = "+idreponse+" AND "+idquestion;
        System.out.println(query);

        ResultSet rs = stm.executeQuery(query);

        ScanJoueur result = null;

        while(rs.next()){
            result = new ScanJoueur(
                    rs.getString("idjoueur"),
                    rs.getInt("idreponse"),
                    rs.getInt("idquestion"),
                    rs.getBoolean("booleen_question")
            );
        }

        stm.close();
        return result;
    }

    /**
     * Method   : insertScanJoueur
     * Params   : scanjoueur(ScanJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet d'ajouter un ScanJoueur dans la BDD
     *
     * @param scanjoueur
     * @return
     * @throws SQLException
     */
    public Boolean insertScanJoueur(ScanJoueur scanjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return scanjoueur.insertQuery(con);
    }

    /**
     * Method   : updateScanJoueur
     * Params   : scanjoueur(ScanJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de mettre à jour un ScanJoueur dans la BDD
     *
     * @param scanjoueur
     * @return
     * @throws SQLException
     */
    public Boolean updateScanJoueur(ScanJoueur scanjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return scanjoueur.updateQuery(con);
    }

    /**
     * Method   : deleteScanJoueur
     * Params   : scanjoueur(ScanJoueur)
     * Return   : Boolean
     *
     * Def      : Method qui permet de supprimer un ScanJoueur dans la BDD
     *
     * @param scanjoueur
     * @return
     * @throws SQLException
     */
    public Boolean deleteScanJoueur(ScanJoueur scanjoueur) throws SQLException {
        if(con == null) this.connectionBDD();
        return scanjoueur.deleteQuery(con);
    }
}