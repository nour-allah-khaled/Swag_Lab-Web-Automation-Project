package Utities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Utities.LogsUtility;
import Utities.Utilitie;

public class LogsUtility {
    public static final String Logs_Path = "Test_out/Logs";

    // Create one shared logger instance
    private static final Logger logger = LogManager.getLogger(LogsUtility.class);

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }
}
