package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;


public interface CustomerCardDAO
{
	List<CustomerCard> findAllCustomers();

	Optional<CustomerCard> findByNumber(final String cardNumber);

	void updateByNumber(final CustomerCard customerCard);

	void saveCustomer(final CustomerCard customerCard);

	void deleteCustomer(final String cardNumber);
}
