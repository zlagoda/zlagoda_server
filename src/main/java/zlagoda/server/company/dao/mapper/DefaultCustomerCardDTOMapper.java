package zlagoda.server.company.dao.mapper;


import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.dto.CategoryDTO;
import zlagoda.server.company.dto.CustomerCardDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultCustomerCardDTOMapper implements RowMapper<CustomerCardDTO> {

    private static final String CHECK_SUM = "check_sum";
    private static final String CARD_NUMBER = "card_number";
	private static final String CUSTOMER_SURNAME = "cust_surname";
	private static final String CUSTOMER_NAME = "cust_name";
	private static final String CUSTOMER_PATRONYMIC = "cust_patronymic";
	private static final String PHONE_NUMBER = "phone_number";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String ZIP_CODE = "zip_code";
	private static final String PERCENT = "percent";

    @Override
    public CustomerCardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerCardDTO customerCardDTO = new CustomerCardDTO();
        customerCardDTO.setCheckSum(rs.getBigDecimal(CHECK_SUM));
        customerCardDTO.setCardNumber(rs.getString(CARD_NUMBER));
		customerCardDTO.setName(rs.getString(CUSTOMER_NAME));
		customerCardDTO.setSurname(rs.getString(CUSTOMER_SURNAME));
		customerCardDTO.setPatronymic(rs.getString(CUSTOMER_PATRONYMIC));
		customerCardDTO.setPhoneNumber(rs.getString(PHONE_NUMBER));
		customerCardDTO.setStreet(rs.getString(STREET));
		customerCardDTO.setCity(rs.getString(CITY));
		customerCardDTO.setZipCode(rs.getString(ZIP_CODE));
		customerCardDTO.setPercent(rs.getInt(PERCENT));
        return customerCardDTO;
    }

}
