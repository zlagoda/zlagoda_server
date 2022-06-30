package zlagoda.server.company.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import zlagoda.server.company.entity.Employee;


@Component
public class EmployeeValidator implements Validator
{
	private static final String PHONE_PATTERN = "\\+?[0-9]{1,12}";
	private static final String PHONE = "phoneNumber";

	private static final String INVALID_PHONE_CODE = "Invalid phone number";

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
	}

	private void validatePhone(final Employee employee, final Errors errors)
	{
		final String phone = employee.getPhoneNumber();
		if (!phone.matches(PHONE_PATTERN))
		{
			errors.rejectValue(PHONE, INVALID_PHONE_CODE);
		}
	}
}