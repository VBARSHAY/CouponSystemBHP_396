package facade;

import beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {

        return false;
    }

    public void couponPurchase(int customerId, int couponId) throws SQLException, InterruptedException {
        List<Coupon> results = couponDAO.findAllEntities();
        List<Coupon> coupons = new ArrayList<>();


        couponDAO.addCouponPurchase(customerId, couponId);

    }
}
