package zlagoda.server.company.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.dao.mapper.DefaultCustomerCardRowMapper;
import zlagoda.server.company.entity.CustomerCard;

@Repository
public class DefaultCustomerCardDAO implements CustomerCardDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer_Card;";

	@Override
	public List<CustomerCard> findAllCustomers()
	{
		RowMapper<CustomerCard> mapper = new DefaultCustomerCardRowMapper();
		return namedParameterJdbcTemplate.query(SELECT_ALL_CUSTOMERS , mapper);
	}

	@Override
	public Optional<CustomerCard> findById(final String customerId)
	{
		return Optional.empty();
	}

	@Override
	public void updateById(final String customerId, final CustomerCard customerCard)
	{

	}

	@Override
	public void saveCustomer(final String customerId, final CustomerCard customerCard)
	{

	}

	@Override
	public void deleteCustomer(final String customerId)
	{

	}
}
