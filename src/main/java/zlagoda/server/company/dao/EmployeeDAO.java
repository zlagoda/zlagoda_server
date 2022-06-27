package zlagoda.server.company.dao;

import java.util.Optional;

import zlagoda.server.company.entity.Employee;


public interface EmployeeDAO
{
	Optional<Employee> findByName(final String name);
}
