package zlagoda.server.company.service;

import javax.management.InvalidAttributeValueException;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

import java.util.List;
import java.util.Optional;

public interface CheckService {
    void insertNewCheck(Check check) throws InvalidAttributeValueException;
    void insertNewSale(String checkNumber, SoldProduct soldProduct);
    List<Check> getChecksForPeriod(String print_date);
    List<Check> getChecksForPeriodByCashier(String id_employee, String print_date);
    Check getCheck(String check_number);
//    void soldProductsSumByCashier(String id_employee, String print_date);
//    void soldProductsSum(String print_date);
//    void soldProductAmount(String upc, String print_date);
}
