package zlagoda.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInStore {
    private String UPC;
    private String promotionalUPC;
    private Product product;
    private long price;
    private long amount;
    private boolean promotional;    
}
