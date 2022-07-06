package zlagoda.server.company.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.springframework.jdbc.core.RowMapper;

import zlagoda.server.company.entity.Employee;


public class DefaultEmployeeRowMapper implements RowMapper<Employee>
{
	private static final String ID_EMPLOYEE = "id_employee";
	private static final String SURNAME = "empl_surname";
	private static final String NAME = "empl_name";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String PATRONYMIC = "empl_patronymic";
	private static final String SALARY = "salary";
	private static final String BIRTH_DATE = "date_of_birth";
	private static final String START_DATE = "date_of_start";
	private static final String PHONE = "phone_number";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String ZIP_CODE = "zip_code";

	@Override
	public Employee mapRow(final ResultSet rs, final int rowNum) throws SQLException
	{
		Employee employee = new Employee();
		employee.setId(rs.getString(ID_EMPLOYEE));
		employee.setName(rs.getString(NAME));
		employee.setPassword(rs.getString(PASSWORD));
		employee.setRole(Employee.Role.valueOf(rs.getString(ROLE).toUpperCase(Locale.ROOT)));
		employee.setSurname(rs.getString(SURNAME));
		employee.setPatronymic(rs.getString(PATRONYMIC));
		employee.setBirthdate(rs.getDate(BIRTH_DATE));
		employee.setPhoneNumber(rs.getString(PHONE));
		employee.setSalary(rs.getBigDecimal(SALARY));
		employee.setStartDate(rs.getDate(START_DATE));
		employee.setStreet(rs.getString(STREET));
		employee.setCity(rs.getString(CITY));
		employee.setZipCode(rs.getString(ZIP_CODE));
		return employee;
	}
}
