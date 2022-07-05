package zlagoda.server.company.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import zlagoda.server.company.dto.CustomerCardDTO;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.service.CustomerCardService;
import zlagoda.server.company.validation.CustomerValidator;


@Controller
public class CustomerCardController
{
	@Autowired
	private CustomerCardService customerCardService;

	@Autowired
	private CustomerValidator customerValidator;

	@GetMapping("customers")
	public String customers(Model model)
	{
		List<CustomerCard> customerCards = customerCardService.getAllCustomers();
		model.addAttribute("customerCards", customerCards);
		return "customers";
	}

	@GetMapping("customers/statistics")
	public String customersStat(Model model)
	{
		List<CustomerCardDTO> customerCardDTOS = customerCardService.getBoughtProductSum();
		model.addAttribute("customerCardDTOS", customerCardDTOS);
		return "customerStatistics";
	}

	@GetMapping("/customer/add")
	public String customerAdd(Model model)
	{
		model.addAttribute("customerCard", new CustomerCard());
		return "addCustomer";
	}

	@PostMapping("/customer/add")
	public String customerAddPost(@ModelAttribute("customerCard") CustomerCard customerCard, BindingResult result)
	{
		customerValidator.validate(customerCard , result);
		if (result.hasErrors())
		{
			return "addCustomer";
		}
		customerCardService.registerCustomer(customerCard);
		return "redirect:/customers";
	}

	@GetMapping("/customer/delete/{customerCardNumber}")
	public String customerDelete(@PathVariable("customerCardNumber") String customerCardNumber)
	{
		customerCardService.deleteCustomer(customerCardNumber);
		return "redirect:/customers";
	}

	@GetMapping("/customer/{customerCardNumber}")
	public String customerEdit(@PathVariable("customerCardNumber") String customerCardNumber , Model model)
	{
		CustomerCard customerCard = customerCardService.getCustomerByNumber(customerCardNumber);
		model.addAttribute("customerCard" , customerCard);
		return "editCustomer";
	}

	@PostMapping("/customer/edit/{customerCardNumber}")
	public String customerEditPost(@ModelAttribute("customerCard") CustomerCard customerCard , BindingResult result, Model model)
	{
		customerValidator.validate(customerCard ,result);
		if (result.hasErrors())
		{
			return "editCustomer";
		}
		customerCardService.updateCustomerById(customerCard);
		return "redirect:/customers";
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
