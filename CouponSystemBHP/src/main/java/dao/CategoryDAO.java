package dao;

import java.sql.SQLException;

public interface CategoryDAO {

    void addCategory(String name) throws SQLException, InterruptedException;
}
