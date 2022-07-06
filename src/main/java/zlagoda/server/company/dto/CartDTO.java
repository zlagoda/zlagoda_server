package zlagoda.server.company.dto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import zlagoda.server.company.entity.CustomerCard;

public class CartDTO {
    private HashMap<String, CartItemDTO> cart;
    private CustomerCard customerCard;

    public CartDTO() {
        cart = new HashMap<>();
    }

    public List<CartItemDTO> getCart() {
        return new LinkedList<CartItemDTO>(cart.values());
    }
    
    public void addProduct(CartItemDTO item) {
        cart.put(item.getUPC(), item);
    }

    public void removeProduct(final String UPC) {
        cart.remove(UPC);
    }

    public void setCustomerCard(CustomerCard customerCard) {
        this.customerCard = customerCard;
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public void clear() {
        cart.clear();
        customerCard = null;
    }
}
