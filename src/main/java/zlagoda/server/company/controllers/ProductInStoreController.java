package zlagoda.server.company.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import zlagoda.server.company.dto.ProductDTO;
import zlagoda.server.company.dto.ProductInStoreDTO;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.service.ProductInStoreService;
import zlagoda.server.company.service.ProductService;

@Controller
public class ProductInStoreController {

    @Autowired
    private ProductInStoreService productInStoreService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products-in-store")
    public String products(Model model) {
        List<ProductInStore> products = productInStoreService.getAllProductsInStore();
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
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productInStore", product);
        model.addAttribute("products", products);
        return "product/productInStoreEdit";
    }

    @PostMapping("/manager/products-in-store/edit")
    public String editProduct(@ModelAttribute("product") ProductInStoreDTO productDTO) {
        ProductInStore productInStore = new ProductInStore();
        productInStore.setUPC(productDTO.getUPC());
        productInStore.setPromotionalUPC(productDTO.getPromotionalUPC());
        productInStore.setAmount(productDTO.getAmount());
        productInStore.setPrice(productDTO.getPrice());
        productInStore.setPromotional(productDTO.isPromotional());
        Product product = new Product();
        product.setId(productDTO.getProductId());
        productInStore.setProduct(product);
        System.out.println(productInStore.toString());
        if (!productDTO.getUPC().equals("0")) {
            productInStoreService.updateByUPC(productDTO.getUPC(), productInStore);
        } else {
            productInStoreService.insertNew(productInStore);
        }
        return "redirect:/products-in-store";
    }

    @PostMapping("/manager/products-in-store/delete")
    public String deleteProduct(@ModelAttribute("deleteProduct") ProductInStore product) {
        productInStoreService.deleteByUPC(product.getUPC());
        return "redirect:/products-in-store";
    }
}
