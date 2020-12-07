package jdbc.tableClass.Interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class    : TableInterface
 * Author   : Justin Métayer
 * Version  : 1.0.0
 *
 * Def      : Interface permettant de donner les fonctions SQL de base
 */
public interface TableInterface {
    public Boolean insertQuery(Connection con) throws SQLException;
    public Boolean updateQuery(Connection con) throws SQLException;
    public Boolean deleteQuery(Connection con) throws SQLException;
}
