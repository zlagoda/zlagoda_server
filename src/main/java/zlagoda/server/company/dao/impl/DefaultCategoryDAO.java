package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.CategoryDAO;
import zlagoda.server.company.dao.mapper.DefaultCategoryRowMapper;
import zlagoda.server.company.entity.Category;

@Repository
public class DefaultCategoryDAO implements CategoryDAO {
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private static final String CATEGORY_NUMBER = "category_number";
  private static final String CATEGORY_NAME = "category_name";

  private static final String FIND_ALL_CATEGORIES = "SELECT * FROM `Category` ORDER BY category_name";
  private static final String INSERT_NEW_CATEGORY = "INSERT INTO `Category` (category_name) VALUES (:category_name)";
  private static final String DELETE_BY_ID = "DELETE FROM `Category` WHERE category_number = :category_number";
  private static final String UPDATE_BY_ID = "UPDATE `Category` SET category_name = :category_name WHERE category_number = :category_number";
  private static final String FIND_BY_ID = "SELECT * FROM `Category` WHERE category_number = :category_number";

  @Override
  public List<Category> findAllCategories() {
    RowMapper<Category> mapper = new DefaultCategoryRowMapper();
    return namedParameterJdbcTemplate.query(FIND_ALL_CATEGORIES, mapper);
  }

  @Override
  public int insertNewCategory(final String name) {
    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put(CATEGORY_NAME, name);
    return namedParameterJdbcTemplate.update(INSERT_NEW_CATEGORY, parameter);
  }

  @Override
  public void deleteById(final int id) {
    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put(CATEGORY_NUMBER, id);
    namedParameterJdbcTemplate.update(DELETE_BY_ID, parameter);
  }

  @Override
  public void updateById(final int id, final String name) {
    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put(CATEGORY_NUMBER, id);
    parameter.put(CATEGORY_NAME, name);
    namedParameterJdbcTemplate.update(UPDATE_BY_ID, parameter);
  }

  @Override
  public Optional<Category> findById(final int id) {
    RowMapper<Category> mapper = new DefaultCategoryRowMapper();
    Map<String, Object> parameter = new HashMap<>();
    parameter.put(CATEGORY_NUMBER, id);
    List<Category> employees = namedParameterJdbcTemplate.query(FIND_BY_ID, parameter, mapper);
    return employees.stream().findFirst();
  }
}
