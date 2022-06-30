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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.service.EmployeeService;
import zlagoda.server.company.validation.EmployeeValidator;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeValidator employeeValidator;

	@GetMapping("/employees")
	public String employees(Model model) {
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

	@GetMapping("/employee/{employeeId}")
	public String employee(@PathVariable("employeeId") String employeeId, Model model) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		model.addAttribute("roles", Employee.Role.values());
		return "singleEmployee";
	}

	@PostMapping("/employee/edit")
	public String employeeEdit(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		employeeValidator.validate(employee, result);
		if (result.hasErrors()) {
			model.addAttribute("roles", Employee.Role.values());
			model.addAttribute("invalidPhone", "Invalid phone number");
			return "singleEmployee";
		}
		employeeService.updateEmployeeById(employee.getId(), employee);
		return "redirect:/employees";
	}

	@ExceptionHandler(UncategorizedSQLException.class)
	public String databaseError() {
		return "redirect:/employees";
	}

	@ExceptionHandler(NoSuchElementException.class)
	public String noElement() {
		return "redirect:/404";
	}

}