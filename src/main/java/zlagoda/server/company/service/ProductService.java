package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();
	List<Product> getByCategory(final int categoryId);
	Product getById(final int id);
	void deleteById(final int id);
	void updateById(final int id, final Product product);
	void insertNew(final Product product);
}
