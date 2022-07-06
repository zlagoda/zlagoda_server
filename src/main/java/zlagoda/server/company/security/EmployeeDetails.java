package zlagoda.server.company.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import zlagoda.server.company.entity.Employee;


public class EmployeeDetails implements UserDetails
{
	String ROLE_PREFIX = "ROLE_";
	private final Employee employee;

	public EmployeeDetails(final Employee employee)
	{
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + employee.getRole().name()));
		return list;
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

	public Employee getEmployee()
	{
		return employee;
	}
}
