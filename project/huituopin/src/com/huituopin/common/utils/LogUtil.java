package com.huituopin.common.utils;

import javax.persistence.Entity;
import org.apache.log4j.Logger;

@Entity
public class LogUtil {
    private static Logger logger = Logger.getLogger(Logger.class);

    public static void logWarn(String message) {
        logger.warn(message);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logError(String message) {
        logger.error(message);
    }

}
