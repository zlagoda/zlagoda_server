package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.EmployeeDAO;
import zlagoda.server.company.dao.mapper.DefaultEmployeeRowMapper;
import zlagoda.server.company.entity.Employee;

@Repository
public class DefaultEmployeeDAO implements EmployeeDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String ID_EMPLOYEE = "id_employee";
	private static final String SURNAME = "empl_surname";
	private static final String NAME = "empl_name";
	private static final String PASSWORD = "password";
	private static final String PATRONYMIC = "empl_patronymic";
	private static final String SALARY = "salary";
	private static final String BIRTH_DATE = "date_of_birth";
	private static final String START_DATE = "date_of_start";
	private static final String PHONE = "phone_number";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String ZIP_CODE = "zip_code";

	private static final String FIND_BY_NAME = "SELECT * FROM `Employee` WHERE empl_name = :empl_name ";
	private static final String FIND_BY_ID = "SELECT * FROM `Employee` WHERE id_employee = :id_employee ";
	private static final String FIND_ALL_EMPLOYEES = "SELECT * FROM `Employee` ";

	@Override
	public Optional<Employee> findByName(final String name)
	{
		RowMapper<Employee> mapper = new DefaultEmployeeRowMapper();
		Map<String , Object> parameter = new HashMap<>();
		parameter.put(NAME , name);
		List<Employee> employees = namedParameterJdbcTemplate.query(FIND_BY_NAME , parameter , mapper);
		return employees.stream().findFirst();
	}

	@Override
	public List<Employee> findAllEmployees()
	{
		RowMapper<Employee> mapper = new DefaultEmployeeRowMapper();
		return namedParameterJdbcTemplate.query(FIND_ALL_EMPLOYEES , mapper);
	}

	@Override
	public Optional<Employee> findById(final String employeeId)
	{
		RowMapper<Employee> mapper = new DefaultEmployeeRowMapper();
		Map<String , Object> parameter = new HashMap<>();
		parameter.put(ID_EMPLOYEE , employeeId);
		List<Employee> employees = namedParameterJdbcTemplate.query(FIND_BY_ID , parameter , mapper);
		return employees.stream().findFirst();
	}
}
