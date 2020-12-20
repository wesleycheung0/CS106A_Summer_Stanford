/*
 * Putting Stones that are four avenue/ lane apart
 */

import stanford.karel.SuperKarel;

public class StoneMasonKarel extends SuperKarel {
    public void run() {
        //Taking Care of all Avenue except last
        while (frontIsClear()) {
            turnLeft();
            if (noBeepersPresent()) {
                putBeeper();
            }
            doYourJob();
            turnAroundNext();
        }
        //Working on the last Avenue
        turnLeft();
        doYourJob();
        turnAroundNext();
    }

    /*Karel working procedure except the last Avenue */
    public void doYourJob() {
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
            if (noBeepersPresent()) {
                putBeeper();
            }
        }
    }

    /* */
    public void turnAroundNext() {
        turnAround();
        while (frontIsClear()) {
            move();
        }
        nextLane();
    }

    /* Turn left and move to the next working avenue */
    public void nextLane() {
        turnLeft();
        if (frontIsClear()) {
            move();
            move();
            move();
            move();
        }
    }
}



