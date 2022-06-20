package zlagoda.server.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    public enum Role {
        CASHIER,
        MANAGER
    }

    private String id;
    private String surname;
    private String name;
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
