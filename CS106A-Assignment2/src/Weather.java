// TODO: Weather

import acm.program.ConsoleProgram;

public class Weather extends ConsoleProgram {
    private static final int sentinel_Value = -1;

    public void run() {
        println("CS 106A \"Weather Master 4000\"!");
        int temp = readInt("Next temperatire or " + sentinel_Value + " to quit");
        int count = 0;
        if (temp == sentinel_Value) {
            println("No temperatures were entered.");
        } else {
            double sum_temp = 0;
            int high_temp = temp;
            int low_temp = temp;
            int cold_day = 0;
            while (temp != sentinel_Value) {
                count++;
                sum_temp += temp;
                if (temp > high_temp) {
                    high_temp = temp;
                }
                if (temp < low_temp) {
                    low_temp = temp;
                }
                if (temp <= 50) {
                    cold_day++;
                }
                temp = readInt("Next temperatire or" + sentinel_Value + " to quit");
            }
            double average = sum_temp / count;
            println("Highest temperature = " + high_temp);
            println("Lowest temperature = " + low_temp);
            println("Average = " + average);
            println(cold_day + " cold day(s)");

        }
    }
}
