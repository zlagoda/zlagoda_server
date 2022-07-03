package zlagoda.server.company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zlagoda.server.company.dto.ProductInStoreDTO;
import zlagoda.server.company.entity.Product;
import zlagoda.server.company.entity.ProductInStore;

@Component
public class ProductInStoreDTOConverter implements Converter<ProductInStoreDTO , ProductInStore>
{
	@Override
	public ProductInStore convert(final ProductInStoreDTO productInStoreDTO)
	{
		ProductInStore productInStore = new ProductInStore();
		productInStore.setUPC(productInStoreDTO.getUPC());

		if (productInStoreDTO.getProductId() != null)
		{
			Product product = new Product();
			product.setId(productInStoreDTO.getProductId());
			productInStore.setProduct(product);

		}
		productInStore.setPromotionalUPC(productInStoreDTO.getPromotionalUPC());
		productInStore.setPromotional(productInStoreDTO.isPromotional());
		productInStore.setPrice(productInStoreDTO.getPrice());
		productInStore.setAmount(productInStoreDTO.getAmount());

		return productInStore;
	}
}
