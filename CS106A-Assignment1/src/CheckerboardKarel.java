/*
 * A Program that create a checkerboard patten inside a rectangular world
 */

import stanford.karel.SuperKarel;

public class CheckerboardKarel extends SuperKarel {
    public void run() {
        putBeeper();
        if (frontIsClear()) {
            while (frontIsClear()) {
                putEveryTwo();
                turn();
            }
        } else {
            turnLeft();
            putEveryTwo();
        }
    }


    /* Put beeper in every other spot*/
    public void putEveryTwo() {
        int i;
        i = 0;
        while (frontIsClear()) {
            if (frontIsClear()) {
                move();
                i++;
            }
            if (i == 2) {
                putBeeper();
                i = 0;
            }
        }
    }

    /* Handling edge case at the corner and turning*/
    public void turn() {
        if (facingEast()) {
            turnLeft();
        }
        if (beepersPresent() && frontIsClear()) {
            move();
            turnLeft();
            move();
            putBeeper();
        } else if (frontIsClear()) {
            move();
            turnLeft();
            putBeeper();
        } else if (facingWest()) {
            turnRight();
            if (beepersPresent() && frontIsClear()) {
                move();
                turnRight();
                move();
                putBeeper();
            } else if (frontIsClear()) {
                move();
                turnRight();
                putBeeper();

            }
        }

    }


}





