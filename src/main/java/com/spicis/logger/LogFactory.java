package com.spicis.logger;

public class LogFactory {

    private static ILogger errorLogger = new ErrorLogger();

    private static ILogger infoLogger = new InfoLogger();

    public static ILogger getErrorLogger() {
        return errorLogger;
    }

    public static ILogger getInfoLogger() {
        return infoLogger;
    }

}
