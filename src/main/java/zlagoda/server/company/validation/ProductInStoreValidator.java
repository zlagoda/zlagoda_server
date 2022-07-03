package zlagoda.server.company.validation;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import zlagoda.server.company.dao.ProductDAO;
import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.entity.ProductInStore;


@Component
public class ProductInStoreValidator implements Validator
{
	private static final String UPC = "UPC";
	private static final String PRODUCT_ID = "productId";
	private static final String INVALID_UPC = "custom.invalid.product.UPC";

	@Autowired
	private ProductInStoreDAO productInStoreDAO;

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return ProductInStore.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		ProductInStore productInStore = (ProductInStore) target;
		checkDuplicate(productInStore, errors);
		//checkDuplicateProduct(productInStore, errors);
	}

	void checkDuplicate(ProductInStore productInStore, Errors errors)
	{
		if (productInStoreDAO.findByUPC(productInStore.getUPC()).isPresent())
		{
			errors.rejectValue(UPC, INVALID_UPC);
		}
	}

	void checkDuplicateProduct(ProductInStore productInStore, Errors errors)
	{
		if (productInStoreDAO.getProductIdIfExists(productInStore.getProduct().getId()) != null)
		{
			errors.rejectValue(PRODUCT_ID, INVALID_UPC);
		}
	}
}
