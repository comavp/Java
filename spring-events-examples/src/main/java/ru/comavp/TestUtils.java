package ru.comavp;

public final class TestUtils {

    private TestUtils() {}

    public static void printlnWithThreadName(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}
