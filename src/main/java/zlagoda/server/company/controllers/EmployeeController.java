package zlagoda.server.company.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.service.EmployeeService;
import zlagoda.server.company.validation.EmployeeValidator;


@Controller
public class EmployeeController
{
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeValidator employeeValidator;

	@GetMapping("/employees")
	public String employees(Model model)
	{
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

	@GetMapping("/employee/{employeeId}")
	public String employee(@PathVariable("employeeId") String employeeId, Model model)
	{
		Employee employee = employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		model.addAttribute("roles", Employee.Role.values());
		return "editEmployee";
	}

	@PostMapping("/edit/{employeeId}")
	public String employeeEdit(@ModelAttribute("employee") Employee employee, BindingResult result, Model model)
	{
		employeeValidator.validate(employee, result);
		if (result.hasErrors())
		{
			model.addAttribute("roles", Employee.Role.values());
			return "editEmployee";
		}
		employeeService.updateEmployeeById(employee);
		return "redirect:/employees";
	}

	@GetMapping("employee/add")
	public String employeeAdd(Model model)
	{
		model.addAttribute("roles", Employee.Role.values());
		model.addAttribute("employee", new Employee());
		return "addEmployee";
	}

	@PostMapping("employee/add")
	public String employeeAddPost(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			Model model)
	{
		employeeValidator.validate(employee, result);
		if (result.hasErrors())
		{
			model.addAttribute("roles", Employee.Role.values());
			return "addEmployee";
		}
		employeeService.registerEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/delete/{employeeId}")
	public String employeeDelete(@PathVariable("employeeId") String employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		return "redirect:/employees";
	}

	@ExceptionHandler(SQLException.class)
	public String databaseError(Model model)
	{
		model.addAttribute("dataBaseError", "Unknown error was detected while working with database.");
		return "redirect:/employees";
	}

	@ExceptionHandler(NoSuchElementException.class)
	public String noElement()
	{
		return "redirect:/404";
	}
}
