package zlagoda.server.company.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private String UPC;
    private String name; 
    private int amount;
    private BigDecimal price;
}
