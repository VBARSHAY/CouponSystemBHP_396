package facade;

import dao.*;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CompanyDao companyDao = new CompanyDaoImpl();
    protected CustomerDAO customerDAO = new CustomerDAOImpl();
    protected CouponDAO couponDAO = new CouponDaoImpl();

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;
}
