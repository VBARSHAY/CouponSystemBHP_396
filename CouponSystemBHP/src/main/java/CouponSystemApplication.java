import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.*;
import db.DatabaseManager;
import db.JDBCUtils;
import facade.AdminFacade;
import facade.CompanyFacade;
import utils.Art;
import utils.PrintUtils;
import utils.TablePrinter;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CouponSystemApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("START");
        DatabaseManager.databaseStrategy();
        CompanyDao companyDao = new CompanyDaoImpl();


        System.out.println(Art.DAO_COMPANY);

        Company company1 = new Company("company1", "comp1@gmail.com", "1234");
        Company company2 = new Company("company2", "comp2@gmail.com", "1234");
        Company company3 = new Company("company3", "comp3@gmail.com", "1234");
        Company company4 = new Company("company4", "comp4@gmail.com", "1234");
        Company company5 = new Company("company5", "comp5@gmail.com", "1234");
        companyDao.addEntity(company1);
        companyDao.addEntity(company2);
        companyDao.addEntity(company3);
        companyDao.addEntity(company4);
        companyDao.addEntity(company5);


        System.out.println("@@@@@@@@@@@@ get all companies @@@@@@@@@@@");
        TablePrinter.print(companyDao.findAllEntities());

        System.out.println("@@@@@@@@@@@@ get one company @@@@@@@@@@@");
        System.out.println(companyDao.findEntityById(1));


        System.out.println("@@@@@@@@@@@@ update company @@@@@@@@@@@");
        company2.setName("Kokoriko Company");
        companyDao.updateEntity(2, company2);
        TablePrinter.print(companyDao.findAllEntities());

        System.out.println("@@@@@@@@@@@@ delete company @@@@@@@@@@@");
        companyDao.deleteEntity(2);
        TablePrinter.print(companyDao.findAllEntities());

        System.out.println("@@@@@@@@@@@@ is company ExistsByEmailAndPassword  @@@@@@@@@@@");

        System.out.println(companyDao.isExistsByEmailAndPassword("comp1@gmail.com", "1234"));//true
        System.out.println(companyDao.isExistsByEmailAndPassword("comp1@gmail.com", "12345"));//false
        System.out.println(companyDao.isExistsByEmailAndPassword("comp1@gmail.comm", "1234"));//false


        //customerDao
        CustomerDAO customerDao = new CustomerDAOImpl();
        System.out.println(Art.DAO_CUSTOMER);

        Customer customer1 = new Customer("Vladi", "Barshay", "barshay@gmail.com", "1234");
        Customer customer2 = new Customer("Gabi", "Barshay", "gabi@gmail.com", "12345");
        customerDao.addEntity(customer1);
        customerDao.addEntity(customer2);
        TablePrinter.print(customerDao.findAllEntities());

        List<Customer> customers = null;
        customers = customerDao.findAllEntities();

        System.out.println("@@@@@@@@@@@@ Get All Customers @@@@@@@@@@@");
        TablePrinter.print(customerDao.findAllEntities());

        System.out.println("@@@@@@@@@@@@ Get One Customer @@@@@@@@@@@");
        System.out.println(customerDao.findEntityById(customers.get(1).getId()));


        System.out.println("@@@@@@@@@@@@ Updating customer @@@@@@@@@@@");
        customer2.setFirstName("Kykyriku customer");
        customerDao.updateEntity(2, customer2);
        TablePrinter.print(customerDao.findAllEntities());


        System.out.println("@@@@@@@@@@@@ Delete customer @@@@@@@@@@@");
        customerDao.deleteEntity(2);
        TablePrinter.print(customerDao.findAllEntities());

        System.out.println("@@@@@@@@@@@@ Is Customer Exist By Email & Password  @@@@@@@@@@@");

        System.out.println(customerDao.isExistsByEmailAndPassword("barshay@gmail.com", "1234"));//T
        System.out.println(customerDao.isExistsByEmailAndPassword("kuku@gmail.com", "1234"));//F
        System.out.println(customerDao.isExistsByEmailAndPassword("gabi@gmail.com", "12345"));//T

        // couponDao
        CouponDAO couponDAO = new CouponDaoImpl();
        System.out.println(Art.DAO_COUPON);

        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusDays(5));
        Coupon coupon1 = new Coupon(1, Category.FOOD, "Uniliver", "Butter", startDate, endDate, 1, 3.1, "png");
        Coupon coupon2 = new Coupon(1, Category.PC, "Lenovo", "PC", startDate, endDate, 1, 1000.5, "s");
        Coupon coupon3 = new Coupon(3, Category.SPORTS, "Decathlon", "Pool accessorirs", startDate, endDate, 1, 100.8, "s");
        Coupon coupon4 = new Coupon(1, Category.FOOD, "Carefour", "Tuna", startDate, endDate, 4, 21.1, "png");
        couponDAO.addEntity(coupon1);
        couponDAO.addEntity(coupon2);
        couponDAO.addEntity(coupon3);
        couponDAO.addEntity(coupon4);

        TablePrinter.print(couponDAO.findAllEntities());

        System.out.println("@@@@@@@@@@@@ updating coupon @@@@@@@@@@@");
        coupon2.setCompanyId(5);
        couponDAO.updateEntity(2, coupon2);
        TablePrinter.print(couponDAO.findAllEntities());

        System.out.println("@@@@@@@@@@@@ delete coupon @@@@@@@@@@@");
        couponDAO.deleteEntity(2);
        TablePrinter.print(couponDAO.findAllEntities());


        // ADMIN
        AdminFacade adminFacade = new AdminFacade();
        System.out.println(Art.DAO_ADMIN);
        System.out.println("@@@@@@@@@@@@ ADMIN Login @@@@@@@@@@@");
        System.out.println(adminFacade.login("admin@admin.com", "admin"));
        System.out.println("@@@@@@@@@@@@ ADMIN Get Companies @@@@@@@@@@@");
        adminFacade.getAllCompanies().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@ ADMIN ADD Company @@@@@@@@@@@");
        Company company6 = new Company("Malam", "MalamO@gmail.com", "123456");

        adminFacade.addCompany(company6);
        adminFacade.getAllCompanies().forEach(System.out::println);
        List<Company> companies = null;
        companies = companyDao.findAllEntities();
        System.out.println("@@@@@@@@@@@@ ADMIN updating company @@@@@@@@@@@");
        companies.get(0).setEmail("office@malam.com");
        adminFacade.updateCompany(companies.get(0).getId(), companies.get(0));
        adminFacade.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@ ADMIN delete company @@@@@@@@@@@");
        adminFacade.deleteCompany(companies.get(4).getId());
        adminFacade.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@ ADMIN get one company @@@@@@@@@@@");
        System.out.println(adminFacade.getOneCompany(companies.get(0).getId()));

        System.out.println("@@@@@@@@@@@@ ADMIN ADD Customer @@@@@@@@@@@");
        Customer customer3 = new Customer("Kobi", "Banks", "kobi@gmail.com", "12345");
        Customer customer4 = new Customer("Roni", "Hermon", "roni@gmail.com", "12345");

        adminFacade.addCustomer(customer3);
        adminFacade.addCustomer(customer4);
        adminFacade.getAllCustomer().forEach(System.out::println);

        List<Customer> customers2 = null;
        customers2 = adminFacade.getAllCustomer();

        System.out.println("@@@@@@@@@@@@ ADMIN updating customer @@@@@@@@@@@");
        customers2.get(0).setFirstName("Vlad");
        try {
            adminFacade.updateCustomer(customers2.get(0).getId(), customers2.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminFacade.getAllCustomer().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@ ADMIN delete customer @@@@@@@@@@@");
        try {
            adminFacade.deleteCustomer(customers2.get(1).getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminFacade.getAllCustomer().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@ ADMIN get one customer @@@@@@@@@@@");
        System.out.println(adminFacade.getCustomer(customers2.get(2).getId()));

        // COMPANY FACADE
        CompanyFacade companyFacade = new CompanyFacade();
        System.out.println(Art.COMPANY_FACADE);
        System.out.println("@@@@@@@@@@@@ COMPANY FACADE Login @@@@@@@@@@@");

        System.out.println(companyFacade.login("kobi@gmail.com", "12345"));

        System.out.println("END");

    }


}
