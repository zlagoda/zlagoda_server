package zlagoda.server.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.CategoryDAO;
import zlagoda.server.company.dto.CategoryDTO;
import zlagoda.server.company.entity.Category;
import zlagoda.server.company.service.CategoryService;

@Service
public class DefaultCategoryService implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

	@Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAllCategories();
    }

    @Override
    public int insertNewCategory(final String name) {
        return categoryDAO.insertNewCategory(name);
    }

    @Override
    public void deleteById(final int id) {
       categoryDAO.deleteById(id); 
    }

    @Override
    public void updateById(final int id, final String name) {
        categoryDAO.updateById(id, name);
    }

    @Override
    public Category getById(int id) {
       return categoryDAO.findById(id).orElseThrow();
    }

    @Override
    public List<CategoryDTO> soldProductAmountInEachCategory() {
        return categoryDAO.soldProductAmountInEachCategory();
    }
}
