package util;

public class Logger {

    public static synchronized void log(String message) {
        System.out.println("[Time: " + System.currentTimeMillis() % 100000 + "] " + message);
    }
}