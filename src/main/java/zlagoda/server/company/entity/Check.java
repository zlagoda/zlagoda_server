package zlagoda.server.company.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Check {
    private String number;
    private Employee employee;
    private CustomerCard card;
    private Timestamp printDate;
    private long totalSum;
    private long VAT; // value added tax
    private List<SoldProduct> products;
}
