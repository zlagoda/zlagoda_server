package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String characteristics;
    private int categoryId;
}
