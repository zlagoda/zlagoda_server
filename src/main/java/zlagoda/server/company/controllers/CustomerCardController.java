package zlagoda.server.company.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.service.CustomerCardService;


@Controller
public class CustomerCardController
{
	@Autowired
	private CustomerCardService customerCardService;

	@GetMapping("customers")
	public String customers(Model model)
	{
		List<CustomerCard> customerCards = customerCardService.getAllCustomers();
		model.addAttribute("customerCards" , customerCards);
		return "customers";
	}
}
