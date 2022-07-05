package zlagoda.server.company.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import zlagoda.server.company.dto.CategoryDTO;
import zlagoda.server.company.entity.Category;
import zlagoda.server.company.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/manager/categories")
    public String categories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
        return "category/categories";
    }

	@GetMapping("/manager/categories/statistics")
	public String categoriesStat(Model model) {
		List<CategoryDTO> categoryDTOS = categoryService.soldProductAmountInEachCategory();
		model.addAttribute("categoryDTOS", categoryDTOS);
		return "category/categoryStatistics";
	}

    @GetMapping("/manager/categories/edit/{categoryId}")
    public String category(@PathVariable("categoryId") String categoryId, Model model) {
        Category category = categoryService.getById(Integer.valueOf(categoryId));
        model.addAttribute("category", category);
        return "category/editCategory";
    }

    @PostMapping("/manager/categories/new")
    public String newCategory(@ModelAttribute("newCategory") Category category) {
        categoryService.insertNewCategory(category.getName());
        return "redirect:/manager/categories";
    }

    @PostMapping("/manager/categories/delete")
    public String deleteCategory(@ModelAttribute("deleteCategory") Category category) {
        categoryService.deleteById(category.getId());
        return "redirect:/manager/categories";
    }

    @PostMapping("/manager/categories/edit")
    public String editCategory(@ModelAttribute("editedCategory") Category category) {
        categoryService.updateById(category.getId(), category.getName());
        return "redirect:/manager/categories";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String constraintError(Model model) {
        model.addAttribute("constraintError", "Error: there are products from this categories");
        return "redirect:/manager/categories";
    }

    //soldProductAmountInEachCategory()
}
