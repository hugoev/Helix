import java.util.Scanner;
import java.awt.*;
import java.util.Random;

public class Helix{

    public static void main(String[] args){
        System.out.println("UTSA - Fall - 2023 - CS1083 - Section 006 - Prj 3 - Helix written by Hugo Villarreal");
        drawHelix();
    }

    public static void drawHelix() {
        // declarations
        DrawingPanel panel = new DrawingPanel(400, 400);
        Graphics g = panel.getGraphics();
        Scanner scnr = new Scanner(System.in);
        int colorNum;
        int speed;
        boolean clockwise;
        int[] coordinates = new int[4];
        int direction = 0;
        int lineCycles;
        // forms/strings to be shown in the screen (in graphical mode)
        // GRAPHICS
        panel.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawRect(100, 100, 200, 200);
        g.fillRect(100, 100, 200, 200);

        // Strings on GRAPHICS
        g.drawString("UTSA - CS1083 - Section 006 - Prj 3 - Helix - Hugo Villarreal", 15,  50);

        // user input (in text mode)
        System.out.print("Please, input the speed (1-10): ");
        speed = scnr.nextInt();
        String speedString = Integer.toString(speed);

        System.out.print("Please, input the number of times the line will be shown: ");
        lineCycles = scnr.nextInt();
        scnr.close();

        // speed on GRAPHICS
        g.drawString("speed: " + speedString, 173, 75);

        // new initialization for COORDINATES
        coordinates[0] = 100;
        coordinates[1] = 100;
        coordinates[2] = 300;
        coordinates[3] = 300;

        // loop
        for (int i = 0 ; i <= lineCycles; ++i) {
            // Clear the area at the beginning of the loop
            g.setColor(Color.WHITE);
            g.fillRect(100, 100, 200, 300); // Adjust this if necessary to cover the old line and text

            // Draw the white box border again after clearing
            g.setColor(Color.BLACK);
            g.drawRect(100, 100, 200, 200);

            g.setColor(Color.BLACK);
            g.fillRect(100, 300, 200, 100);


            // Set color for the line
            g.setColor(getColor());

            // Draw the line
            g.drawLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

            // Draw the coordinates text
            String coordinates0 = Integer.toString(coordinates[0]);
            String coordinates1 = Integer.toString(coordinates[1]);
            String coordinates2 = Integer.toString(coordinates[2]);
            String coordinates3 = Integer.toString(coordinates[3]);
            g.setColor(Color.WHITE);
            g.drawString("(" + coordinates0 + "," + coordinates1 + "),(" + coordinates2 + "," + coordinates3 + ")" , 140, 350);

            // Draw the iteration count text
            g.drawString("i: " + i, 195, 325);

            // Update the direction and coordinates
            direction = newPos(direction, coordinates);

            // Implement the delay
            panel.sleep(100/speed);
        }

        // After the loop, redraw the last line to ensure it stays
        g.setColor(getColor());
        //   g.drawLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

        // Close the scanner resource
        scnr.close();
    }


    public static int newPos(int lastDirection, int[] coordinates) {
        final int step = 10; // Movement step size
        // Assuming the square's bounds are known and fixed:
        final int leftBound = 100;
        final int rightBound = 300;
        final int upperBound = 100;
        final int lowerBound = 300;

        switch (lastDirection) {
            case 0: // Moving right
                if (coordinates[0] < rightBound) {
                    coordinates[0] += step;
                    coordinates[2] -= step;
                } else {
                    // When x1 reaches the right bound and x2 is at the left bound, start moving down
                    lastDirection = 1; // Change direction to down
                }
                break;
            case 1: // Moving down
                // Increment y1 and decrement y2 until reaching the lower bound
                if (coordinates[1] < lowerBound) {
                    coordinates[1] += step;
                    coordinates[3] -= step;
                } else {
                    // When y1 reaches the lower bound, start moving left
                    lastDirection = 2; // Change direction to left
                }
                break;
            case 2: // Moving left
                if (coordinates[0] > leftBound) {
                    coordinates[0] -= step;
                    coordinates[2] += step;
                } else {
                    // When x1 reaches the left bound, start moving up
                    lastDirection = 3; // Change direction to up
                }
                break;
            case 3: // Moving up
                // Decrement y1 and increment y2 until reaching the upper bound
                if (coordinates[1] > upperBound) {
                    coordinates[1] -= step;
                    coordinates[3] += step;
                } else {
                    // When y1 reaches the upper bound, start moving right
                    lastDirection = 0; // Change direction to right
                }
                break;
        }

        return lastDirection;
    }





    public static Color getColor() {
        Random random = new Random();
        int randomNumber = random.nextInt(9);

        switch (randomNumber) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.GRAY;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.RED;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.PINK;
            case 6:
                return Color.DARK_GRAY;
            case 7:
                return Color.BLUE;
            case 8:
                return Color.BLACK;
            default:
                return Color.BLACK;
        }
    }
}
