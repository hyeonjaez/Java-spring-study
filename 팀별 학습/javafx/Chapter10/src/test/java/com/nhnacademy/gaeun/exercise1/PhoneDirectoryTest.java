package com.nhnacademy.gaeun.exercise1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneDirectoryTest {

    @Test
    @DisplayName("find test")
    void findTest() {
        PhoneDirectory kaeunPhoneDirectory = new PhoneDirectory();
        kaeunPhoneDirectory.putNumber("엄마", "010-4610-0000");
        kaeunPhoneDirectory.putNumber("아빠", "010-2680-0000");

        assertEquals(kaeunPhoneDirectory.find("엄마"), 1);
        assertEquals(kaeunPhoneDirectory.find("동생"), -1);
    }

    @Test
    @DisplayName("getNumber test")
    void getNumberTest() {
        PhoneDirectory kaeunPhoneDirectory = new PhoneDirectory();
        kaeunPhoneDirectory.putNumber("엄마", "010-4610-0000");
        kaeunPhoneDirectory.putNumber("아빠", "010-2680-0000");

        assertEquals(kaeunPhoneDirectory.getNumber("엄마"), "010-1234-1234");
        assertEquals(kaeunPhoneDirectory.getNumber("아빠"), "010-0000-0000");
        assertThrows(IllegalArgumentException.class, () -> kaeunPhoneDirectory.getNumber("개구리"));
    }

    @Test
    @DisplayName("putNumber test")
    void putNumberTest() {
        PhoneDirectory kaeunPhoneDirectory = new PhoneDirectory();
        kaeunPhoneDirectory.putNumber("엄마", "010-1234-1234");
        kaeunPhoneDirectory.putNumber("아빠", "010-0000-0000");

        assertEquals(kaeunPhoneDirectory.getTreeMapData().size(), 2);
        assertThrows(IllegalArgumentException.class, () -> kaeunPhoneDirectory.putNumber("", ""));
    }
}