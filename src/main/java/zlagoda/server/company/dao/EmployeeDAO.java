package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.dto.EmployeeStatisticDTO;
import zlagoda.server.company.entity.Employee;


public interface EmployeeDAO
{
	Optional<Employee> findByName(final String name);

	List<Employee> findAllEmployees();

	Optional<Employee> findById(final String employeeId);

	void updateById(final String employeeId, final Employee employee);

	void saveEmployee(final String employeeId , final Employee employee);

	void deleteEmployee(final String employeeId);

    List<EmployeeStatisticDTO> getEmployeeStats();

}
