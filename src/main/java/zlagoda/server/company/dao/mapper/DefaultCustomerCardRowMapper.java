package zlagoda.server.company.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zlagoda.server.company.entity.CustomerCard;


public class DefaultCustomerCardRowMapper implements RowMapper<CustomerCard>
{
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
	public CustomerCard mapRow(final ResultSet rs, final int rowNum) throws SQLException
	{
		CustomerCard customerCard = new CustomerCard();
		customerCard.setNumber(rs.getString(CARD_NUMBER));
		customerCard.setName(rs.getString(CUSTOMER_NAME));
		customerCard.setSurname(rs.getString(CUSTOMER_SURNAME));
		customerCard.setPatronymic(rs.getString(CUSTOMER_PATRONYMIC));
		customerCard.setPhoneNumber(rs.getString(PHONE_NUMBER));
		customerCard.setStreet(rs.getString(STREET));
		customerCard.setCity(rs.getString(CITY));
		customerCard.setZipCode(rs.getString(ZIP_CODE));
		customerCard.setPercent(rs.getInt(PERCENT));
		return customerCard;
	}
}
