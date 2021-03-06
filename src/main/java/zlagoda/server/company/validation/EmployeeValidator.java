package zlagoda.server.company.validation;

import java.sql.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import zlagoda.server.company.entity.Employee;


@Component
public class EmployeeValidator implements Validator
{
	private static final String PHONE_PATTERN = "\\+?[0-9]{5,12}";

	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PHONE = "phoneNumber";
	private static final String PATRONYMIC = "patronymic";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String ZIP_CODE = "zipCode";
	private static final String PASSWORD = "password";

	private static final String BIRTH_DATE = "birthdate";
	private static final String START_DATE = "startDate";

	private static final String INVALID_PHONE_CODE = "custom.invalid.phone";
	private static final String INVALID_ZIP_CODE = "custom.invalid.zip.code";
	private static final String INVALID_BIRTH_DATE = "custom.invalid.birth.date";
	private static final String INVALID_START_DATE  = "custom.invalid.start.date";
	private static final String INVALID_LENGTH  = "custom.invalid.field.length";

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		Employee employee = (Employee) target;
		validatePhone(employee, errors);
		validateZip(employee , errors);
		validateBirthDate(employee , errors);
		validateStartDate(employee , errors);
		validateLength(employee , errors);
	}

	private void validatePhone(final Employee employee, final Errors errors)
	{
		final String phone = employee.getPhoneNumber();
		if (!phone.matches(PHONE_PATTERN))
		{
			errors.rejectValue(PHONE, INVALID_PHONE_CODE);
		}
	}

	private void validateZip(final Employee employee, final Errors errors)
	{
		final String zipCode = employee.getZipCode();
		if (zipCode.length() > 9)
		{
			errors.rejectValue(ZIP_CODE, INVALID_ZIP_CODE);
		}
	}

	private void validateBirthDate(final Employee employee, final Errors errors)
	{
		final Date birthDate = employee.getBirthdate();
		if (birthDate == null)
		{
			errors.rejectValue(BIRTH_DATE, INVALID_BIRTH_DATE);
		}
	}

	private void validateStartDate(final Employee employee, final Errors errors)
	{
		final Date startDate = employee.getStartDate();
		if (startDate == null)
		{
			errors.rejectValue(START_DATE, INVALID_START_DATE);
		}
	}

	private void validateLength(final Employee employee, final Errors errors){
		if (employee.getName().length() > 50){
			errors.rejectValue(NAME ,INVALID_LENGTH);
		}
		if (employee.getSurname().length() > 50)
		{
			errors.rejectValue(SURNAME, INVALID_LENGTH);
		}
		if (employee.getPassword().length() > 72)
		{
			errors.rejectValue(PASSWORD, INVALID_LENGTH);
		}
		if (employee.getPatronymic().length() > 50)
		{
			errors.rejectValue(PATRONYMIC, INVALID_LENGTH);
		}
		if (employee.getCity().length() > 50)
		{
			errors.rejectValue(CITY, INVALID_LENGTH);
		}
		if (employee.getStreet().length() > 50)
		{
			errors.rejectValue(STREET, INVALID_LENGTH);
		}
	}
}
