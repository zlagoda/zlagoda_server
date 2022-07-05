package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeStatDTO {
    public enum Role {
        CASHIER,
        MANAGER
    }

    private int checksCreated;
    private BigDecimal totalSum;
    private String id;
    private String name;
    private String surname;
    private EmployeeStatDTO.Role role;

}
