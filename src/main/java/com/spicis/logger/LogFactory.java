package com.spicis.logger;

public class LogFactory {
    private static ILogger errorLogger = new ErrorLogger();
    private static ILogger infoLogger = new InfoLogger();
    private static ILogger traceLogger = new TraceLogger();

    public static ILogger getErrorLogger() {
        return errorLogger;
    }

    public static ILogger getInfoLogger() {
        return infoLogger;
    }

    public static ILogger getTraceLogger() {
        return traceLogger;
    }


}
