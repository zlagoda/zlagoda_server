package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CheckDAO;
import zlagoda.server.company.entity.Check;
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
    
}
