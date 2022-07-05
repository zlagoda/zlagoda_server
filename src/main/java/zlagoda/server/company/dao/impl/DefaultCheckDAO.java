package zlagoda.server.company.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CheckDAO;
import zlagoda.server.company.dao.mapper.DefaultCheckRowMapper;
import zlagoda.server.company.dao.mapper.DefaultProductRowMapper;
import zlagoda.server.company.dao.mapper.DefaultSoldProductRowMapper;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.entity.SoldProduct;

@Repository
public class DefaultCheckDAO implements CheckDAO {

        @Autowired
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        private static final String CHECK_NUMBER = "check_number";
        private static final String ID_EMPLOYEE = "id_employee";
        private static final String CARD_NUMBER = "card_number";
        private static final String PRINT_DATE = "print_date";
        private static final String SUM_TOTAL = "sum_total";
        private static final String VAT = "vat";
        private static final String UPC = "UPC";
        private static final String PRODUCT_NUMBER = "product_number";
        private static final String SELLING_PRICE = "selling_price";

        private static final String INSERT_NEW_CHECK = "INSERT INTO `Check` (" +
                        "check_number, id_employee, card_number, print_date, sum_total, vat) VALUES (" +
                        ":check_number, :id_employee, :card_number, :print_date, :sum_total, :vat)";

        private static final String INSERT_NEW_SALE_TO_CHECK = "INSERT INTO `Sale` (" +
                        "UPC, check_number, product_number, selling_price) VALUES (" +
                        ":UPC, :check_number, :product_number, :selling_price)";

        private static final String GET_CHECK = "SELECT" +
                        "*" +
                        "FROM" +
                        "    `Check`" +
                        "WHERE" +
                        "    `Check`.`check_number` = :check_number";

        private static final String GET_CHECKS_FOR_A_PERIOD_BY_CASHIER = "SELECT\n" +
                        "    `Check`.`card_number`,\n" +
                        "    `Check`.`check_number`,\n" +
                        "    `Check`.`id_employee`,\n" +
                        "    `Check`.`print_date`,\n" +
                        "    `Check`.`sum_total`,\n" +
                        "    `Check`.`vat`" +
                        "FROM\n" +
                        "    `Check`\n" +
                        "        INNER JOIN Employee ON Employee.id_employee = `Check`.id_employee\n" +
                        "WHERE\n" +
                        "        `Check`.id_employee = :id_employee AND Employee.role = 'cashier' AND `Check`.print_date >= :print_date";
        private static final String GET_CHECKS_FOR_A_PERIOD = "SELECT\n" +
                        "    `Check`.`card_number`,\n" +
                        "    `Check`.`check_number`,\n" +
                        "    `Check`.`id_employee`,\n" +
                        "    `Check`.`print_date`,\n" +
                        "    `Check`.`sum_total`,\n" +
                        "    `Check`.`vat`" +
                        "FROM\n" +
                        "    `Check`\n" +
                        "        INNER JOIN Employee ON Employee.id_employee = `Check`.id_employee\n" +
                        "WHERE\n" +
                        "         Employee.role = 'cashier' AND `Check`.print_date >= :print_date";
        private static final String SOLD_PRODUCT_IN_CATEGORY = "SELECT" +
                        "    SUM(Sale.product_nubmer) AS total_amount," +
                        "    SUM(Sale.selling_price) AS total_price," +
                        "    Category.category_name" +
                        "FROM" +
                        "    Product" +
                        "INNER JOIN Store_Product ON Store_Product.id_product = Product.id_product" +
                        "INNER JOIN Sale ON Sale.UPC = Store_Product.UPC" +
                        "INNER JOIN Check ON Check.check_number = Sale.check_number" +
                        "INNER JOIN Category ON Category.category_number = Product.category_number" +
                        "WHERE" +
                        "    Check.print_date >= :print_date" +
                        "GROUP BY" +
                        "    Product.category_number";
        private static final String SOLD_PRODUCT_SUM_BY_CASHIER = "SELECT SUM(`Check`.`sum_total`) AS check_sum, `Check`.`id_employee`\n"
                        +
                        "FROM `Check`\n" +
                        "         INNER JOIN Employee ON Employee.id_employee = `Check`.id_employee\n" +
                        "         INNER JOIN Sale ON Sale.check_number = `Check`.`check_number`\n" +
                        "WHERE `Check`.id_employee = :id_employee AND Employee.role = 'cashier' AND `Check`.`print_date` >= :print_date";
        private static final String SOLD_PRODUCT_SUM = "SELECT \n" +
                        "\tSUM(`Check`.`sum_total`) AS total_check_sum\n" +
                        "FROM `Check`\n" +
                        "\tINNER JOIN Employee ON Employee.id_employee = `Check`.`id_employee`\n" +
                        "WHERE Employee.role LIKE 'cashier' AND `Check`.`print_date` >= :print_date";
        private static final String SOLD_PRODUCT_AMOUNT = "SELECT SUM(Sale.product_number) AS sold\n" +
                        "\n" +
                        "FROM Sale\n" +
                        "         INNER JOIN `Check` ON `Check`.`check_number` = Sale.check_number\n" +
                        "WHERE Sale.UPC LIKE :UPC AND`Check`.`print_date` >= :print_date";

        private static final String GET_CHECKS = "SELECT * FROM `Check` ;";
        private static final String GET_CHECK_BY_NUMBER = "SELECT *" +
                        "FROM `Check`" +
                        "WHERE `Check`.`check_number` = :check_number";

        private static final String GET_ALL_CHECKS_INFO = "SELECT * FROM `Check` ORDER BY print_date DESC";
        private static final String GET_PRODUCTS_FROM_CHECK = "SELECT Sale.UPC AS product_UPC," +
                        "Product.product_name," +
                        "Sale.product_number," +
                        "Sale.selling_price\n" +
                        "FROM Sale\n" +
                        "INNER JOIN Store_Product ON Sale.UPC = Store_Product.UPC\n" +
                        "INNER JOIN Product ON Store_Product.id_product = Product.id_product\n" +
                        "WHERE Sale.check_number = :check_number";
        private static final String GET_CHECKS_CONSIST_CATEGORIES = "SELECT *\n" +
                        "FROM `Check` AS ch1\n" +
                        "WHERE NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Category AS c1\n" +
                        "WHERE c1.category_number IN (:ids) AND\n" +
                        "NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Sale\n" +
                        "INNER JOIN Store_Product ON Store_Product.UPC = Sale.UPC\n" +
                        "INNER JOIN Product ON Product.id_product = Store_Product.id_product\n" +
                        "INNER JOIN Category AS c2 ON c2.category_number = Product.category_number\n" +
                        "WHERE c1.category_number = c2.category_number AND ch1.check_number = Sale.check_number))";
        private static final String GET_CHECKS_CONSIST_ONLY_CATEGORIES = "SELECT *\n" +
                        "FROM `Check` AS ch1\n" +
                        "WHERE NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Category AS c1\n" +
                        "WHERE c1.category_number IN (:ids) AND\n" +
                        "NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Sale\n" +
                        "INNER JOIN Store_Product ON Store_Product.UPC = Sale.UPC\n" +
                        "INNER JOIN Product ON Product.id_product = Store_Product.id_product\n" +
                        "INNER JOIN Category AS c2 ON c2.category_number = Product.category_number\n" +
                        "WHERE c1.category_number = c2.category_number AND ch1.check_number = Sale.check_number))\n" +
                        "AND ch1.check_number NOT IN (\n" +
                        "SELECT Sale.check_number\n" +
                        "FROM Sale\n" +
                        "INNER JOIN Store_Product ON Store_Product.UPC = Sale.UPC\n" +
                        "INNER JOIN Product ON Product.id_product = Store_Product.id_product\n" +
                        "INNER JOIN Category ON Category.category_number = Product.category_number\n" +
                        "WHERE Category.category_number NOT IN (:ids))";
        private static final String GET_CHECKS_CONSIST_PRODUCTS = "SELECT *\n" +
                        "FROM `Check` AS ch1\n" +
                        "WHERE NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Product AS p1\n" +
                        "WHERE p1.id_product IN (:ids) AND\n" +
                        "NOT EXISTS(\n" +
                        "SELECT *\n" +
                        "FROM Sale\n" +
                        "INNER JOIN Store_Product ON Store_Product.UPC = Sale.UPC\n" +
                        "INNER JOIN Product AS p2 ON p2.id_product = Store_Product.id_product\n" +
                        "WHERE p1.id_product = p2.id_product AND ch1.check_number = Sale.check_number))";
        private static final String GET_CHECKS_BY_EMPLOYEE = "SELECT * FROM `Check` WHERE id_employee = :id_employee";

        @Override
        public void insertNewCheck(final Check check) {
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(CHECK_NUMBER, check.getNumber());
                parameter.put(ID_EMPLOYEE, check.getEmployee().getId());
                parameter.put(CARD_NUMBER, check.getCard() == null ? null : check.getCard().getNumber());
                parameter.put(PRINT_DATE, check.getPrintDate());
                parameter.put(SUM_TOTAL, check.getTotalSum());
                parameter.put(VAT, check.getVAT());
                namedParameterJdbcTemplate.update(INSERT_NEW_CHECK, parameter);
        }

        @Override
        public void insertNewSale(final String checkNumber, final SoldProduct soldProduct) {
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(CHECK_NUMBER, checkNumber);
                parameter.put(UPC, soldProduct.getUPC());
                parameter.put(PRODUCT_NUMBER, soldProduct.getAmount());
                parameter.put(SELLING_PRICE, soldProduct.getPrice());
                namedParameterJdbcTemplate.update(INSERT_NEW_SALE_TO_CHECK, parameter);
        }

        @Override
        public List<Check> getChecksForPeriod(final String print_date) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(PRINT_DATE, print_date);
                return namedParameterJdbcTemplate.query(GET_CHECKS_FOR_A_PERIOD, parameter, mapper);
        }

        @Override
        public List<Check> getChecksForPeriodByCashier(final String id_employee, final String print_date) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(ID_EMPLOYEE, id_employee);
                parameter.put(PRINT_DATE, print_date);
                return namedParameterJdbcTemplate.query(GET_CHECKS_FOR_A_PERIOD_BY_CASHIER, parameter, mapper);
        }

        @Override
        public List<Check> getAllChecksInfo() {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                return namedParameterJdbcTemplate.query(GET_ALL_CHECKS_INFO, mapper);
        }

        @Override
        public List<SoldProduct> getProductsInCheck(String checkNumber) {
                RowMapper<SoldProduct> mapper = new DefaultSoldProductRowMapper();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(CHECK_NUMBER, checkNumber);
                return namedParameterJdbcTemplate.query(GET_PRODUCTS_FROM_CHECK, parameter, mapper);
        }

        @Override
        public Optional<Check> getCheckByNumber(final String checkNumber) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(CHECK_NUMBER, checkNumber);
                List<Check> checks = namedParameterJdbcTemplate.query(GET_CHECK_BY_NUMBER, parameter, mapper);
                return checks.stream().findFirst();
        }

        @Override
        public List<Check> getChecksConsistCategory(List<Integer> categoryIds) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                SqlParameterSource parameters = new MapSqlParameterSource("ids", categoryIds);
                return namedParameterJdbcTemplate.query(GET_CHECKS_CONSIST_CATEGORIES, parameters, mapper);
        }

        @Override
        public List<Check> getChecksConsistOnlyCategory(List<Integer> categoryIds) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                SqlParameterSource parameters = new MapSqlParameterSource("ids", categoryIds);
                return namedParameterJdbcTemplate.query(GET_CHECKS_CONSIST_ONLY_CATEGORIES, parameters, mapper);
        }

        @Override
        public List<Check> getChecksConsistProduct(List<Integer> productIds) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                SqlParameterSource parameters = new MapSqlParameterSource("ids", productIds);
                return namedParameterJdbcTemplate.query(GET_CHECKS_CONSIST_PRODUCTS, parameters, mapper);
        }

        @Override
        public List<Check> getChecksByEmployee(String employeeNumber) {
                RowMapper<Check> mapper = new DefaultCheckRowMapper();
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(ID_EMPLOYEE, employeeNumber);
                return namedParameterJdbcTemplate.query(GET_CHECKS_BY_EMPLOYEE, parameter, mapper);
        }
}
