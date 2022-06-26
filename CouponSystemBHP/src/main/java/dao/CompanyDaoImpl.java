package dao;

import beans.Company;
import db.JDBCUtils;
import db.ResultsUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDaoImpl implements CompanyDao {

    private static final String QUERY_EXIST = "select exists(SELECT * FROM `coupon-system-396`.companies where email=? and password=?) as res";
    private static final String QUERY_INSERT = "INSERT INTO `coupon-system-396`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon-system-396`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);\n";
    private static final String QUERY_DELETE = "DELETE FROM `coupon-system-396`.`companies` WHERE (`id` = ?);\n";
    private static final String QUERY_ALL = "select * FROM `coupon-system-396`.companies";
    private static final String QUERY_ONE = "select * FROM `coupon-system-396`.`companies` WHERE (`id` = ?);\n";
    private static final String QUERY_EXIST_BY_NAME = "select * FROM `coupon-system-396`.`companies` WHERE (`name` = ?);\n";

    @Override
    public boolean isExistsByEmailAndPassword(String email, String password) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, email);
        param.put(2, password);
        List<?> rows = JDBCUtils.executeResults(QUERY_EXIST, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));
        return results;
    }

    @Override
    public void addEntity(Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, company.getName());
        param.put(2, company.getEmail());
        param.put(3, company.getPassword());

        JDBCUtils.execute(QUERY_INSERT, param);
    }

    @Override
    public void updateEntity(Integer id, Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, company.getName());
        param.put(2, company.getEmail());
        param.put(3, company.getPassword());
        param.put(4, id);

        JDBCUtils.execute(QUERY_UPDATE, param);
    }

    @Override
    public void deleteEntity(Integer id) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, id);

        JDBCUtils.execute(QUERY_DELETE, param);

    }

    @Override
    public List<Company> findAllEntities() throws SQLException, InterruptedException {
        List<Company> results = new ArrayList<>();
        List<?> rows = JDBCUtils.executeResults(QUERY_ALL);
        for (Object obj : rows) {
            results.add(ResultsUtils.fromHashTableToCompany((HashMap<String, Object>) obj));
        }
        return results;
    }

    @Override
    public Company findEntityById(Integer id) throws SQLException, InterruptedException {
        Company results = null;
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, id);
        List<?> rows = JDBCUtils.executeResults(QUERY_ONE, param);

        results = ResultsUtils.fromHashTableToCompany((HashMap<String, Object>) rows.get(0));
        return results;
    }

    @Override
    public boolean isExistsByName(String name) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, name);
        List<?> rows = JDBCUtils.executeResults(QUERY_EXIST_BY_NAME, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));

        return results;
    }
}
