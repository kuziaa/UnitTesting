package shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RealItemTest {

    //I assumed that setting of incorrect value of weight should not change previous value
    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, -0.1, 0})
    void SetNegativeWeightNotChangeDefaultValue(double weight) {

        RealItem realItem = new RealItem();
        double defaultWeight = realItem.getWeight();

        assertEquals(defaultWeight, 0);

        realItem.setWeight(weight);
        double newWeight = realItem.getWeight();

        assertEquals(newWeight, 0);
    }
}