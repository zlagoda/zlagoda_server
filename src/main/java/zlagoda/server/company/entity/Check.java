package zlagoda.server.company.entity;

import java.math.BigDecimal;
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
    private BigDecimal totalSum;
    private BigDecimal VAT; // value added tax
    private List<SoldProduct> products;
}
