package com.nhnacademy.jaehyeon.exercise5_2;

public class StatCalc {
    private int count;
    private double sum;
    private double squareSum;

    private double max;
    private double min;


    public void enter(double num) {
        count++;
        sum += num;
        squareSum += num * num;
        if (this.max < num) {
            this.max = num;
        }

        if (this.min > num) {
            this.min = num;
        }
    }


    public int getCount() {
        return count;
    }


    public double getSum() {
        return sum;
    }


    public double getMean() {
        return sum / count;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getStandardDeviation() { // 표준편차
        double mean = getMean();
        return Math.sqrt(squareSum / count - mean * mean); // 분산 -> 표준편차
    }

    @Override
    public String toString() {
        return "StatCalc{"
                + "count=" + count
                + ", sum=" + sum
                + ", squareSum=" + squareSum
                + ", max=" + max
                + ", min=" + min
                + ", standardDeviation=" + getStandardDeviation() + "}";
    }
}

