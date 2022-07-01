package zlagoda.server.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.EmployeeDAO;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.service.EmployeeService;

@Service
public class DefaultEmployeeService implements EmployeeService
{
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public List<Employee> getAllEmployees()
	{
		return employeeDAO.findAllEmployees();
	}

	@Override
	public Employee getEmployeeById(final String employeeId)
	{
		return employeeDAO.findById(employeeId).orElseThrow();
	}

	@Override
	public void updateEmployeeById(String employeeId, Employee employee) {
		employeeDAO.updateById(employeeId, employee);
	}

	@Override
	public void registerEmployee(final Employee employee)
	{
		employeeDAO.saveEmployee(employee.getId(),  employee);
	}

	@Override
	public void deleteEmployee(final String employeeId)
	{
		employeeDAO.deleteEmployee(employeeId);
	}
}
