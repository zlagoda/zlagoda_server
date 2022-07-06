package zlagoda.server.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.ProductDAO;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.service.ProductService;

@Service
public class DefaultProductService implements ProductService{

	@Autowired
	private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getByCategory(final int categoryId) {
        return productDAO.findByCategory(categoryId);
    }

    @Override
    public Product getById(final int id) {
        return productDAO.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(final int id) {
       productDAO.deleteById(id); 
    }

    @Override
    public void updateById(final int id, final Product product) {
        productDAO.updateById(id, product);
    }

    @Override
    public void insertNew(final Product product) {
        productDAO.insertNew(product);
    }
    
}
