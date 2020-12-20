/*
 * Find the mid-point of the rectangular space
 */

import stanford.karel.SuperKarel;

public class MidpointFinderKarel extends SuperKarel {

    public void run() {
        travelNorthEast();
        travelSouthWest();
        putBeeper();
    }

    /*Traveling to North East corner*/
    private void travelNorthEast() {
        while (frontIsClear()) {
            move();
            turnLeft();
            move();
            turnRight();
        }
    }

    /*Traveling to South West corner*/
    private void travelSouthWest() {
        turnRight();
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                turnRight();
                move();
                turnLeft();
            }
        }
    }
}
