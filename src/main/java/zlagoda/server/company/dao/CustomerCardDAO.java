package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;


public interface CustomerCardDAO
{
	List<CustomerCard> findAllCustomers();

	Optional<CustomerCard> findById(final String customerId);

	void updateById(final String customerId, final CustomerCard customerCard);

	void saveCustomer(final String customerId , final CustomerCard customerCard);

	void deleteCustomer(final String customerId);
}
