package com.nhnacademy.jaehyeon.exercise10_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PhoneDirectoryTest {


    @DisplayName("add test")
    @Test
    void addNumber() {
        //given
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        //when
        phoneDirectory.addNumber("010-9417-3268", "정재현");

        //then
        Assertions.assertEquals(phoneDirectory.getPhoneMap().get("010-9417-3268"), "정재현");
    }

    @DisplayName("empty add Test")
    @Test
    void verifyPhoneNumberIsEmptyTest() {
        //given
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneDirectory.addNumber("", ""));
    }

    @DisplayName("Pattern Test")
    @Test
    void verifyPhoneNumberNotPatternTest() {
        //given
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        //when
        phoneDirectory.addNumber("01-1-1", "정재현");

        //then
        Assertions.assertEquals(phoneDirectory.getPhoneMap().containsValue("정재현"), false);
    }

    @DisplayName("lookupName() test")
    @Test
    void lookupNameTest() {
        //given
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        //when
        phoneDirectory.addNumber("010-1234-1234", "정재현");

        //then
        Assertions.assertEquals("정재현", phoneDirectory.lookUpName("010-1234-1234"));
    }

    @DisplayName("lookupNumber() test")
    @Test
    void lookupNumberTest() {
        //given
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        //when
        phoneDirectory.addNumber("010-1234-1234", "정재현");

        //then
        Assertions.assertEquals("010-1234-1234", phoneDirectory.lookUpNumber("정재현"));
    }


}