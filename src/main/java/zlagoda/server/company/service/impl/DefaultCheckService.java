package zlagoda.server.company.service.impl;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zlagoda.server.company.dao.CheckDAO;
import zlagoda.server.company.dao.CustomerCardDAO;
import zlagoda.server.company.dao.EmployeeDAO;
import zlagoda.server.company.dao.ProductInStoreDAO;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;
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

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private CustomerCardDAO cardDAO;

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
    public List<Check> getAllChecksInfo() {
        List<Check> checks = checkDAO.getAllChecksInfo();
        for (Check item : checks) {
            Employee employee = employeeDAO.findById(item.getEmployee().getId()).orElseThrow();
            item.setEmployee(employee);
        }
        return checks;
    }

    @Override
    public Check getCheck(String checkNumber) {
        Check check = checkDAO.getCheckByNumber(checkNumber).orElseThrow();
        Employee employee = employeeDAO.findById(check.getEmployee().getId()).orElseThrow();
        check.setEmployee(employee);
        List<SoldProduct> soldProducts = checkDAO.getProductsInCheck(checkNumber);
        check.setProducts(soldProducts);
        if (check.getCard().getNumber() != null) {
            CustomerCard card = cardDAO.findByNumber(check.getCard().getNumber()).orElseThrow();
            check.setCard(card);
        }
        return check;
    }

    @Override
    public List<Check> getChecksConsistCategory(List<Integer> categoryIds, boolean only) {
        List<Check> checks = null;
        if (only) {
            checks = checkDAO.getChecksConsistOnlyCategory(categoryIds);
        } else {
            checks = checkDAO.getChecksConsistCategory(categoryIds);
        }
        for (Check item : checks) {
            Employee employee = employeeDAO.findById(item.getEmployee().getId()).orElseThrow();
            item.setEmployee(employee);
        }
        return checks;
    }

    @Override
    public List<Check> getChecksConsistProducts(List<Integer> productIds) {
        List<Check> checks = checkDAO.getChecksConsistProduct(productIds);
        for (Check item : checks) {
            Employee employee = employeeDAO.findById(item.getEmployee().getId()).orElseThrow();
            item.setEmployee(employee);
        }
        return checks;
    }

    @Override
    public List<Check> getChecksByEmployee(String employeeNumber) {
        List<Check> checks = checkDAO.getChecksByEmployee(employeeNumber);
        for (Check item : checks) {
            Employee employee = employeeDAO.findById(item.getEmployee().getId()).orElseThrow();
            item.setEmployee(employee);
        }
        return checks;
    }

    /*
     * @Override
     * public List<Check> getChecksForPeriod(String print_date) {
     * return checkDAO.getChecksForPeriod(print_date);
     * }
     * 
     * @Override
     * public List<Check> getChecksForPeriodByCashier(String id_employee, String
     * print_date) {
     * return checkDAO.getChecksForPeriodByCashier(id_employee, print_date);
     * }
     */

    // @Override
    // public void soldProductsSumByCashier(String id_employee, String print_date) {
    // checkDAO.soldProductsSumByCashier(id_employee,print_date);
    // }
    // @Override
    // public void soldProductsSum(String print_date) {
    // checkDAO.soldProductsSum(print_date);
    // }
    // @Override
    // public void soldProductAmount(String upc, String print_date) {
    // checkDAO.soldProductAmount(upc, print_date);
    // }
}
