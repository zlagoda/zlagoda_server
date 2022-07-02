package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.entity.Employee;


public interface EmployeeService
{
	List<Employee> getAllEmployees();

	Employee getEmployeeById(final String employeeId);

	void updateEmployeeById(Employee employee);

	void registerEmployee(final Employee employee);

	void deleteEmployee(final String employeeId);
}
