package me.gabrielsalvador.utils;

public class Stopwatch {
    private static long startTime;

    public static void start() {
        startTime = System.nanoTime();
    }

    public static long stopAndPrint() {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double milliseconds = (double) duration / 1_000_000;
        System.out.println("Elapsed time: " + milliseconds + " ms");
        return duration;
    }
}
