package com.nhnacademy.jaehyeon.exercise7_2;

public class TransposeArray {

    private final int[][] targetArray;
    private final int[][] resultArray;
    private final int row;
    private final int column;

    public TransposeArray(Array array) {
        this.row = array.getArray()[0].length;
        this.column = array.getArray().length;
        this.targetArray = array.getArray();
        this.resultArray = new int[row][column];
        transposeArray();
    }

    private void transposeArray() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.resultArray[i][j] = this.targetArray[j][i];
            }
        }
    }

    public int[][] getResultArray() {
        return resultArray;
    }
}


