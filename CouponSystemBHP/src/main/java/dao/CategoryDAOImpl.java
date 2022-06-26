package dao;

import db.JDBCUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO{

    private static final String QUERY_INSERT = "INSERT INTO `coupon-system-396`.`categories` (`name`) VALUES (?);\n";

    @Override
    public void addCategory(String name) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        JDBCUtils.execute(QUERY_INSERT, params);
    }
}
