package com.nhnacademy.jaehyeon;

public class Problem5 {

    public static ArrayProcessor counter(double value) {
        return array -> {
            int count = 0;
            for (double a : array) {
                if (a == value) {
                    count++;
                }
            }
            return count;
        };
    }

    public static final ArrayProcessor max = array -> {
        double max = 0;
        for (double a : array) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    };

    public static final ArrayProcessor sum = array -> {
        double sum = 0;
        for (double a : array) {
            sum += a;
        }
        return sum;
    };


    public static ArrayProcessor average =
            array -> sum.apply(array) / array.length;
}


