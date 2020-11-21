package jdbc.tableClass.Interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface TableInterface {
    public Boolean insertQuery(Connection con) throws SQLException;
    public Boolean updateQuery(Connection con) throws SQLException;
    public Boolean deleteQuery(Connection con) throws SQLException;
}
