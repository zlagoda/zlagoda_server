package zlagoda.server.company.dao.mapper;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.entity.SoldProduct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;


public class DefaultCheckRowMapper implements RowMapper<Check> {

    private static final String CHECK_NUMBER = "check_number";
    private static final String ID_EMPLOYEE = "id_employee";
    private static final String CARD_NUMBER = "card_number";
    private static final String PRINT_DATE = "print_date";
    private static final String SUM_TOTAL = "sum_total";
    private static final String VAT = "vat";

    @Override
    public Check mapRow(ResultSet rs, int rowNum) throws SQLException {
        Check check = new Check();
        check.setNumber(rs.getString(CHECK_NUMBER));
        Employee employee = new Employee();
        employee.setId(rs.getString(ID_EMPLOYEE));
        check.setEmployee(employee);
        CustomerCard customer_card = new CustomerCard();
        customer_card.setNumber(CARD_NUMBER);
        check.setCard(customer_card);
        check.setPrintDate(rs.getTimestamp(PRINT_DATE));
        check.setTotalSum(rs.getBigDecimal(SUM_TOTAL));
        check.setVAT(rs.getBigDecimal(VAT));
        check.setProducts(null);
        return check;
    }
}
