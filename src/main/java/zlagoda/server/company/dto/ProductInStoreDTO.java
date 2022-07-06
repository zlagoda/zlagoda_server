package zlagoda.server.company.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInStoreDTO {
    private String id;
    private String UPC;
    private String promotionalUPC;
    private Integer productId;
    private BigDecimal price;
    private int amount;
    private boolean promotional;
}
