package zlagoda.server.company.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zlagoda.server.company.entity.Category;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.entity.ProductInStore;

public class DefaultProductInStoreMapper implements RowMapper<ProductInStore> {

	private static final String UPC = "UPC";
	private static final String UPC_PROM = "UPC_prom";
	private static final String SELLING_PRICE = "selling_price";
	private static final String PRODUCTS_NUMBER = "products_number";
	private static final String PROMOTIONAL_PRODUCT = "promotional_product";
	private static final String ID_PRODUCT = "product_id";
	private static final String PRODUCT_NAME = "product_name";
	private static final String CHARACTERISTICS = "characteristics";
	private static final String CATEGORY_ID = "category_id";
	private static final String CATEGORY_NAME = "category_name";

    @Override
    public ProductInStore mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductInStore productInStore = new ProductInStore();
        productInStore.setUPC(rs.getString(UPC));
        productInStore.setPromotionalUPC(rs.getString(UPC_PROM));
        productInStore.setPrice(rs.getBigDecimal(SELLING_PRICE));
        productInStore.setAmount(rs.getInt(PRODUCTS_NUMBER));
        productInStore.setPromotional(rs.getBoolean(PROMOTIONAL_PRODUCT));
        Product product = new Product();
        product.setId(rs.getInt(ID_PRODUCT));
        product.setName(rs.getString(PRODUCT_NAME));
        product.setCharacteristics(rs.getString(CHARACTERISTICS));
        Category category = new Category();
        category.setId(rs.getInt(CATEGORY_ID));
        category.setName(rs.getString(CATEGORY_NAME));
        product.setCategory(category);
        productInStore.setProduct(product);
        return productInStore;
    }
    
}
