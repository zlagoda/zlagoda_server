package zlagoda.server.company.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import zlagoda.server.company.entity.Employee;

@Data
@NoArgsConstructor
public class EmployeeDTO
{public enum Role {
	CASHIER,
	MANAGER
}

	private String id;

	private String surname;
	private String name;
	private String password;
	private String patronymic;
	private Employee.Role role;
	private BigDecimal salary;
	private String birthdate;
	private String startDate;
	private String phoneNumber;
	private String city;
	private String street;
	private String zipCode;
}
