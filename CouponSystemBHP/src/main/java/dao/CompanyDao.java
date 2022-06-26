package dao;

import beans.Company;

import java.sql.SQLException;

public interface CompanyDao extends EntityDAO<Company, Integer>{
    boolean isExistsByName(String name) throws SQLException, InterruptedException;
}
