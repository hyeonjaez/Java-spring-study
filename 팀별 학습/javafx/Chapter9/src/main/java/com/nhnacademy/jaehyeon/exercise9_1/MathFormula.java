package com.nhnacademy.jaehyeon.exercise9_1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MathFormula implements Formula {

    private final Map<BigInteger, BigInteger> fibonacciResult;

    public MathFormula() {
        this.fibonacciResult = new HashMap<>();
        settingFibonacciInitial();
    }

    @Override
    public BigInteger factorial(BigInteger number) {
        if (number.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        return number.multiply(factorial(number.subtract(BigInteger.ONE)));
    }

    @Override
    public BigInteger fibonacci(BigInteger number) {
        if (this.fibonacciResult.containsKey(number)) {
            return this.fibonacciResult.get(number);
        }

        BigInteger result = fibonacci(number.subtract(BigInteger.TWO))
                .add(fibonacci(number.subtract(BigInteger.ONE)));
        this.fibonacciResult.put(number, result);

        return this.fibonacciResult.get(number);
    }


    private void settingFibonacciInitial() {
        this.fibonacciResult.put(BigInteger.ZERO, BigInteger.ZERO);
        this.fibonacciResult.put(BigInteger.ONE, BigInteger.ONE);
    }
}
