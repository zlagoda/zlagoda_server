package zlagoda.server.company.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import zlagoda.server.company.converter.ProductInStoreConverter;
import zlagoda.server.company.converter.ProductInStoreDTOConverter;
import zlagoda.server.company.dto.ProductDTO;
import zlagoda.server.company.dto.ProductInStoreDTO;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.service.ProductInStoreService;
import zlagoda.server.company.service.ProductService;
import zlagoda.server.company.validation.ProductInStoreValidator;

@Controller
public class ProductInStoreController {

    @Autowired
    private ProductInStoreService productInStoreService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInStoreValidator productInStoreValidator;

    @Autowired
    private ProductInStoreConverter productInStoreConverter;

    @Autowired
    private ProductInStoreDTOConverter productInStoreDTOConverter;

    @GetMapping("/products-in-store")
    public String products(Model model, @RequestParam(required = false, name = "sort") String sort) {
        List<ProductInStore> products = null;
        if (sort == null) {
            products = productInStoreService.getAllProductsInStore();
        }
        else {
            products = productInStoreService.getAllSorted(sort);
        }
        model.addAttribute("products", products);
        return "product/productsInStore";
    }

    @GetMapping("/manager/products-in-store/{UPC}")
    public String product(@PathVariable("UPC") String UPC, Model model) {
        ProductInStore product = null;
        if (!UPC.equals("0")) {
            product = productInStoreService.getByUPC(UPC);
        } else {
            product = new ProductInStore();
            product.setUPC(UPC);
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productInStore", productInStoreConverter.convert(product));
        model.addAttribute("products", products);
        return "product/productInStoreEdit";
    }

    @PostMapping("/manager/products-in-store/edit")
    public String editProduct(@ModelAttribute("productInStore") ProductInStoreDTO productDTO, BindingResult result,
            Model model) {
        ProductInStore productInStore = productInStoreDTOConverter.convert(productDTO);
        productInStoreValidator.validate(productInStore, result);
        if (!productDTO.getId().equals("0")) {
            productInStoreService.updateByUPC(productDTO.getUPC(), productInStore);
        } else {
            if (result.hasErrors()) {
                List<Product> products = productService.getAllProducts();
                model.addAttribute("products", products);
                return "product/productInStoreEdit";
            }
            productInStoreService.insertNew(productInStore);
        }
        return "redirect:/products-in-store";
    }

    @PostMapping("/manager/products-in-store/delete")
    public String deleteProduct(@ModelAttribute("deleteProduct") ProductInStore product) {
        productInStoreService.deleteByUPC(product.getUPC());
        return "redirect:/products-in-store";
    }

    @ExceptionHandler(SQLException.class)
    public String databaseError(Model model) {
        model.addAttribute("dataBaseError", "Unknown error was detected while working with database.");
        return "redirect:/products-in-store";
    }
}
