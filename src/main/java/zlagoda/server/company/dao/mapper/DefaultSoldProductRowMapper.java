package zlagoda.server.company.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultSoldProductRowMapper implements RowMapper<SoldProduct> {
    private static final String UPC = "UPC";
    private static final String PRODUCT_NUMBER = "product_number";
    private static final String SELLING_PRICE = "selling_price";

    @Override
    public SoldProduct mapRow(ResultSet rs, int rowNum) throws SQLException{
        SoldProduct soldProduct = new SoldProduct();
        soldProduct.setUPC(rs.getString(UPC));
        soldProduct.setAmount(rs.getInt(PRODUCT_NUMBER));
        soldProduct.setPrice(rs.getBigDecimal(SELLING_PRICE));
        return soldProduct;
    }
}
