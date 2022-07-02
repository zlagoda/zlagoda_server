package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;


public interface CustomerCardService
{
	List<CustomerCard> getAllCustomers();

	CustomerCard getCustomerById(final String customerId);

	void updateCustomerById(CustomerCard customerCard);

	void registerCustomer(final CustomerCard customerCard);

	void deleteCustomer(final String customerId);
}
