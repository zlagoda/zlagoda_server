package zlagoda.server.company.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SoldProduct {
    private String UPC;
    private String name;
    private int amount;
    private BigDecimal price;
}
