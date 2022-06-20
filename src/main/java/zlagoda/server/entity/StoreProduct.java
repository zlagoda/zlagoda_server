package zlagoda.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreProduct {
    private String UPC;
    private String promotionalUPC;
    private long productId;
    private long price;
    private long count;
    private boolean promotional;    
}
