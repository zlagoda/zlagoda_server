package zlagoda.server.company.dao;

import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.SoldProduct;

public interface CheckDAO {
    void insertNewCheck(final Check check);
    void insertNewSale(final String checkNumber, final SoldProduct soldProduct);
}
