package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeStatisticDTO {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private BigDecimal totalSum;
    private int checksCount;
}
