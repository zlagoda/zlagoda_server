package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zlagoda.server.company.entity.Category;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class CategoryDTO {
    private int totalAmount;
    private BigDecimal totalPrice;
    private String catName;
	private int id;
}
