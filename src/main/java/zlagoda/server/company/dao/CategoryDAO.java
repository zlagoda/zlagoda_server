package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.entity.Category;

public interface CategoryDAO {
    List<Category> findAllCategories();
    int insertNewCategory(final String name);
    void deleteById(final int id);
    void updateById(final int id, final String name);
    Optional<Category> findById(final int id);
}
