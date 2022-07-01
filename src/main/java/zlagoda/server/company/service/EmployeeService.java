package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.entity.Employee;


public interface EmployeeService
{
	List<Employee> getAllEmployees();

	Employee getEmployeeById(final String employeeId);

	void updateEmployeeById(final String employeeId, Employee employee);
}
