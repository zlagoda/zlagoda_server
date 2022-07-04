package zlagoda.server.company.dao;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

public interface CheckDAO {
    void insertNewCheck(final Check check);
    void insertNewSale(final String checkNumber, final SoldProduct soldProduct);
    void getChecksForPeriod(String print_date);
    void getChecksForPeriodByCashier(String print_date);
    void getChecksForPeriodByCashier(String id_employee, String print_date);
    void getCheck(String check_number);
    void soldProductsSumByCashier(String id_employee, String print_date);
    void soldProductsSum(String print_date);
    void soldProductAmount(String upc, String print_date);
}
