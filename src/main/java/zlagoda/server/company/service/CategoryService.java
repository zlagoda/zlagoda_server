package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.dto.CategoryDTO;
import zlagoda.server.company.entity.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	int insertNewCategory(final String name);
	void deleteById(final int id);
	void updateById(final int id, final String name);
	Category getById(final int id);
	List<CategoryDTO> soldProductAmountInEachCategory();
}
