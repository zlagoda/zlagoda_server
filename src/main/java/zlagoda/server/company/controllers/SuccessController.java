package zlagoda.server.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import zlagoda.server.company.entity.Employee;


@Controller
public class SuccessController
{
	@GetMapping("/success")
	public String employee()
	{
		return "success";
	}
}
