package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VirtualItemTest {

    //I assumed that setting of incorrect value of sizeOnDisk should not change previous value
    @Test
    void SetNegativeSizeOnDiskNotChangeDefaultValue() {

        VirtualItem virtualItem = new VirtualItem();

        double defaultSizeOnDisk = virtualItem.getSizeOnDisk();
        virtualItem.setSizeOnDisk(-0.1);
        double newSizeOnDisk = virtualItem.getSizeOnDisk();
        assertEquals(defaultSizeOnDisk, newSizeOnDisk);

    }

}