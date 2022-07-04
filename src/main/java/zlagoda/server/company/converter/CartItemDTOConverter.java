package zlagoda.server.company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zlagoda.server.company.dto.CartItemDTO;
import zlagoda.server.company.entity.SoldProduct;

@Component
public class CartItemDTOConverter implements Converter<CartItemDTO, SoldProduct> {

    @Override
    public SoldProduct convert(CartItemDTO cartItemDTO) {
        SoldProduct product = new SoldProduct();
        product.setUPC(cartItemDTO.getUPC());
        product.setAmount(cartItemDTO.getAmount());
        product.setPrice(cartItemDTO.getPrice());
        return product;
    }
    
}
