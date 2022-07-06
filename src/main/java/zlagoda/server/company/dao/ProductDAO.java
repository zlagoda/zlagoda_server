package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.entity.Product;

public interface ProductDAO {
    List<Product> findAll();
    List<Product> findByCategory(final int categoryId);
    Optional<Product> findById(final int id);
    int insertNew(final Product product);
    void updateById(final int id, final Product product);
    void deleteById(final int id);
}
