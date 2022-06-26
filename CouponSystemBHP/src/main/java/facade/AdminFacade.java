package facade;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import db.JDBCUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminFacade extends ClientFacade {

    private static final String QUERY_UPDATE = "UPDATE `coupon-system-396`.`companies` SET`email` = ?, `password` = ? WHERE (`id` = ?);\n";

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (email == "admin@admin.com" && password == "admin") {
            return true;
        }
        return false;
    }

    public void addCompany(Company company) throws Exception {
        if (companyDao.isExistsByEmailAndPassword(company.getEmail(), company.getPassword()) &&
                companyDao.isExistsByName(company.getName())) {
            throw new Exception("company exists");
        }
        companyDao.addEntity(company);
    }

    public void updateCompany(Integer id, Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, company.getEmail());
        param.put(2, company.getPassword());
        param.put(3, id);

        JDBCUtils.execute(QUERY_UPDATE, param);
    }

    public void deleteCompany(int companyId) throws SQLException, InterruptedException {
        List<Coupon> coupons = this.couponDAO.getCompanyCoupons(companyId);
        for (Coupon coupon : coupons) {
            if (this.couponDAO.isCouponPurchaseExist(coupon.getId())) {
                this.couponDAO.deleteCouponPurchase(coupon.getId());
            }
            this.couponDAO.deleteEntity(coupon.getId());
        }

        this.companyDao.deleteEntity(companyId);
    }

    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
        return companyDao.findAllEntities();
    }

    public Company getOneCompany(int companyId) throws SQLException, InterruptedException {
        return companyDao.findEntityById(companyId);
    }


    public void addCustomer(Customer customer) throws Exception {
        if (customerDAO.isExistsByEmail(customer.getEmail())) {
            throw new Exception("customer exists");
        }
        customerDAO.addEntity(customer);
    }

    public void updateCustomer(Integer id, Customer customer) throws Exception {
        if (customerDAO.isExistsByEmailAndPassword(customer.getEmail(), customer.getPassword())) {
            customerDAO.updateEntity(id, customer);
        }
        throw new Exception("customer not exists");
    }

    public void deleteCustomer(int customerId) throws SQLException, InterruptedException {
        List<Coupon> coupons = this.couponDAO.getCustomerCoupons(customerId);
        for (Coupon coupon : coupons) {
            if (this.couponDAO.isCouponPurchaseExist(coupon.getId())) {
                this.couponDAO.deleteCouponPurchase(coupon.getId());
            }
            this.couponDAO.deleteEntity(coupon.getId());
        }

        this.customerDAO.deleteEntity(customerId);
    }


    public List<Customer> getAllCustomer() throws SQLException, InterruptedException {
        return customerDAO.findAllEntities();
    }


    public Customer getCustomer(int customerId) throws SQLException, InterruptedException {
        return customerDAO.findEntityById(customerId);
    }

}
