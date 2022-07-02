package zlagoda.server.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.service.CustomerCardService;

@Service
public class DefaultCustomerCardService implements CustomerCardService
{
	@Autowired
	private CustomerCardDAO customerCardDAO;

	@Override
	public List<CustomerCard> getAllCustomers()
	{
		return customerCardDAO.findAllCustomers();
	}

	@Override
	public CustomerCard getCustomerById(final String customerId)
	{
		return null;
	}

	@Override
	public void updateCustomerById(final CustomerCard customerCard)
	{

	}

	@Override
	public void registerCustomer(final CustomerCard customerCard)
	{

	}

	@Override
	public void deleteCustomer(final String customerId)
	{

	}
}
