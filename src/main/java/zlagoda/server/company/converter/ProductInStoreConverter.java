package zlagoda.server.company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zlagoda.server.company.dto.ProductInStoreDTO;
import zlagoda.server.company.entity.ProductInStore;


@Component
public class ProductInStoreConverter implements Converter<ProductInStore , ProductInStoreDTO>
{
	@Override
	public ProductInStoreDTO convert(final ProductInStore productInStore)
	{
		ProductInStoreDTO productInStoreDTO = new ProductInStoreDTO();
		productInStoreDTO.setUPC(productInStore.getUPC());
		if (productInStore.getProduct() != null)
		{
			productInStoreDTO.setProductId(productInStore.getProduct().getId());
		}
		productInStoreDTO.setPromotionalUPC(productInStore.getPromotionalUPC());
		productInStoreDTO.setPromotional(productInStore.isPromotional());
		productInStoreDTO.setPrice(productInStore.getPrice());
		productInStoreDTO.setAmount(productInStore.getAmount());

		return productInStoreDTO;
	}
}
