package zlagoda.server.company.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInStore {
    private String UPC;
    private String promotionalUPC;
    private Product product;
    private BigDecimal price;
    private int amount;
    private boolean promotional;    
}
