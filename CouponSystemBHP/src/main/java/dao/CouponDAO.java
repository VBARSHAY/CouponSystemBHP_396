package dao;

import beans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponDAO extends EntityDAO<Coupon, Integer>{

    void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException;
    void deleteCouponPurchase(int couponID) throws SQLException, InterruptedException;

    List<Coupon> getCompanyCoupons(int companyId) throws SQLException, InterruptedException;

    List<Coupon> getCustomerCoupons(int customerId) throws SQLException, InterruptedException;

    List<Coupon> getExpCoupons() throws SQLException, InterruptedException;

    boolean isCouponPurchaseExist(int couponID) throws SQLException, InterruptedException;
}
