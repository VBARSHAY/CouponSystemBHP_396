package dao;

import beans.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends EntityDAO<Customer, Integer>{

    boolean isExistsByEmail(String email) throws SQLException, InterruptedException;
}
