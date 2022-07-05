package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.dao.mapper.DefaultCustomerCardDTOMapper;
import zlagoda.server.company.dao.mapper.DefaultCustomerCardRowMapper;
import zlagoda.server.company.dto.CustomerCardDTO;
import zlagoda.server.company.entity.CustomerCard;


@Repository
public class DefaultCustomerCardDAO implements CustomerCardDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String CARD_NUMBER = "card_number";
	private static final String CUSTOMER_SURNAME = "cust_surname";
	private static final String CUSTOMER_NAME = "cust_name";
	private static final String CUSTOMER_PATRONYMIC = "cust_patronymic";
	private static final String PHONE_NUMBER = "phone_number";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String ZIP_CODE = "zip_code";
	private static final String PERCENT = "percent";

	private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer_Card ORDER BY cust_surname";
	private static final String INSERT_NEW_CUSTOMER_CARD = "INSERT INTO Customer_Card "
			+ "(card_number ,cust_surname,cust_name,cust_patronymic, phone_number, city,street, zip_code,percent)"
			+ "VALUES ( :card_number , :cust_surname ,:cust_name, :cust_patronymic, :phone_number, "
			+ ":city , :street , :zip_code , :percent)";

	private static final String DELETE_CUSTOMER_CARD = "DELETE FROM Customer_Card "
			+ "WHERE card_number = :card_number ";
	private static final String SELECT_BY_NUMBER = "SELECT * "
			+ "FROM Customer_Card "
			+ "WHERE card_number = :card_number ";

	private static final String UPDATE_BY_NUMBER = "UPDATE Customer_Card "
			+ "SET cust_surname = :cust_surname ,"
			+ "    cust_name = :cust_name , "
			+ "    cust_patronymic = :cust_patronymic,"
			+ "    phone_number = :phone_number ,"
			+ "    city = :city,"
			+ "    street = :street, "
			+ "    zip_code = :zip_code ,"
			+ "    percent = :percent "
			+ "WHERE card_number = :card_number;";

	private static final String BOUGHT_PRODUCTS_SUM = "SELECT " +
			"    SUM(`Check`.sum_total) AS check_sum, " +
			"    `Check`.card_number, " +
			"    Customer_Card.cust_name, " +
			"    Customer_Card.cust_surname , Customer_Card.card_number , Customer_Card.cust_surname , Customer_Card.cust_patronymic , "
			+ " Customer_Card.phone_number , Customer_Card.city , Customer_Card.street  , Customer_Card.zip_code , Customer_Card.percent "
			+" FROM " +
			" `Check` " +
			"INNER JOIN Customer_Card ON Customer_Card.card_number = `Check`.card_number " +
			"GROUP BY " +
			"    (Customer_Card.card_number) ";

	@Override
	public List<CustomerCard> findAllCustomers()
	{
		RowMapper<CustomerCard> mapper = new DefaultCustomerCardRowMapper();
		return namedParameterJdbcTemplate.query(SELECT_ALL_CUSTOMERS, mapper);
	}

	@Override
	public Optional<CustomerCard> findByNumber(final String cardNumber)
	{
		RowMapper<CustomerCard> mapper = new DefaultCustomerCardRowMapper();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put(CARD_NUMBER, cardNumber);
		List<CustomerCard> cards = namedParameterJdbcTemplate.query(SELECT_BY_NUMBER, parameter, mapper);
		return cards.stream().findFirst();
	}

	@Override
	public void updateByNumber(final CustomerCard customerCard)
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(CARD_NUMBER, customerCard.getNumber());
		parameters.put(CUSTOMER_NAME, customerCard.getName());
		parameters.put(CUSTOMER_SURNAME, customerCard.getSurname());
		parameters.put(CUSTOMER_PATRONYMIC, customerCard.getPatronymic());
		parameters.put(PHONE_NUMBER, customerCard.getPhoneNumber());
		parameters.put(CITY, customerCard.getCity());
		parameters.put(STREET, customerCard.getStreet());
		parameters.put(ZIP_CODE, customerCard.getZipCode());
		parameters.put(PERCENT, customerCard.getPercent());
		namedParameterJdbcTemplate.update(UPDATE_BY_NUMBER, parameters);
	}

	@Override
	public void saveCustomer(final CustomerCard customerCard)
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(CARD_NUMBER, customerCard.getNumber());
		parameters.put(CUSTOMER_NAME, customerCard.getName());
		parameters.put(CUSTOMER_SURNAME, customerCard.getSurname());
		parameters.put(CUSTOMER_PATRONYMIC, customerCard.getPatronymic());
		parameters.put(PHONE_NUMBER, customerCard.getPhoneNumber());
		parameters.put(CITY, customerCard.getCity());
		parameters.put(STREET, customerCard.getStreet());
		parameters.put(ZIP_CODE, customerCard.getZipCode());
		parameters.put(PERCENT, customerCard.getPercent());
		namedParameterJdbcTemplate.update(INSERT_NEW_CUSTOMER_CARD, parameters);
	}

	@Override
	public void deleteCustomer(final String cardNumber)
	{
		Map<String, Object> parameter = new HashMap<>();
		parameter.put(CARD_NUMBER, cardNumber);
		namedParameterJdbcTemplate.update(DELETE_CUSTOMER_CARD, parameter);
	}

	@Override
	public List<CustomerCardDTO> getBoughtProductSum()
	{
		RowMapper<CustomerCardDTO> mapper = new DefaultCustomerCardDTOMapper();
		List<CustomerCardDTO> result = namedParameterJdbcTemplate.query(BOUGHT_PRODUCTS_SUM, mapper);
		return result;
	}
}
