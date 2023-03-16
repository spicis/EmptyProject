package com.spicis.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoLogger implements ILogger {

    Logger log = LoggerFactory.getLogger("infoLogger");

    @Override
    public void logError(String message, Throwable e) {
        log.error(message, e);
    }

    @Override
    public void logError(String message) {
        log.error(message);
    }

    @Override
    public void logDebug(String message) {
        log.debug(message);
    }

    @Override
    public void logInfo(String message) {
        log.info(message);
    }
}
