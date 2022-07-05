package zlagoda.server.company.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.EmployeeDAO;
import zlagoda.server.company.dto.EmployeeStatisticDTO;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.security.EmployeeDetails;
import zlagoda.server.company.service.EmployeeService;

@Service
public class DefaultEmployeeService implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAllEmployees();
	}

	@Override
	public Employee getEmployeeById(final String employeeId) {
		return employeeDAO.findById(employeeId).orElseThrow();
	}

	@Override
	public void updateEmployeeById(Employee employee) {
		Employee oldEmployee = employeeDAO.findById(employee.getId()).orElseThrow();
		oldEmployee = setOnlyPresentFields(oldEmployee, employee);
		employeeDAO.updateById(oldEmployee.getId(), oldEmployee);
	}

	@Override
	public void registerEmployee(final Employee employee) {
		employeeDAO.saveEmployee(employee.getId(), employee);
	}

	@Override
	public void deleteEmployee(final String employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

	private Employee setOnlyPresentFields(final Employee oldEmployee, final Employee newEmployee) {
		String name = newEmployee.getName();
		String surname = newEmployee.getSurname();
		String password = newEmployee.getPassword();
		String patronymic = newEmployee.getPatronymic();
		Employee.Role role = newEmployee.getRole();
		BigDecimal salary = newEmployee.getSalary();
		Date birthdate = newEmployee.getBirthdate();
		Date startDate = newEmployee.getStartDate();
		String phoneNumber = newEmployee.getPhoneNumber();
		String city = newEmployee.getCity();
		String street = newEmployee.getStreet();
		String zipCode = newEmployee.getZipCode();

		if (name.length() > 0) {
			oldEmployee.setName(name);
		}
		if (surname.length() > 0) {
			oldEmployee.setSurname(surname);
		}
		if (password.length() > 0) {
			oldEmployee.setPassword(password);
		}
		if (patronymic.length() > 0) {
			oldEmployee.setPatronymic(patronymic);
		}
		if (role != null) {
			oldEmployee.setRole(role);
		}
		if (salary != null) {
			oldEmployee.setSalary(salary);
		}
		if (birthdate != null) {
			oldEmployee.setBirthdate(birthdate);
		}
		if (startDate != null) {
			oldEmployee.setStartDate(startDate);
		}
		if (phoneNumber.length() > 0) {
			oldEmployee.setPhoneNumber(phoneNumber);
		}
		if (city.length() > 0) {
			oldEmployee.setCity(city);
		}
		if (street.length() > 0) {
			oldEmployee.setStreet(street);
		}
		if (zipCode.length() > 0) {
			oldEmployee.setZipCode(zipCode);
		}
		return oldEmployee;
	}

	@Override
	public Employee getCurrent() {
		EmployeeDetails employeeDetails = (EmployeeDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Employee employee = employeeDetails.getEmployee();
		return employee;
	}

	@Override
	public List<EmployeeStatisticDTO> getEmployeeStats() {
		return employeeDAO.getEmployeeStats();
	}
}
