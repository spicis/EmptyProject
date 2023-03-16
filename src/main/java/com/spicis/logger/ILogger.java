package com.spicis.logger;

public interface ILogger {

    void logError(String message, Throwable e);

    void logError(String message);

    void logDebug(String message);

    void logInfo(String message);
}
