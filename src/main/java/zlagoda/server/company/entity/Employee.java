package zlagoda.server.company.entity;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    public enum Role {
        CASHIER,
        MANAGER
    }

    private String id;
    private String surname;
    private String name;
    private String password;
    private String patronymic;
    private Role role;
    private long salary;
    private Date birthdate;
    private Date startDate;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
}
