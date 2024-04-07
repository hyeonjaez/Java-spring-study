package com.nhnacademy.jaehyeon.exercise10_1;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneDirectory {
    static Pattern pattern;
    private final TreeMap<String, String> phoneMap;



    public PhoneDirectory() {
        phoneMap = new TreeMap<>();
    }

    public void addNumber(String number, String name) {
        if (name.isEmpty() || number.isEmpty()) {
            throw new IllegalArgumentException("name and number cannot be null");
        }

        if (verifyPhoneNumber(number) && verifyName(name)) {
            this.phoneMap.put(number, name);
        }

    }

    private boolean verifyPhoneNumber(String number) {
        try {
            String phoneNumberPattern = "^01[016789]-\\d{3,4}-\\d{4}$";

            pattern = Pattern.compile(phoneNumberPattern);
            Matcher matcher = pattern.matcher(number);

            if (!matcher.matches()) {
                throw new NotCorrectPhoneNumberFormatException("전화번호를 알맞게 입력하세요");
            }
            return true;
        } catch (NotCorrectPhoneNumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean verifyName(String name) {
        String namePattern = "^[a-zA-Z가-힣]+$";
        pattern = Pattern.compile(namePattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public String lookUpName(String number) {
        verifyPhoneNumber(number);
        String findName;
        findName = this.phoneMap.get(number);
        isExist(findName);
        return findName;
    }

    public String lookUpNumber(String name) {
        String findNumber = null;
        for (Map.Entry<String, String> entry : this.phoneMap.entrySet()) {
            if (Objects.equals(entry.getValue(), name)) {
                findNumber = entry.getKey();
                break;
            }
        }
        isExist(findNumber);

        return findNumber;

    }

    private void isExist(String find) {
        try {
            if (find == null) {
                throw new NothingLookingForException("찾기를 원하시는 항목이 없습니다.");
            }
        } catch (NothingLookingForException e) {
            System.out.println(e.getMessage());
        }
    }

    public TreeMap<String, String> getPhoneMap() {
        return phoneMap;
    }


}