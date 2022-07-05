package zlagoda.server.company.dao.mapper;


import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.dto.CategoryDTO;
import zlagoda.server.company.dto.CustomerCardDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultCustomerCardDTOMapper implements RowMapper<CustomerCardDTO> {

    private static final String CHECK_SUM = "check_sum";
    private static final String CARD_NUMBER = "card_number";
    private static final String CUST_NAME = "cust_name";
    private static final String CUST_SURNAME = "cust_surname";

    @Override
    public CustomerCardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerCardDTO customerCardDTO = new CustomerCardDTO();
        customerCardDTO.setCheckSum(rs.getBigDecimal(CHECK_SUM));
        customerCardDTO.setCardNumber(rs.getString(CARD_NUMBER));
        customerCardDTO.setName(rs.getString(CUST_NAME));
        customerCardDTO.setSurname(rs.getString(CUST_SURNAME));
        return customerCardDTO;
    }

}
