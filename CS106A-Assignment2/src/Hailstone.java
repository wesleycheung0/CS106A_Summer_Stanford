// TODO: comment this program

import acm.program.ConsoleProgram;

public class Hailstone extends ConsoleProgram {
    public void run() {
        hailstoneSequence();
        boolean run_Again = readBoolean("Run again?", "y", "n");
        if (run_Again) {
            while (run_Again) {
                hailstoneSequence();
                run_Again = readBoolean("Run again?", "y", "n");
            }
        }
        println("Thanks for using Hailstone");
    }

    /*Function hailstoneSequence prints Hofstadter's step to number one*/
    public void hailstoneSequence() {
        int number = readInt("Enter a number: ");
        if (number == 1) {
            println("It took 0  steps to reach 1.");
        } else {
            int step = 0;
            while (number != 1) {
                if (number % 2 == 0) {
                    println(number + "is even, so I make half: " + (number / 2));
                    number = number / 2;
                    step++;
                } else {
                    println(number + "is odd, so i make 3n+1: " + ((number * 3) + 1));
                    number = (number * 3) + 1;
                    step++;
                }
            }
            println("It took " + step + "steps to reach 1");
        }
    }
}
