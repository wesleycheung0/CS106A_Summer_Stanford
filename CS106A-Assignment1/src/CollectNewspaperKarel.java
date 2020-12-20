/*
 * The class CollectNewspaperKarel is to collect newspaper from the destinated position back
 * to starting position
 */

import stanford.karel.Karel;

public class CollectNewspaperKarel extends Karel {

    /* Method run is the main function consisted all method to collect newspaper*/
    public void run() {
        moveToNewspaper();
        pickBeeper();
        moveBack();
    }

    /*Function that move Karel to the newspaper*/
    public void moveToNewspaper() {
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
    }

    /*Function that move back to startin position*/
    public void moveBack() {
        turnLeft();
        turnLeft();
        move();
        move();
        move();
        turnRight();
        move();
        putBeeper();
        turnRight();
    }

    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
