package db;


import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

import java.sql.Date;
import java.util.HashMap;

public class ResultsUtils {

    public static Company fromHashTableToCompany(HashMap<String, Object> row) {
        int id = (int) row.get("id");
        String name = (String) row.get("name");
        String email = (String) row.get("email");
        String password = (String) row.get("password");
        return new Company(id, name, email, password);
    }

    public static Customer fromHashMapToCustomer(HashMap<String, Object> row) {
        int id = (int) row.get("id");
        String firstName = (String) row.get("first_name");
        String lastName = (String) row.get("last_name");
        String email = (String) row.get("email");
        String password = (String) row.get("password");
        return new Customer(id, firstName, lastName, email, password);
    }

    public static Coupon fromHashMapToCoupon(HashMap<Integer, Object> row) {
        int id = (int) row.get("id");
        int companyId = (int) row.get("company_id");
        Category categoryId = Category.values()[(int)row.get("category_id")-1];
        String title = (String) row.get("title");
        String description = (String) row.get("description");
        Date startDate = (Date) row.get("startDate");
        Date endDate = (Date) row.get("endDate");
        int amount = (int) row.get("amount");
        Double price = (Double) row.get("price");
        String img = (String) row.get("img");
        return new Coupon(id, companyId, categoryId, title, description,startDate,endDate,amount,price,img);
    }

    public static Category fromHashMapToCategory(HashMap<String, Object> row) {
        int id = (int) row.get("id");
        String name = (String) row.get("name");
        return Category.valueOf(name);
    }

    public static boolean fromHashMapToBoolean(HashMap<String, Object> map) {
        long res = (long) map.get("res");
        return (res == 1);
    }

    public static int fromHashMapToInt(HashMap<Integer, Object> map) {
        long res = (long) map.get("res");
        return (int) res;
    }

    public static double fromHashMapToDouble(HashMap<Integer, Object> map) {
        double res = (double) map.get("res");
        return res;
    }
}
