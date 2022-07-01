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
import zlagoda.server.company.entity.Category;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.service.CategoryService;
import zlagoda.server.company.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String categories(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/manager/product/{productId}")
    public String product(@PathVariable("productId") String productId, Model model) {
        Product product = null;
        int id = Integer.valueOf(productId);
        if (id > 0) {
            product = productService.getById(id);
        } else {
            product = new Product();
        }
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/productEdit";
    }

    @PostMapping("/manager/product/edit")
    public String editProduct(@ModelAttribute("product") ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCharacteristics(productDTO.getCharacteristics());
        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        product.setCategory(category);
        if (productDTO.getId() > 0) {
            productService.updateById(productDTO.getId(), product);
        } else {
            productService.insertNew(product);
        }
        return "redirect:/products";
    }

    @PostMapping("/manager/product/delete")
    public String deleteProduct(@ModelAttribute("deleteProduct") Product product) {
        productService.deleteById(product.getId());
        return "redirect:/products";
    }
}
