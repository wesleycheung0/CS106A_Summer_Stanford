// Rocket

import acm.program.ConsoleProgram;

public class Rocket extends ConsoleProgram {
    public static final int size = 40;

    public void run() {
        top();
        middle_top();
        middle_bottom();
        top();
    }

    public void top() {
        for (int i = 0; i < (size + 1); i++) {
            for (int q = (size - i + 1); q > 0; q--) {
                print(" ");
            }
            for (int j = 0; j < i; j++) {
                print("/");
            }
            for (int k = 0; k < i; k++) {
                print("\\");
            }
            for (int q = (size - i); q > 0; q--) {
                print(" ");
            }
            println();
        }
    }

    public void middle_line() {
        print("+");
        for (int i = 0; i < size * 2; i++)
            print("=");
        print("+");
        println();
    }

    public void middle_top() {
        middle_line();
        for (int i = 0; i < size; i++) {
            print("|");
            for (int j = (size - i - 1); j > 0; j--) {
                print(".");
            }
            for (int k = 0; k < i + 1; k++) {
                print("/");
                print("\\");
            }
            for (int l = (size - i - 1); l > 0; l--) {
                print(".");
            }
            print("|");
            println();
        }
    }

    public void middle_bottom() {

        for (int i = 0; i < size; i++) {
            print("|");
            for (int u = 0; u < i; u++) {
                print(".");
            }
            for (int l = (size - i); l > 0; l--) {
                print("\\");
                print("/");
            }
            for (int k = 0; k < i; k++) {
                print(".");
            }
            print("|");
            println();
        }
        middle_line();
    }


}





