package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.ProductDAO;
import zlagoda.server.company.dao.mapper.DefaultProductRowMapper;
import zlagoda.server.company.entity.Product;

@Repository
public class DefaultProductDAO implements ProductDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID_PRODUCT = "id_product";
    private static final String PRODUCT_NAME = "product_name";
    private static final String CHARACTERISTICS = "characteristics";
    private static final String CATEGORY_ID = "category_id";

    private static final String FIND_ALL_PRODUCTS = "SELECT Product.id_product," +
            "Product.product_name," +
            "Product.characteristics," +
            "Category.category_number as category_id," +
            "Category.category_name\n" +
            "FROM `Product`\n" +
            "INNER JOIN Category ON Product.category_number = Category.category_number\n" +
            "ORDER BY Product.product_name";
    private static final String FIND_BY_ID = "SELECT Product.id_product," +
            "Product.product_name," +
            "Product.characteristics," +
            "Category.category_number as category_id," +
            "Category.category_name\n" +
            "FROM `Product`\n" +
            "INNER JOIN `Category` ON Product.category_number = Category.category_number\n" +
            "WHERE Product.id_product = :id_product\n" +
            "ORDER BY Product.product_name";
    private static final String FIND_BY_CATEGORY = "SELECT Product.id_product," +
            "Product.product_name," +
            "Product.characteristics," +
            "Category.category_number as category_id," +
            "Category.category_name\n" +
            "FROM `Product`\n" +
            "INNER JOIN `Category` ON Product.category_number = Category.category_number\n" +
            "WHERE category_id = :category_id\n" +
            "ORDER BY Product.product_name";
    private static final String INSERT_NEW = "INSERT INTO `Product` (" +
            "category_number," +
            "product_name," +
            "characteristics) " +
            "VALUES (" +
            ":category_id," +
            ":product_name," +
            ":characteristics)";
    private static final String UPDATE_BY_ID = "UPDATE `Product`\n" +
            "SET category_number = :category_id," +
            "product_name = :product_name," +
            "characteristics = :characteristics\n" +
            "WHERE id_product = :id_product";
    private static final String DELETE_BY_ID = "DELETE FROM `Product`\n" +
            "WHERE id_product = :id_product";


    @Override
    public List<Product> findAll() {
        RowMapper<Product> mapper = new DefaultProductRowMapper();
        return namedParameterJdbcTemplate.query(FIND_ALL_PRODUCTS, mapper);
    }

    @Override
    public List<Product> findByCategory(final int categoryId) {
        RowMapper<Product> mapper = new DefaultProductRowMapper();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(CATEGORY_ID, categoryId);
        return namedParameterJdbcTemplate.query(FIND_BY_CATEGORY, parameter, mapper);
    }

    @Override
    public Optional<Product> findById(final int id) {
        RowMapper<Product> mapper = new DefaultProductRowMapper();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(ID_PRODUCT, id);
        List<Product> products = namedParameterJdbcTemplate.query(FIND_BY_ID, parameter, mapper);
        return products.stream().findFirst();
    }

    @Override
    public int insertNew(final Product product) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(CATEGORY_ID, product.getCategory().getId());
        parameter.put(PRODUCT_NAME, product.getName());
        parameter.put(CHARACTERISTICS, product.getCharacteristics());
        return namedParameterJdbcTemplate.update(INSERT_NEW, parameter);
    }

    @Override
    public void updateById(final int id, final Product product) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(ID_PRODUCT, id);
        parameter.put(CATEGORY_ID, product.getCategory().getId());
        parameter.put(PRODUCT_NAME, product.getName());
        parameter.put(CHARACTERISTICS, product.getCharacteristics());
        namedParameterJdbcTemplate.update(UPDATE_BY_ID, parameter);
    }

    @Override
    public void deleteById(final int id) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(ID_PRODUCT, id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID, parameter);
    }
}
