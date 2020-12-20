// A program to solve quadratic equation

import acm.program.ConsoleProgram;

public class QuadraticEquation extends ConsoleProgram {
    public void run() {
        println("CS 106A Quadratic Solver!");
        int a = readInt("Enter a:");
        int b = readInt("Enter b:");
        int c = readInt("Enter c:");

        double discriminant = (b * b) - (4 * a * c);

        if (discriminant < 0) {

            println("No Real Root");

        } else {

            double answer_1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double answer_2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            if (answer_1 == answer_2) {

                println("One root: " + answer_1);
            } else {
                println("Two roots: " + answer_1 + " and " + answer_2);
            }
        }
    }
}
