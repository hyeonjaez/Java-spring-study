package com.nhnacademy.jaehyeon.problem6_7;

public class RandomConvert {

    static final int ROWS = 80;
    static final int COLUMNS = 80;
    static final int SQUARE_SIZE = 5;

    static int currentRow;    // Row currently containing the disturbance.
    static int currentColumn; // Column currently containing disturbance.


    public static void main(String[] args) {
        Mosaic.setUse3DEffect(false);
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        fillWithRandomColors();

        currentRow = ROWS / 2;   // start at center of window
        currentColumn = COLUMNS / 2;

        while (true) {

            changeToOneColor(currentRow, currentColumn);
            convertRandomNeighbor();
            Mosaic.delay(1);
        }
    }

    static void fillWithRandomColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                changeToRandomColor(row, col);
            }
        }
    }

    static void changeToRandomColor(int rowNum, int colNum) {
        int red = (int) (256 * Math.random());
        int green = (int) (256 * Math.random());
        int blue = (int) (256 * Math.random());
        Mosaic.setColor(rowNum, colNum, red, green, blue);
    }

    static void changeToOneColor(int rowNum, int colNum) {
        int row = rowNum;
        int col = colNum + 1;
        if (row > 0 && col >= COLUMNS) {
            row -= 1;
            col -= 1;
        } else if (row < ROWS - 1 && col >= COLUMNS) {
            row += 1;
            col -= 1;
        }

        int red = Mosaic.getRed(row, col);
        int green = Mosaic.getGreen(row, col);
        int blue = Mosaic.getBlue(row, col);

        Mosaic.setColor(rowNum, colNum, red, green, blue);
    }


    static void convertRandomNeighbor() {

        int directionNum = (int) (4 * Math.random());
        switch (directionNum) {
            case 0: {
                currentRow--;
                if (currentRow < 0) {
                    currentRow = ROWS - 1;
                }
                break;
            }
            case 1: {
                currentColumn++;
                if (currentColumn >= COLUMNS) {
                    currentColumn = 0;
                }
                break;
            }
            case 2: {
                currentRow++;
                if (currentRow >= ROWS) {
                    currentRow = 0;
                }
                break;
            }
            case 3: {
                currentColumn--;
                if (currentColumn < 0) {
                    currentColumn = COLUMNS - 1;
                }
                break;
            }
        }
    }

}