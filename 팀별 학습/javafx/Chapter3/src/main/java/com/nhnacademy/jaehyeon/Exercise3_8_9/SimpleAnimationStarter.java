package com.nhnacademy.jaehyeon.Exercise3_8_9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This file can be used to create very simple animations.  Just fill in
 * the definition of drawFrame with the code to draw one frame of the
 * animation, and possibly change a few of the values in the rest of
 * the program as noted below.
 */
public class SimpleAnimationStarter extends Application {

    /**
     * Draws one frame of an animation. This subroutine should be called
     * about 60 times per second.  It is responsible for redrawing the
     * entire drawing area. The parameter g is used for drawing. The frameNumber
     * starts at zero and increases by 1 each time this subroutine is called.
     * The parameter elapsedSeconds gives the number of seconds since the animation
     * was started.  By using frameNumber and/or elapsedSeconds in the drawing
     * code, you can make a picture that changes over time.  That's an animation.
     * The parameters width and height give the size of the drawing area, in pixels.
     */
    public void drawFrame(GraphicsContext g, int frameNumber, double elapsedSeconds, int width, int height) {

        /* NOTE:  To get a different animation, just erase the contents of this
         * subroutine and substitute your own.
         */

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height);  // Fill drawing area with white.

        double inset; // Gap between edges of drawing area and outer rectangle.

        double rectWidth, rectHeight;   // The size of one of the rectangles.

        g.setStroke(Color.BLACK);  // Draw the rectangle outlines in black.

        inset = frameNumber % 15 + 0.5;  // (The 0.5 is a technicality that gives
        //  a sharper picture.)

        rectWidth = width - 2 * inset;
        rectHeight = height - 2 * inset;
        int centerX = 0;
        int centerY = 0;

        for (int i = 0; i < 5; i++) {
            centerY = 100 * i;
            g.setStroke(Color.BLACK);
            g.strokeRect(centerX, centerY, 5000, 100);

        }

//        for(int i = 0; i<2; i++){
//            centerY = i* 100;
//            g.setStroke(Color.BLACK);
//            g.strokeRect(cycleFrameNumer, centerY, 100,100);
//        }

        int cyclicFrameNum;
//        cyclicFrameNum = frameNumber % 1000;  // Repeats every 300 frames
//        g.setFill(Color.RED);
//        g.fillRect(cyclicFrameNum, 0, 100, 100);
//
//        cyclicFrameNum = frameNumber % 500;  // Repeats every 150 frames
//        g.setFill(Color.GREEN);
//        g.fillRect(2 * cyclicFrameNum, 100, 100, 100);
//
//        cyclicFrameNum = frameNumber % 330;  // Repeats every 100 frames
//        g.setFill(Color.BLUE);
//        g.fillRect(3 * cyclicFrameNum, 200, 100, 100);

        for (int i = 0; i < 3; i++) {
            cyclicFrameNum = frameNumber % (1000 / (i + 1));
            if (i == 0) {
                g.setFill(Color.RED);
            } else if (i == 1) {
                g.setFill(Color.GREEN);
            } else if (i == 2) {
                g.setFill(Color.BLUE);
            }
            g.fillRect(cyclicFrameNum * (i + 1), i * 100, 100, 100);
        }

        int oscilationFrameNumber;
        oscilationFrameNumber = frameNumber % (2 * 1000);
        if (oscilationFrameNumber > 1000) {
            oscilationFrameNumber = 2000 - oscilationFrameNumber;
        }
        g.setFill(Color.SKYBLUE);
        g.fillRect(oscilationFrameNumber, 300, 100, 100);

        oscilationFrameNumber = frameNumber % (2 * 500);
        if (oscilationFrameNumber > 500) {
            oscilationFrameNumber = 1000 - oscilationFrameNumber;
        }
        g.setFill(Color.YELLOW);
        g.fillRect(2 * oscilationFrameNumber, 400, 100, 100);

        oscilationFrameNumber = frameNumber % (2 * 330);
        if (oscilationFrameNumber > 330) {
            oscilationFrameNumber = 660 - oscilationFrameNumber;
        }
        g.setFill(Color.PURPLE);
        g.fillRect(3 * oscilationFrameNumber, 500, 100, 100);


//        g.setStroke(Color.BLACK);
//        g.setFill(Color.GREEN);
//        g.fillRect(cycleFrameNumer, 100, 100,100);
//
//        g.setStroke(Color.BLACK);
//        g.setFill(Color.BLUE);
//        g.fillRect(cycleFrameNumer, 200, 100,100);
//
//        g.setStroke(Color.BLACK);
//        g.setFill(Color.SKYBLUE);
//        g.fillRect(oscilationFrameNumber, 300, 100,100);
//
//        g.setStroke(Color.BLACK);
//        g.setFill(Color.PURPLE);
//        g.fillRect(oscilationFrameNumber, 400, 100,100);
//
//        g.setStroke(Color.BLACK);
//        g.setFill(Color.YELLOW);
//        g.fillRect(oscilationFrameNumber, 500, 100,100);


//        while (rectWidth >= 0 && rectHeight >= 0) {
//            g.strokeRect(inset, inset, rectWidth, rectHeight);
//            inset += 15;       // rectangles are 15 pixels apart
//            rectWidth -= 30;
//            rectHeight -= 30;
//        }


    }

    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------


    public void start(Stage stage) {
        int width = 1000;   // The width of the image.  You can modify this value!
        int height = 600;  // The height of the image. You can modify this value!
        Canvas canvas = new Canvas(width, height);
        drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Animation"); // STRING APPEARS IN WINDOW TITLEBAR!
        stage.show();
        stage.setResizable(false);
        AnimationTimer anim = new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;

            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
                } else if (now - previousTime > 0.95e9 / 60) {
                    // The test in the else-if is to make sure that drawFrame() is
                    // called about once every 1/60 second.  It is required since
                    // handle() can be called by the system more often than that.
                    frameNum++;
                    drawFrame(canvas.getGraphicsContext2D(), frameNum, (now - startTime) / 1e9, width, height);
                    previousTime = now;
                }
            }
        };
        anim.start();
    }

    public static void main(String[] args) {
        launch();
    }


    public void red(GraphicsContext g) {
        int centerX = 0;
        int centerY = 0;

        while (true) {
            centerX++;
            g.setStroke(Color.BLACK);
            g.setFill(Color.RED);
            g.strokeRect(centerX, centerY, 100, 100);
            if (centerX == 1000) {
                centerX--;
            }
        }
    }

} // end SimpleAnimationStarter