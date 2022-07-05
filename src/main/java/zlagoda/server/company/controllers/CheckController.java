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
import org.springframework.web.bind.annotation.SessionAttributes;

import zlagoda.server.company.converter.CartDTOConverter;
import zlagoda.server.company.dto.CardNumberDTO;

import zlagoda.server.company.dto.CartDTO;
import zlagoda.server.company.dto.CartItemDTO;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.service.CheckService;
import zlagoda.server.company.service.CustomerCardService;


@Controller
@SessionAttributes("sessionCheck")
public class CheckController
{
	@Autowired
	private CheckService checkService;

	/* @GetMapping("/checks-for-period")
	public String getChecksForPeriod(String print_date, Model model)
	{
		List<Check> checks = checkService.getChecksForPeriod(print_date);
		model.addAttribute("checks", checks);
		return "checks/" + print_date;
	}

	@GetMapping("/checks-for-period/cashier{id_employee}")
	public String getChecksForPeriodByCashier(String id_employee, String print_date, Model model)
	{
		List<Check> checks = checkService.getChecksForPeriodByCashier(id_employee, print_date);
		model.addAttribute("checks", checks);
		return "checks/" + print_date + "/" + id_employee;
	} */


	@GetMapping("/manager/checks")
	public String checks(Model model)
	{
		List<Check> checks = checkService.getAllChecksInfo();
		model.addAttribute("checks" , checks);
		return "check/checks";
	}

	@GetMapping("/check/{checkNumber}")
	public String getCheck(@PathVariable("checkNumber") String checkNumber, Model model)
	{
		Check check;
		check = checkService.getCheck(checkNumber);
		model.addAttribute("check", check);
		return "check/checkInfo";
	}
}
