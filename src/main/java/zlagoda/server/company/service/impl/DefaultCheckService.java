package zlagoda.server.company.service.impl;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zlagoda.server.company.dao.CheckDAO;
import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.ProductInStore;
import zlagoda.server.company.entity.SoldProduct;
import zlagoda.server.company.service.CheckService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultCheckService implements CheckService {

    @Autowired
    private CheckDAO checkDAO;

    @Autowired
    private ProductInStoreDAO productInStoreDAO;

    @Override
    public void insertNewCheck(Check check) throws InvalidAttributeValueException {
        checkDAO.insertNewCheck(check);
        for (SoldProduct sale : check.getProducts()) {
            ProductInStore productInStore = productInStoreDAO.findByUPC(sale.getUPC()).orElseThrow();
            if (productInStore.getAmount() < sale.getAmount() || sale.getAmount() <= 0) {
                throw new InvalidAttributeValueException();
            }
            productInStoreDAO.updateAmountByUPC(sale.getUPC(), productInStore.getAmount() - sale.getAmount());
            checkDAO.insertNewSale(check.getNumber(), sale);
        }
    }

    @Override
    public void insertNewSale(String checkNumber, SoldProduct soldProduct) {
        checkDAO.insertNewSale(checkNumber, soldProduct);
    }

    @Override
    public List<Check> getChecksForPeriod(String print_date) {
        return checkDAO.getChecksForPeriod(print_date);
    }

	@Override
	public List<Check> getChecks()
	{
		return checkDAO.getChecks();
	}

	@Override
    public List<Check> getChecksForPeriodByCashier(String id_employee, String print_date) {
        return checkDAO.getChecksForPeriodByCashier(id_employee,print_date);
    }



    @Override
    public Check getCheck(String checkNumber) {
        return checkDAO.getCheck(checkNumber).orElseThrow();
    }

//    @Override
//    public void soldProductsSumByCashier(String id_employee, String print_date) {
//        checkDAO.soldProductsSumByCashier(id_employee,print_date);
//    }
//    @Override
//    public void soldProductsSum(String print_date) {
//        checkDAO.soldProductsSum(print_date);
//    }
//    @Override
//    public void soldProductAmount(String upc, String print_date) {
//        checkDAO.soldProductAmount(upc, print_date);
//    }
}
