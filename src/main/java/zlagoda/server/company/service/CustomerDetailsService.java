package zlagoda.server.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.EmployeeDAO;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.security.EmployeeDetails;


@Service
public class CustomerDetailsService implements UserDetailsService
{
	@Autowired
	private EmployeeDAO employeeDAO;

	private static final String USER_NOT_FOUND_MSG = "Not found user with username : ";

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		final Optional<Employee> employeeOptional = employeeDAO.findByName(username);
		final Employee employee = employeeOptional.orElseThrow(
				() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG + username));
		return new EmployeeDetails(employee);
	}
}
