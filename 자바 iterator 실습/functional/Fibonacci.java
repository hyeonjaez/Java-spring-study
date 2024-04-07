package org.example.functional;

import java.math.BigInteger;
import java.util.Iterator;

public class Fibonacci implements Iterator<BigInteger> {

    private BigInteger prevCurrent = BigInteger.ZERO;
    private BigInteger current = BigInteger.ONE;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        BigInteger tmp = prevCurrent;
        prevCurrent = current;
        current = tmp.add(current);
        return tmp;
    }
}
