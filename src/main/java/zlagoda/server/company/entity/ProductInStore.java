package zlagoda.server.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInStore {
    private String UPC;
    private String promotionalUPC;
    private Product product;
    private long price;
    private long amount;
    private boolean promotional;    
}
