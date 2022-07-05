package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.dto.CustomerCardDTO;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;


public interface CustomerCardService
{
	List<CustomerCard> getAllCustomers();

	CustomerCard getCustomerByNumber(final String cardNumber);

	void updateCustomerById(CustomerCard customerCard);

	void registerCustomer(final CustomerCard customerCard);

	void deleteCustomer(final String cardNumber);

	List<CustomerCardDTO> getBoughtProductSum();
}
