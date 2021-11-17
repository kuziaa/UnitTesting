package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealItemTest {

    //I assumed that setting of incorrect value of weight should not change previous value
    @Test
    void SetNegativeWeightNotChangeDefaultValue() {

        RealItem realItem = new RealItem();

        double defaultWeight = realItem.getWeight();
        realItem.setWeight(-0.1);
        double newWeight = realItem.getWeight();
        assertEquals(defaultWeight, newWeight);

    }
}