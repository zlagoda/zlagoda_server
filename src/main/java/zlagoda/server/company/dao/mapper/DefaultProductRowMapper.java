package zlagoda.server.company.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zlagoda.server.company.entity.Category;
import zlagoda.server.company.entity.Product;

public class DefaultProductRowMapper implements RowMapper<Product> {

	private static final String ID_PRODUCT = "id_product";
	private static final String PRODUCT_NAME = "product_name";
	private static final String CHARACTERISTICS = "characteristics";
	private static final String CATEGORY_ID = "category_id";
	private static final String CATEGORY_NAME = "category_name";

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt(ID_PRODUCT));
        product.setName(rs.getString(PRODUCT_NAME));
        product.setCharacteristics(rs.getString(CHARACTERISTICS));
        Category category = new Category();
        category.setId(rs.getInt(CATEGORY_ID));
        category.setName(rs.getString(CATEGORY_NAME));
        product.setCategory(category);
        return product;
    }
    
}
