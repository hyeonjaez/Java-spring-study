package com.nhnacademy.main.exercise10_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PhoneDirectoryTest {

    @Test
    @DisplayName("putNumberSuccess")
    void putNumber() throws DataEntryFormatException, EmptyDataException {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.putNumber("가나다", "010-1234-1234");
        Assertions.assertEquals(phoneDirectory.getNumber("가나다"), "010-1234-1234");
    }

    @Test
    @DisplayName("Incorrect Data putNumberMethod Test")
    void incorrectDataPutNuberMethodTest() throws DataEntryFormatException {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        //phoneDirectory.putNumber("가나다", "가나다");
        Assertions.assertThrows(DataEntryFormatException.class, () -> phoneDirectory.putNumber("가나다", "가나다"));
        Assertions.assertThrows(DataEntryFormatException.class, () -> phoneDirectory.putNumber("가나다", "111-1111-1111"));
        Assertions.assertThrows(DataEntryFormatException.class, () -> phoneDirectory.putNumber("가나다", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneDirectory.putNumber("가나다", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneDirectory.putNumber(null, null));
        Assertions.assertThrows(DataEntryFormatException.class, () -> phoneDirectory.putNumber("010-1234-1234", "010-1234-1234"));
    }

    @Test
    @DisplayName("getNumber Correct Data Test")
    void getNumberTest() throws DataEntryFormatException, EmptyDataException {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.putNumber("가나다", "010-1234-1234");
        Assertions.assertEquals(phoneDirectory.getNumber("가나다"), "010-1234-1234");
    }

    @Test
    @DisplayName("getNumber InCorrect Data Test")
    void getNumberIncorrectDataTest() throws DataEntryFormatException, EmptyDataException {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.putNumber("가나다", "010-1234-1234");
        Assertions.assertThrows(NullPointerException.class, () -> phoneDirectory.getNumber("가나"));
        Assertions.assertThrows(EmptyDataException.class, () -> phoneDirectory.getNumber(""));
    }

}