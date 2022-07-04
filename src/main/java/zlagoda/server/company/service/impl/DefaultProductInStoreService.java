package zlagoda.server.company.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.service.ProductInStoreService;

@Service
@Transactional
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
        int result = 0;
        if (productInStore.isPromotional()) {
            ProductInStore nonPromotional = productInStoreDAO
                    .getNonPromotionalByProductId(productInStore.getProduct().getId()).orElseThrow();
            nonPromotional.setPromotionalUPC(productInStore.getUPC());
            productInStore.setPrice(nonPromotional.getPrice().multiply(new BigDecimal("0.8")));
            result = productInStoreDAO.insertNew(productInStore);
            productInStoreDAO.updateByUPC(nonPromotional.getUPC(), nonPromotional);
        } else {
            result = productInStoreDAO.insertNew(productInStore);
        }
        return result;
    }

    @Override
    public void deleteByUPC(String UPC) {
        productInStoreDAO.deleteByUPC(UPC);
    }

    @Override
    public void updateByUPC(String UPC, ProductInStore productInStore) {
        if (productInStore.isPromotional()) {
            ProductInStore nonPromotional = productInStoreDAO
                    .getNonPromotionalByProductId(productInStore.getProduct().getId()).orElseThrow();
            nonPromotional.setPromotionalUPC(productInStore.getUPC());
            productInStoreDAO.updateByUPC(nonPromotional.getUPC(), nonPromotional);
            productInStore.setPrice(nonPromotional.getPrice().multiply(new BigDecimal("0.8")));
        } else {
            try {
                ProductInStore promotional = productInStoreDAO
                        .getPromotionalByProductId(productInStore.getProduct().getId()).orElseThrow();
                promotional.setPrice(productInStore.getPrice().multiply(new BigDecimal("0.8")));
                productInStoreDAO.updateByUPC(promotional.getUPC(), promotional);
            } catch (Exception e) {
            }
        }
        productInStoreDAO.updateByUPC(UPC, productInStore);
    }

    @Override
    public void updateAmountByUPC(String UPC, int amount) {
        productInStoreDAO.updateAmountByUPC(UPC, amount);
    }

}
