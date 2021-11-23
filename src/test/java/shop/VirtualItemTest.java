package shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class VirtualItemTest {

    //I assumed that setting of incorrect value of sizeOnDisk should not change previous value
    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, -0.1, 0})
    void SetNegativeSizeOnDiskNotChangeDefaultValue(double size) {

        VirtualItem virtualItem = new VirtualItem();
        double defaultSizeOnDisk = virtualItem.getSizeOnDisk();

        assertEquals(defaultSizeOnDisk, 0);

        virtualItem.setSizeOnDisk(size);
        double newSizeOnDisk = virtualItem.getSizeOnDisk();

        assertEquals(newSizeOnDisk, 0);
    }
}