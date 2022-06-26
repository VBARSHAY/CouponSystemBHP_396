package dao;

import beans.Company;
import beans.Customer;
import db.JDBCUtils;
import db.ResultsUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO{
    private static final String QUERY_EXIST = "select exists(SELECT * FROM `coupon-system-396`.customers where email=? and password=?) as res";
    private static final String QUERY_EXIST_BY_EMAIL = "select exists(SELECT * FROM `coupon-system-396`.customers where email=?) as res";
    private static final String QUERY_INSERT = "INSERT INTO `coupon-system-396`.`customers` (`first_name`, `last_name`, `email`, `password`) VALUES (?, ?, ?, ?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon-system-396`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);\n";
    private static final String QUERY_DELETE = "DELETE FROM `coupon-system-396`.`customers` WHERE (`id` = ?);\n";
    private static final String QUERY_ALL = "select * FROM `coupon-system-396`.customers";
    private static final String QUERY_ONE = "select * FROM `coupon-system-396`.`customers` WHERE (`id` = ?);\n";

    @Override
    public boolean isExistsByEmailAndPassword(String email, String password) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param =  new HashMap<>();
        param.put(1, email);
        param.put(2, password);
        List<?> rows = JDBCUtils.executeResults(QUERY_EXIST, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));
        return results;
    }

    @Override
    public void addEntity(Customer customer) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());

        JDBCUtils.execute(QUERY_INSERT, params);
    }

    @Override
    public void updateEntity(Integer id, Customer customer) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, id);

        JDBCUtils.execute(QUERY_UPDATE, params);
    }

    @Override
    public void deleteEntity(Integer id) throws SQLException, InterruptedException {
        Map<Integer, Object> param =  new HashMap<>();
        param.put(1, id);
        JDBCUtils.execute(QUERY_DELETE, param);

    }

    @Override
    public List<Customer> findAllEntities() throws SQLException, InterruptedException {
        List<Customer> results =  new ArrayList<>();
        List<?> rows = JDBCUtils.executeResults(QUERY_ALL);
        for (Object obj: rows) {
            results.add(ResultsUtils.fromHashMapToCustomer((HashMap<String, Object>) obj));
        }
        return results;
    }

    @Override
    public Customer findEntityById(Integer id) throws SQLException, InterruptedException {
        Customer results =  null;
        Map<Integer, Object> param =  new HashMap<>();
        param.put(1, id);
        List<?> rows = JDBCUtils.executeResults(QUERY_ONE, param);
        results = ResultsUtils.fromHashMapToCustomer((HashMap<String, Object>) rows.get(0));
        return results;
    }


    public boolean isExistsByEmail(String email) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param =  new HashMap<>();
        param.put(1, email);
        List<?> rows = JDBCUtils.executeResults(QUERY_EXIST_BY_EMAIL, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));
        return results;
    }
}
