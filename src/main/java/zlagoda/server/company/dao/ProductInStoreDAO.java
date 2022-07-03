package zlagoda.server.company.dao;

import java.util.List;
import java.util.Optional;

import zlagoda.server.company.entity.ProductInStore;

public interface ProductInStoreDAO {
    List<ProductInStore> findAll();
    List<ProductInStore> findAllSorted(final String sortedBy);
    Optional<ProductInStore> findByUPC(final String UPC);
    int insertNew(final ProductInStore productInStore);
    void deleteByUPC(final String UPC);
    void updateByUPC(final String UPC, final ProductInStore productInStore);
    void updateAmountByUPC(final String UPC, final int amount);
	Integer getProductIdIfExists(final Integer productId);
}
