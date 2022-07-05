package zlagoda.server.company.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.dto.CustomerCardDTO;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;
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
	public CustomerCard getCustomerByNumber(final String cardNumber)
	{
		return customerCardDAO.findByNumber(cardNumber).orElseThrow();
	}

	@Override
	public void updateCustomerById(final CustomerCard customerCard)
	{
		CustomerCard oldCustomerCard = customerCardDAO.findByNumber(customerCard.getNumber()).orElseThrow();
		oldCustomerCard = setOnlyPresentFields(oldCustomerCard , customerCard);
		customerCardDAO.updateByNumber(oldCustomerCard);
	}

	@Override
	public void registerCustomer(final CustomerCard customerCard)
	{
		customerCardDAO.saveCustomer(customerCard);
	}

	@Override
	public void deleteCustomer(final String cardNumber)
	{
		customerCardDAO.deleteCustomer(cardNumber);
	}

	@Override
	public List<CustomerCardDTO> getBoughtProductSum() {
		return customerCardDAO.getBoughtProductSum();
	}

	private CustomerCard setOnlyPresentFields(final CustomerCard oldCustomerCard , final CustomerCard newCustomerCard)
	{
		String name = newCustomerCard.getName();
		String surname = newCustomerCard.getSurname();
		String patronymic = newCustomerCard.getPatronymic();
		Integer percent = newCustomerCard.getPercent();
		String phoneNumber = newCustomerCard.getPhoneNumber();
		String city = newCustomerCard.getCity();
		String street = newCustomerCard.getStreet();
		String zipCode = newCustomerCard.getZipCode();

		if (name.length()>0)
		{
			oldCustomerCard.setName(name);
		}
		if (surname.length()>0)
		{
			oldCustomerCard.setSurname(surname);
		}
		if (patronymic.length()>0)
		{
			oldCustomerCard.setPatronymic(patronymic);
		}
		if (percent > 0)
		{
			oldCustomerCard.setPercent(percent);
		}
		if (phoneNumber.length() > 0)
		{
			oldCustomerCard.setPhoneNumber(phoneNumber);
		}
		if (city.length() > 0)
		{
			oldCustomerCard.setCity(city);
		}
		if (street.length() > 0)
		{
			oldCustomerCard.setStreet(street);
		}
		if (zipCode.length()>0)
		{
			oldCustomerCard.setZipCode(zipCode);
		}
		return oldCustomerCard;
	}
}
