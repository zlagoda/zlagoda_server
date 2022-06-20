package zlagoda.server.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SoldProduct {
    private String UPC;
    private long amount;
    private long price;
}
