package com.nhnacademy.jaehyeon.exercise7_2;

public class Array {

    private final int row;
    private final int column;

    private final int[][] array;

    public Array(int row, int column) {
        this.row = row;
        this.column = column;
        this.array = new int[row][column];
        setArrayInNumber();
    }

    private void setArrayInNumber() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.array[i][j] = getRandomNumber();
            }
        }
    }

    public int[][] getArray() {
        return this.array;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * (this.row * this.column));
    }


}