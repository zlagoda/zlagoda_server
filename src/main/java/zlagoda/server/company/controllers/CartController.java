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
@SessionAttributes("sessionCart")
public class CartController {

    @ModelAttribute("sessionCart")
    public CartDTO populateCart() {
        return new CartDTO();
    }

    @Autowired
    private CustomerCardService customerCardService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private CartDTOConverter cartDTOConverter;

    @PostMapping("/cashier/cart/add")
    public String addProductToCart(@ModelAttribute("product") CartItemDTO cartItemDTO,
            @ModelAttribute("sessionCart") CartDTO cart) {
        cart.addProduct(cartItemDTO);
        return "redirect:/cashier/cart";
    }

    @GetMapping("/cashier/cart")
    public String cart(@ModelAttribute("sessionCart") CartDTO cart, Model model) {
        model.addAttribute("cart", cart.getCart());
        model.addAttribute("customer", cart.getCustomerCard());
        return "cart";
    }

    @PostMapping("/cashier/cart/customer-card")
    public String addCustomerCard(@ModelAttribute("cardNumber") CardNumberDTO cardNumber,
            @ModelAttribute("sessionCart") CartDTO cart, Model model) {
        CustomerCard card = customerCardService.getCustomerByNumber(cardNumber.getCardNumber());
        cart.setCustomerCard(card);
        return "redirect:/cashier/cart";
    }

    @PostMapping("/cashier/cart/create")
    public String addCheck(@ModelAttribute("sessionCart") CartDTO cart, Model model)
            throws InvalidAttributeValueException {
        Check check = cartDTOConverter.convert(cart);
        checkService.insertNewCheck(check);
        cart.clear();
        return "redirect:/cashier/cart";
    }

    @PostMapping("/cashier/cart/remove")
    public String removeProduct(@ModelAttribute("sessionCart") CartDTO cart,
            @ModelAttribute("removeProduct") CartItemDTO removeProduct) {
        cart.removeProduct(removeProduct.getUPC());
        return "redirect:/cashier/cart";
    }

    @ExceptionHandler(InvalidAttributeValueException.class)
    public String databaseError(Model model) {
        model.addAttribute("noSuchElement", "Invalid amount of products");
        return "redirect:/cashier/cart";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementError(Model model) {
        model.addAttribute("noSuchElement", "There is no such card number");
        return "redirect:/cashier/cart";
    }
}
