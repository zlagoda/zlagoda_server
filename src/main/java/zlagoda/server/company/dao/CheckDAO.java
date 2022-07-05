package zlagoda.server.company.dao;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

import java.util.List;
import java.util.Optional;

public interface CheckDAO {
    void insertNewCheck(final Check check);
    void insertNewSale(final String checkNumber, final SoldProduct soldProduct);
    List<Check> getChecksForPeriod(String print_date);
    List<Check> getChecks();
    List<Check>  getChecksForPeriodByCashier(String idEmployee, String printDate);
    Optional<Check> getCheck(String checkNumber);
//    int soldProductsSumByCashier(String id_employee, String print_date);
//    int soldProductsSum(String print_date);
//    int soldProductAmount(String upc, String print_date);
}
