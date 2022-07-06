package zlagoda.server.company.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zlagoda.server.company.entity.Category;

public class DefaultCategoryRowMapper implements RowMapper<Category> {
	private static final String CATEGORY_NUMBER = "category_number";
	private static final String CATEGORY_NAME = "category_name";

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt(CATEGORY_NUMBER));
        category.setName(rs.getString(CATEGORY_NAME));
        return category;
    }
    
}
