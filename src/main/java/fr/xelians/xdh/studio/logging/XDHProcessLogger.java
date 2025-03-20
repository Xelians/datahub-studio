package fr.xelians.xdh.studio.logging;


import org.slf4j.Logger;

/**
 * The logger to use when logging inside workers.
 * This allows the application to format correctly the logs for monitoring.
 */

/**
 * @author aurelien on 10/01/2022
 */
public final class XDHProcessLogger {

    private String collectId;
    private String step;
    private Logger logger;

    public XDHProcessLogger(String collectId, Step step, Logger logger) {
        this.collectId = collectId != null ? String.format("%-5s",collectId) : "     ";
        this.step = String.format("%-14s", step);
        this.logger = logger;
    }

    public void trace(String s) {
        logger.trace(getFormattedMessage(s));
    }

    public void trace(String s, Object o) {
        logger.trace(getFormattedMessage(s), o);
    }

    public void trace(String s, Object o, Object o1) {
        logger.trace(getFormattedMessage(s), o, o1);
    }

    public void trace(String s, Object... objects) {
        logger.trace(getFormattedMessage(s), objects);
    }

    public void debug(String s) {
        logger.debug(getFormattedMessage(s));
    }

    public void debug(String s, Object o) {
        logger.debug(getFormattedMessage(s), o);
    }

    public void debug(String s, Object o, Object o1) {
        logger.debug(getFormattedMessage(s), o, o1);
    }

    public void debug(String s, Object... objects) {
        logger.debug(getFormattedMessage(s), objects);
    }

    public void info(String s) {
        logger.info(getFormattedMessage(s));
    }

    public void info(String s, Object o) {
        logger.info(getFormattedMessage(s), o);
    }

    public void info(String s, Object o, Object o1) {
        logger.info(getFormattedMessage(s), o, o1);
    }

    public void info(String s, Object... objects) {
        logger.info(getFormattedMessage(s), objects);
    }

    public void warn(String s) {
        logger.warn(getFormattedMessage(s));
    }

    public void warn(String s, Object o) {
        logger.warn(getFormattedMessage(s), o);
    }

    public void warn(String s, Object... objects) {
        logger.warn(getFormattedMessage(s), objects);
    }

    public void warn(String s, Object o, Object o1) {
        logger.warn(getFormattedMessage(s), o, o1);
    }

    private String getFormattedMessage(String s){
        return  String.format("%-12.12s",Thread.currentThread().getName()) + " - " + collectId + " - " + step + " - " + s;
    }

}
