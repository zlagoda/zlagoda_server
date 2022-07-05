package zlagoda.server.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CustomerCardDTO {
	private String number;
	private String surname;
	private String name;
	private String patronymic;
	private String phoneNumber;
	private String city;
	private String street;
	private String zipCode;
	private int percent;
    private BigDecimal checkSum;
    private String cardNumber;
}
