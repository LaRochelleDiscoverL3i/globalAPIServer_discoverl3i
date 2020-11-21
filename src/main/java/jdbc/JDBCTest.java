package jdbc;

import jdbc.tableClass.joueur.Joueur;
import jdbc.tableClass.joueur.JoueurJDBC;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {
        JoueurJDBC jojdbc = new JoueurJDBC();

        List<Joueur> joueurs = jojdbc.getAllJoueur();
        for(Joueur jo : joueurs){
            System.out.println(jo);
        }

        Joueur jo2 = new Joueur(2,1,new Timestamp(1998, 05, 21, 0, 0, 0, 0), 1);

        System.out.println("Result sucess ? "+jojdbc.insertJoueur(jo2));
        joueurs = jojdbc.getAllJoueur();
        for(Joueur jo : joueurs){
            System.out.println(jo);
        }

        jo2.setLevel_game(5);
        System.out.println("Result sucess update ? "+jojdbc.updateJoueur(jo2));

        System.out.println("Result sucess delete ? "+jojdbc.deleteJoueur(jo2));
        joueurs = jojdbc.getAllJoueur();
        for(Joueur jo : joueurs){
            System.out.println(jo);
        }

    }
}
