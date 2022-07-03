package zlagoda.server.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Service;

import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.service.ProductInStoreService;

@Service
public class DefaultProductInStoreService implements ProductInStoreService {

    @Autowired
    private ProductInStoreDAO productInStoreDAO;

    @Override
    public List<ProductInStore> getAllProductsInStore() {
        return productInStoreDAO.findAll();
    }

    @Override
    public List<ProductInStore> getAllSorted(String sortedBy) {
        return productInStoreDAO.findAllSorted(sortedBy);
    }

    @Override
    public ProductInStore getByUPC(String UPC) {
        return productInStoreDAO.findByUPC(UPC).orElseThrow();
    }

    @Override
    public int insertNew(ProductInStore productInStore) {
		if (productInStore.isPromotional())
		{
			productInStore.setPromotionalUPC(productInStore.getUPC());
		}
        return productInStoreDAO.insertNew(productInStore);
    }

    @Override
    public void deleteByUPC(String UPC) {
        productInStoreDAO.deleteByUPC(UPC);
    }

    @Override
    public void updateByUPC(String UPC, ProductInStore productInStore) {
		if (productInStore.isPromotional())
		{
			productInStore.setPromotionalUPC(productInStore.getUPC());
		}
       productInStoreDAO.updateByUPC(UPC, productInStore); 
    }

    @Override
    public void updateAmountByUPC(String UPC, int amount) {
        productInStoreDAO.updateAmountByUPC(UPC, amount);
    }
    
}
