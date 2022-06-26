package jobs;

import beans.Coupon;

import java.util.List;

public class CouponExpirationDailyJob implements Runnable{

    List<Coupon> results;
    boolean quit = false;

    public CouponExpirationDailyJob() {
    }

    @Override
    public void run() {

    }

    public void stop() {

    }


}
