package zlagoda.server.company.service;

import java.util.List;

import zlagoda.server.company.entity.ProductInStore;

public interface ProductInStoreService {
    List<ProductInStore> getAllProductsInStore();

    List<ProductInStore> getAllSorted(final String sortedBy);

    ProductInStore getByUPC(final String UPC);

    int insertNew(final ProductInStore productInStore);

    void deleteByUPC(final String UPC);

    void updateByUPC(final String UPC, final ProductInStore productInStore);

    void updateAmountByUPC(final String UPC, final int amount);
}
