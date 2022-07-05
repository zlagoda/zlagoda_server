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
public class CheckController {
    @Autowired
    private CheckService checkService;

    @GetMapping("/checks-for-period")
    public String getChecksForPeriod(String print_date, Model model){
        List<Check> checks = checkService.getChecksForPeriod(print_date);
        model.addAttribute("checks", checks);
        return "checks/" + print_date;
    }

    @GetMapping("/checks-for-period/cashier{id_employee}")
    public String getChecksForPeriodByCashier(String id_employee, String print_date, Model model){
        List<Check> checks = checkService.getChecksForPeriodByCashier(id_employee, print_date);
        model.addAttribute("checks", checks);
        return "checks/" + print_date + "/" + id_employee;
    }

    @GetMapping("/check/")
    public String getCheck(String check_number, Model model){
        Check check = null;
        int id = Integer.valueOf(check_number);
        if (id > 0) {
            check = checkService.getCheck(check_number);
        } else {
            check = new Check();
        }
        model.addAttribute("check", check);
        return "check/"+check_number;
    }

}
