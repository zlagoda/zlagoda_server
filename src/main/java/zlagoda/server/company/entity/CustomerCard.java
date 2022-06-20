package zlagoda.server.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerCard {
    private String number;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private Long percent;
}
