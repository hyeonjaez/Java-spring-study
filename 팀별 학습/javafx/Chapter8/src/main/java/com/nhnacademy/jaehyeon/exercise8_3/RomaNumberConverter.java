package com.nhnacademy.jaehyeon.exercise8_3;

import java.util.Arrays;
import java.util.Objects;

public class RomaNumberConverter {
    static final String[] romaLetter = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    static final int[] romaLetterValue = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private int targetNumber;
    private String targetRoma;

    public RomaNumberConverter(int targetNumber) {
        verifyTargetNumber(targetNumber);
        this.targetNumber = targetNumber;
    }

    public void verifyTargetNumber(int number) {
        if (!(1 <= number && number <= 3999)) {
            throw new NumberFormatException("1부터 3999까지의 숫자만 넣으세여");
        }
    }

    public RomaNumberConverter(String targetRoma) {
        verifyTargetRoma(targetRoma);
        this.targetRoma = targetRoma;
    }

    public void verifyTargetRoma(String roma) {
        for (String ro : romaLetter) {
            if (Objects.equals(ro, roma.split("")[0])) {
                return;
            }
        }
        throw new NumberFormatException("로마 숫자를 입력하세요");
    }

    public int convertRomaToNumber() {
        int resultNumber = 0;
        int preValue = 0;

        for (int i = this.targetRoma.length() - 1; i >= 0; i--) {
            char ch = this.targetRoma.charAt(i);
            int index = Arrays.asList(romaLetter).indexOf(String.valueOf(ch));
            int romavalue = romaLetterValue[index];

            if (romavalue < preValue) {
                resultNumber -= romavalue;
            } else {
                resultNumber += romavalue;
            }
            preValue = romavalue;
        }
        return resultNumber;
    }

    public String convertNumberToRoma() {
        int targetNumber = this.targetNumber;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < romaLetterValue.length; i++) {
            int romaValue = romaLetterValue[i];
            String roma = romaLetter[i];

            while (targetNumber >= romaValue) {
                sb.append(roma);
                targetNumber -= romaValue;
            }
        }
        return sb.toString();
    }
}
