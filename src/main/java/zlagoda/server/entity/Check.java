package zlagoda.server.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Check {
    private String number;
    private Employee employee;
    private CustomerCard card;
    private Timestamp printDate;
    private long totalSum;
    private long VAT; // value added tax
    private List<ProductSale> products;
}
