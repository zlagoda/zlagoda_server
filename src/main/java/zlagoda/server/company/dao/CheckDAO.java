package zlagoda.server.company.dao;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

import java.util.List;
import java.util.Optional;

public interface CheckDAO {
    void insertNewCheck(final Check check);

    void insertNewSale(final String checkNumber, final SoldProduct soldProduct);

    List<Check> getAllChecksInfo();

    Optional<Check> getCheckByNumber(String checkNumber);

    List<Check> getChecksConsistCategory(List<Integer> categoryIds);

    List<Check> getChecksConsistOnlyCategory(List<Integer> categoryIds);

    List<Check> getChecksConsistProduct(List<Integer> productIds);

    List<Check> getChecksByEmployee(final String employeeNumber);

    List<SoldProduct> getProductsInCheck(final String checkNumber);

    List<Check> getChecksForPeriod(String print_date);

    List<Check> getChecksForPeriodByCashier(String idEmployee, String printDate);
    // int soldProductsSumByCashier(String id_employee, String print_date);
    // int soldProductsSum(String print_date);
    // int soldProductAmount(String upc, String print_date);
}
