package zlagoda.server.company.converter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zlagoda.server.company.dto.CartDTO;
import zlagoda.server.company.dto.CartItemDTO;
import zlagoda.server.company.entity.Check;
import zlagoda.server.company.entity.CustomerCard;
import zlagoda.server.company.entity.Employee;
import zlagoda.server.company.entity.SoldProduct;
import zlagoda.server.company.service.EmployeeService;


@Component
public class CartDTOConverter implements Converter<CartDTO, Check>
{
	@Autowired
	EmployeeService employeeService;

	@Autowired
	CartItemDTOConverter cartItemDTOConverter;

	@Override
	public Check convert(CartDTO cartDTO)
	{
		Check check = new Check();
		Employee employee = employeeService.getCurrent();
		check.setEmployee(employee);
		CustomerCard card = cartDTO.getCustomerCard();
		check.setCard(card);
		BigDecimal totalSum = new BigDecimal(0);
		LinkedList<SoldProduct> products = new LinkedList<>();
		for (CartItemDTO item : cartDTO.getCart())
		{
			products.add(cartItemDTOConverter.convert(item));
			totalSum = totalSum.add(item.getPrice().multiply(new BigDecimal(item.getAmount())));
		}
		check.setProducts(products);
		if (card != null)
		{
			totalSum = totalSum.divide(new BigDecimal("100"));
			int discount = 100 - card.getPercent();
			totalSum = totalSum.multiply(new BigDecimal(discount));
		}
		check.setTotalSum(totalSum);
		BigDecimal VAT = totalSum.multiply(new BigDecimal("0.2"));
		check.setVAT(VAT);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		check.setPrintDate(timestamp);
		check.setNumber(String.valueOf(System.currentTimeMillis() / 1000));
		return check;
	}
}
