package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void AddingRealItemIncreaseTotalPrice() {

        Cart cart = new Cart("Test cart");
        assertEquals(cart.getTotalPrice(), 0);

        double itemPrice = 100;
        double itemTax = 0.2;
        RealItem realItem = new RealItem();
        realItem.setPrice(itemPrice);

        cart.addRealItem(realItem);
        assertEquals(cart.getTotalPrice(), itemPrice * (1 + itemTax));
    }

    @Test
    void DeletingRealItemDecreaseTotalPrice() {
        Cart cart = new Cart("Test cart");
        assertEquals(cart.getTotalPrice(), 0);

        double itemPrice = 100;
        double itemTax = 0.2;
        RealItem realItem = new RealItem();
        realItem.setPrice(itemPrice);

        cart.addRealItem(realItem);

        cart.deleteRealItem(realItem);
        assertEquals(0, cart.getTotalPrice());
    }
}