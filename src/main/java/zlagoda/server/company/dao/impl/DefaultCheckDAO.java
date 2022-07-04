package zlagoda.server.company.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CheckDAO;
import zlagoda.server.company.dao.mapper.DefaultCheckRowMapper;
import zlagoda.server.company.dao.mapper.DefaultProductRowMapper;
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
    private static final String SOLD_PRODUCT_SUM_BY_CASHIER = "SELECT SUM(`Check`.`sum_total`) AS check_sum, `Check`.`id_employee`\n" +
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
    public Optional<Check> getCheck(final String check_number){
        RowMapper<Check> mapper = new DefaultCheckRowMapper();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(CHECK_NUMBER, check_number);
        List<Check> checks = namedParameterJdbcTemplate.query(GET_CHECK, parameter, mapper);
        return checks.stream().findFirst();
    }

//    @Override
//    public int soldProductsSumByCashier(final String id_employee, final String print_date){
//        Map<String, Object> parameter = new HashMap<>();
//        parameter.put(ID_EMPLOYEE, id_employee);
//        parameter.put(PRINT_DATE, print_date);
//        return namedParameterJdbcTemplate.update(SOLD_PRODUCT_SUM_BY_CASHIER, parameter);
//    }
//
//    @Override
//    public int soldProductsSum(final String print_date){
//        RowMapper<Check>
//        Map<String, Object> parameter = new HashMap<>();
//        parameter.put(PRINT_DATE, print_date);
//        ResultSet rs =
//        return namedParameterJdbcTemplate.query(SOLD_PRODUCT_SUM, parameter);
//    }
//
//    @Override
//    public int soldProductAmount(final String upc, final String print_date){
//        Map<String, Object> parameter = new HashMap<>();
//        parameter.put(UPC, upc);
//        parameter.put(PRINT_DATE, print_date);
//        return namedParameterJdbcTemplate.update(SOLD_PRODUCT_AMOUNT, parameter);
//    }


}
