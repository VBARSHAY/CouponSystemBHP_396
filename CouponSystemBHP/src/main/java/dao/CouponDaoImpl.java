package dao;

import beans.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDaoImpl implements CouponDAO {
    Date currentDate = Date.valueOf(LocalDate.now());

    private static final String QUERY_INSERT = "INSERT INTO `coupon-system-396`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon-system-396`.`coupons` SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);\n";
    private static final String QUERY_DELETE = "DELETE FROM `coupon-system-396`.`coupons` WHERE (`id` = ?);\n";
    private static final String QUERY_ALL = "select * FROM `coupon-system-396`.coupons";
    private static final String QUERY_ALL_BY_COMPANYID = "SELECT * FROM `coupon-system-396`.coupons where company_id = ?";
    private static final String QUERY_BY_ID = "SELECT * FROM `coupon-system-396`.coupons where id = ?";
    private static final String QUERY_EXP_COUPONS = "SELECT * FROM `coupon-system-396`.coupons where end_date < currentDate";


    private static final String QUERY_ADD_PURCHASE = "INSERT INTO `coupon-system-396`.`customers_coupons` (`CUSTOMER_id`, `COUPON_id`) VALUES (?, ?);\n";
    private static final String QUERY_DELETE_PURCHASE = "DELETE FROM `coupon-system-396`.`customers_coupons` WHERE (`CUSTOMER_id` = ?) and (`COUPON_id` = ?)";
    private static final String QUERY_ALL_CUSTOMERS_COUPONS = "SELECT * FROM `coupon-system-396`.customers_coupons;";

    @Override
    public boolean isExistsByEmailAndPassword(String email, String password) {
        return false;
    }

    @Override
    public void addEntity(Coupon coupon) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, coupon.getCompanyId());
        param.put(2, coupon.getCategory().ordinal() + 1);
        param.put(3, coupon.getTitle());
        param.put(4, coupon.getDescription());
        param.put(5, coupon.getStartDate());
        param.put(6, coupon.getEndDate());
        param.put(7, coupon.getAmount());
        param.put(8, coupon.getPrice());
        param.put(9, coupon.getImage());

        JDBCUtils.execute(QUERY_INSERT, param);
    }

    @Override
    public void updateEntity(Integer id, Coupon coupon) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, coupon.getCompanyId());
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

    @Override
    public void deleteEntity(Integer id) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, id);

        JDBCUtils.execute(QUERY_DELETE, param);
    }

    @Override
    public List<Coupon> findAllEntities() throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        List<?> rows = JDBCUtils.executeResults(QUERY_ALL);
        for (Object obj : rows) {
            results.add(ResultsUtils.fromHashMapToCoupon((HashMap<Integer, Object>) obj));
        }
        return results;
    }

    @Override
    public Coupon findEntityById(Integer integer) {
        return null;
    }


    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerID);
        map.put(2, couponID);

        JDBCUtils.execute(QUERY_ADD_PURCHASE, map);
    }

    @Override
    public void deleteCouponPurchase(int couponID) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, couponID);

        JDBCUtils.execute(QUERY_DELETE_PURCHASE, param);
    }

    public List<Coupon> getCompanyCoupons(int companyId) throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        List<?> rows = JDBCUtils.executeResults(QUERY_ALL_BY_COMPANYID, map);
        for (Object row : rows) {
            coupons.add(ResultsUtils.fromHashMapToCoupon((HashMap<Integer, Object>) row));
        }
        return coupons;
    }

    public boolean isCouponPurchaseExist(int couponID) throws SQLException, InterruptedException {
        boolean results = false;
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, couponID);
        List<?> rows = JDBCUtils.executeResults(QUERY_BY_ID, param);
        results = ResultsUtils.fromHashMapToBoolean((HashMap<String, Object>) rows.get(0));

        return results;
    }

    public List<Coupon> getCustomerCoupons(int customerId) throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        List<?> rows = JDBCUtils.executeResults(QUERY_ALL_CUSTOMERS_COUPONS, map);
        for (Object row : rows) {
            coupons.add(ResultsUtils.fromHashMapToCoupon((HashMap<Integer, Object>) row));
        }
        return coupons;
    }

    public List<Coupon> getExpCoupons() throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();

        List<?> rows = JDBCUtils.executeResults(QUERY_EXP_COUPONS);
        for (Object row : rows) {
            coupons.add(ResultsUtils.fromHashMapToCoupon((HashMap<Integer, Object>) row));
        }
        return coupons;
    }

}
