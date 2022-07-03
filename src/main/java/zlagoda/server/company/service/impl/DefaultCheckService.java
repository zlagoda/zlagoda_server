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
}
