package facade;

import beans.Company;
import beans.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade {

    private static final String QUERY_UPDATE = "UPDATE `coupon-system-396`.`coupons` SET `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);\n";
    private static final String QUERY_IS_LOGIN_EXIST = "Select exists(SELECT * FROM `coupon-system-396`.customers Where email=? and password=?) as res";

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, email);
        param.put(2, password);
        List<?> rows = JDBCUtils.executeResults(QUERY_IS_LOGIN_EXIST, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));
        return results;

    }


    public void updateCoupon(Integer id, Coupon coupon) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(2, coupon.getCategory().ordinal() + 1);
        param.put(3, coupon.getTitle());
        param.put(4, coupon.getDescription());
        param.put(5, coupon.getStartDate());
        param.put(6, coupon.getEndDate());
        param.put(7, coupon.getAmount());
        param.put(8, coupon.getPrice());
        param.put(9, coupon.getImage());
        param.put(10, id);

        JDBCUtils.execute(QUERY_UPDATE, param);
    }

}
