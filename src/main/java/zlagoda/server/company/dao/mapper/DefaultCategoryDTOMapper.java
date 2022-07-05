package zlagoda.server.company.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.dto.CategoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultCategoryDTOMapper implements RowMapper<CategoryDTO> {

    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String TOTAL_PRICE = "total_price";
    private static final String CATEGORY_NAME = "category_name";

    @Override
    public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setTotalAmount(rs.getInt(TOTAL_AMOUNT));
        categoryDTO.setTotalPrice(rs.getBigDecimal(TOTAL_PRICE));
        categoryDTO.setCatName(rs.getString(CATEGORY_NAME));
        return categoryDTO;
    }
}
