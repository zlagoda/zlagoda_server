package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CustomerCardDTO {
    private BigDecimal checkSum;
    private String cardNumber;
    private String name;
    private String surname;
}
