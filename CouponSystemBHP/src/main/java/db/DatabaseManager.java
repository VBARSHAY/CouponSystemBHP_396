package db;

import beans.Category;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;

import java.sql.SQLException;

public class DatabaseManager {

    private static final String CREATE_SCHEMA = "CREATE SCHEMA `coupon-system-396`";
    private static final String DROP_SCHEMA = "DROP SCHEMA `coupon-system-396`";
    private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `coupon-system-396`.`companies` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `email` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));";

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `coupon-system-396`.`customers` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `first_name` VARCHAR(45) NOT NULL,\n" +
            "  `last_name` VARCHAR(45) NOT NULL,\n" +
            "  `email` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));";


    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon-system-396`.`categories` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));\n";


    private static final String CREATE_TABLE_COUPONS =  "CREATE TABLE `coupon-system-396`.`coupons` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `company_id` INT NOT NULL,\n" +
            "  `category_id` INT NOT NULL,\n" +
            "  `title` VARCHAR(45) NOT NULL,\n" +
            "  `description` VARCHAR(45) NOT NULL,\n" +
            "  `start_date` DATE NOT NULL,\n" +
            "  `end_date` DATE NOT NULL,\n" +
            "  `amount` INT NOT NULL,\n" +
            "  `price` DOUBLE NOT NULL,\n" +
            "  `image` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n" +
            "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" +
            "  CONSTRAINT `company_id`\n" +
            "    FOREIGN KEY (`company_id`)\n" +
            "    REFERENCES `coupon-system-396`.`companies` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `category_id`\n" +
            "    FOREIGN KEY (`category_id`)\n" +
            "    REFERENCES `coupon-system-396`.`categories` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";


    private static final String CREATE_TABLE_CUSTOMERS_COUPONS = "CREATE TABLE `coupon-system-396`.`customers_coupons` (\n" +
            "  `CUSTOMER_id` INT NOT NULL,\n" +
            "  `COUPON_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`CUSTOMER_id`, `COUPON_id`),\n" +
            "  INDEX `COUPON_id_idx` (`COUPON_id` ASC) VISIBLE,\n" +
            "  CONSTRAINT `CUSTOMER_id`\n" +
            "    FOREIGN KEY (`CUSTOMER_id`)\n" +
            "    REFERENCES `coupon-system-396`.`customers` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `COUPON_id`\n" +
            "    FOREIGN KEY (`COUPON_id`)\n" +
            "    REFERENCES `coupon-system-396`.`coupons` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";


    public static void databaseStrategy() throws SQLException, InterruptedException  {

        CategoryDAO categoryDAO = new CategoryDAOImpl();

        try {
            JDBCUtils.execute(DROP_SCHEMA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JDBCUtils.execute(CREATE_SCHEMA);
        JDBCUtils.execute(CREATE_TABLE_CUSTOMERS);
        JDBCUtils.execute(CREATE_TABLE_COMPANIES);
        JDBCUtils.execute(CREATE_TABLE_CATEGORIES);
        JDBCUtils.execute(CREATE_TABLE_COUPONS);
        JDBCUtils.execute(CREATE_TABLE_CUSTOMERS_COUPONS);

        for (Category category: Category.values()) {
            categoryDAO.addCategory(category.name());
        }
    }
}


