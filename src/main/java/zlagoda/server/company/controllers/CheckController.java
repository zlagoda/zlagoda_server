package zlagoda.server.company.controllers;
import java.util.Arrays;
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

}
