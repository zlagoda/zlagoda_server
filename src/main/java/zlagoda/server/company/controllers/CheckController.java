package zlagoda.server.company.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import zlagoda.server.company.converter.CartDTOConverter;
import zlagoda.server.company.dto.CardNumberDTO;

import zlagoda.server.company.dto.CartDTO;
import zlagoda.server.company.dto.CartItemDTO;
import zlagoda.server.company.entity.Category;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.service.CategoryService;
import zlagoda.server.company.service.CheckService;
import zlagoda.server.company.service.CustomerCardService;
import zlagoda.server.company.service.EmployeeService;
import zlagoda.server.company.service.ProductService;

@Controller
@SessionAttributes("sessionCheck")
public class CheckController {
	@Autowired
	private CheckService checkService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/checks")
	public String checks(Model model,
			@RequestParam(required = false, name = "filterSelect") Integer select,
			@RequestParam(required = false, name = "only") boolean onlyThis,
			@RequestParam(required = false, name = "product") List<Integer> checkedProducts,
			@RequestParam(required = false, name = "employee") String employeeId,
			@RequestParam(required = false, name = "category") List<Integer> checkedCategories) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);

		List<Check> checks = null;
		if (select != null) {
			if (select == 1 && checkedCategories != null) {
				checks = checkService.getChecksConsistCategory(checkedCategories, onlyThis);
				model.addAttribute("checkedCategories", checkedCategories);
			} else if (select == 3 && employeeId != null) {
				checks = checkService.getChecksByEmployee(employeeId);
			} else if (select == 2 && checkedProducts != null) {
				checks = checkService.getChecksConsistProducts(checkedProducts);
				model.addAttribute("checkedProducts", checkedProducts);
			} else {
				checks = checkService.getAllChecksInfo();
			}
		} else {
			checks = checkService.getAllChecksInfo();
		}
		model.addAttribute("checks", checks);

		return "check/checks";
	}

	@GetMapping("/check/{checkNumber}")
	public String getCheck(@PathVariable("checkNumber") String checkNumber, Model model) {
		Check check;
		check = checkService.getCheck(checkNumber);
		model.addAttribute("check", check);
		return "check/checkInfo";
	}
}
