package zlagoda.server.company.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import zlagoda.server.company.entity.Employee;


public class EmployeeDetails implements UserDetails
{
	private final Employee employee;

	public EmployeeDetails(final Employee employee)
	{
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return null;
	}

	@Override
	public String getPassword()
	{
		return employee.getPassword();
	}

	@Override
	public String getUsername()
	{
		return employee.getName();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
