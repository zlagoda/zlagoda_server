package zlagoda.server.company.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.dao.mapper.DefaultProductInStoreMapper;

@Repository
public class DefaultProductInStoreDAO implements ProductInStoreDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    private static final String FIND_ALL_PRODUCTS = "SELECT Store_Product.UPC," +
            "Store_Product.UPC_prom," +
            "Store_Product.selling_price," +
            "Store_Product.products_number," +
            "Store_Product.promotional_product," +
            "Product.id_product AS product_id," +
            "Product.product_name," +
            "Product.characteristics," +
            "Category.category_number AS category_id," +
            "Category.category_name\n" +
            "FROM `Store_Product`\n" +
            "INNER JOIN `Product` ON Store_Product.id_product = Product.id_product\n" +
            "INNER JOIN `Category` ON Product.category_number = Category.category_number\n" +
            "ORDER BY :sortedBy";
    private static final String FIND_BY_UPC = "SELECT Store_Product.UPC," +
            "Store_Product.UPC_prom," +
            "Store_Product.selling_price," +
            "Store_Product.products_number," +
            "Store_Product.promotional_product," +
            "Product.id_product AS product_id," +
            "Product.product_name," +
            "Product.characteristics," +
            "Category.category_number AS category_id," +
            "Category.category_name\n" +
            "FROM `Store_Product`\n" +
            "INNER JOIN `Product` ON Store_Product.id_product = Product.id_product\n" +
            "INNER JOIN `Category` ON Product.category_number = Category.category_number\n" +
            "WHERE UPC = :UPC";
    private static final String UPDATE_AMOUNT_BY_UPC = "UPDATE `Store_Product` SET products_number = :products_number WHERE UPC = :UPC";
    private static final String DELETE_BY_UPC = "DELETE FROM `Store_Product` WHERE UPC = :UPC";
    private static final String INSERT_NEW = "INSERT INTO `Store_Product` VALUES (" +
            "UPC, UPC_prom, id_product, selling_price, products_number, promotional_product) VALUES (" +
            ":UPC, :UPC_prom, :product_id, :selling_price, :products_number, :promotional_product" +
            ")";
    private static final String UPDATE_BY_UPC = "UPDATE `Store_Product`\n" +
            "SET UPC_prom = :UPC_prom," +
            "id_product = :product_id," +
            "selling_price = :selling_price," +
            "products_number = :products_number," +
            "promotional_product = :promotional_product\n" +
            "WHERE UPC = :UPC";

    @Override
    public List<ProductInStore> findAll() {
        RowMapper<ProductInStore> mapper = new DefaultProductInStoreMapper();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("sortedBy", "products_number");
        return namedParameterJdbcTemplate.query(FIND_ALL_PRODUCTS, parameter, mapper);
    }

    @Override
    public List<ProductInStore> findAllSorted(String sortedBy) {
        RowMapper<ProductInStore> mapper = new DefaultProductInStoreMapper();
        Map<String, Object> parameter = new HashMap<>();
        if (sortedBy.equals("name")) {
            sortedBy = PRODUCT_NAME;
        } else {
            sortedBy = PRODUCTS_NUMBER;
        }
        parameter.put("sortedBy", sortedBy);
        return namedParameterJdbcTemplate.query(FIND_ALL_PRODUCTS, parameter, mapper);
    }

    @Override
    public Optional<ProductInStore> findByUPC(String UPC) {
        RowMapper<ProductInStore> mapper = new DefaultProductInStoreMapper();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(DefaultProductInStoreDAO.UPC, UPC);
        List<ProductInStore> products = namedParameterJdbcTemplate.query(FIND_BY_UPC, parameter, mapper);
        return products.stream().findFirst();
    }

    @Override
    public int insertNew(ProductInStore productInStore) {
        System.out.println(productInStore.toString());
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(UPC, productInStore.getUPC());
        parameter.put(UPC_PROM, productInStore.getPromotionalUPC());
        parameter.put(ID_PRODUCT, productInStore.getProduct().getId());
        parameter.put(SELLING_PRICE, productInStore.getPrice());
        parameter.put(PRODUCTS_NUMBER, productInStore.getAmount());
        parameter.put(PROMOTIONAL_PRODUCT, productInStore.isPromotional());
        return namedParameterJdbcTemplate.update(INSERT_NEW, parameter);
    }

    @Override
    public void deleteByUPC(String UPC) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(DefaultProductInStoreDAO.UPC, UPC);
        namedParameterJdbcTemplate.update(DELETE_BY_UPC, parameter);
    }

    @Override
    public void updateByUPC(String UPC, ProductInStore productInStore) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(UPC, productInStore.getUPC());
        parameter.put(UPC_PROM, productInStore.getPromotionalUPC());
        parameter.put(ID_PRODUCT, productInStore.getProduct().getId());
        parameter.put(SELLING_PRICE, productInStore.getPrice());
        parameter.put(PRODUCTS_NUMBER, productInStore.getAmount());
        parameter.put(PROMOTIONAL_PRODUCT, productInStore.isPromotional());
        namedParameterJdbcTemplate.update(UPDATE_BY_UPC, parameter);

    }

    @Override
    public void updateAmountByUPC(String UPC, int amount) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(PRODUCTS_NUMBER, amount);
        parameter.put(DefaultProductInStoreDAO.UPC, UPC);
        namedParameterJdbcTemplate.update(UPDATE_AMOUNT_BY_UPC, parameter);
    }

}