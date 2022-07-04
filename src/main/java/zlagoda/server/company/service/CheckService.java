package zlagoda.server.company.service;

import javax.management.InvalidAttributeValueException;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

public interface CheckService {
    void insertNewCheck(Check check) throws InvalidAttributeValueException;
    void insertNewSale(String checkNumber, SoldProduct soldProduct);
    void getChecksForPeriod(String print_date);
    void getChecksForPeriodByCashier(String id_employee, String print_date);
    void getCheck(String check_number);
    void soldProductsSumByCashier(String id_employee, String print_date);
    void soldProductsSum(String print_date);
    void soldProductAmount(String upc, String print_date);
}
