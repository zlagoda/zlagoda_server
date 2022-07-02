package zlagoda.server.company.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;


@Component
public class CustomerValidator implements Validator
{

	private static final String PHONE_PATTERN = "\\+?[0-9]{5,12}";

	private static final String NUMBER = "number";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PATRONYMIC = "patronymic";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String PHONE = "phoneNumber";
	private static final String ZIP_CODE = "zipCode";

	private static final String INVALID_PHONE_CODE = "custom.invalid.phone";
	private static final String INVALID_ZIP_CODE = "custom.invalid.zip.code";
	private static final String INVALID_LENGTH  = "custom.invalid.field.length";


	@Override
	public boolean supports(final Class<?> clazz)
	{
		return CustomerCard.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		CustomerCard customerCard = (CustomerCard) target;
		validatePhone(customerCard, errors);
		validateZip(customerCard, errors);
		validateLength(customerCard , errors);
	}

	private void validatePhone(final CustomerCard customerCard, final Errors errors)
	{
		final String phone = customerCard.getPhoneNumber();
		if (!phone.matches(PHONE_PATTERN))
		{
			errors.rejectValue(PHONE, INVALID_PHONE_CODE);
		}
	}

	private void validateZip(final CustomerCard customerCard, final Errors errors)
	{
		final String zipCode = customerCard.getZipCode();
		if (zipCode.length() > 9)
		{
			errors.rejectValue(ZIP_CODE, INVALID_ZIP_CODE);
		}
	}

	private void validateLength(final CustomerCard customerCard, final Errors errors){
		if (customerCard.getName().length() > 50){
			errors.rejectValue(NAME ,INVALID_LENGTH);
		}
		if (customerCard.getSurname().length() > 50)
		{
			errors.rejectValue(SURNAME, INVALID_LENGTH);
		}
		if (customerCard.getNumber().length() > 13)
		{
			errors.rejectValue(NUMBER, INVALID_LENGTH);
		}
		if (customerCard.getPatronymic().length() > 50)
		{
			errors.rejectValue(PATRONYMIC, INVALID_LENGTH);
		}
		if (customerCard.getCity().length() > 50)
		{
			errors.rejectValue(CITY, INVALID_LENGTH);
		}
		if (customerCard.getStreet().length() > 50)
		{
			errors.rejectValue(STREET, INVALID_LENGTH);
		}
	}
}
