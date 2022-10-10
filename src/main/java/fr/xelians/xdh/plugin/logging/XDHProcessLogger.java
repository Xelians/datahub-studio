package fr.xelians.xdh.plugin.logging;


import org.slf4j.Logger;

public class XDHProcessLogger {

    private String collectId;
    private Step step;
    private Logger logger;

    public XDHProcessLogger(String collectId, Step step, Logger logger) {
        this.collectId = collectId != null ? collectId: "";
        this.step = step;
        this.logger = logger;
    }

    public void trace(String s) {
        logger.trace(collectId + " - " + step + " - " + s);
    }

    public void trace(String s, Object o) {
        logger.trace(collectId + " - " + step + " - " + s, o);
    }

    public void trace(String s, Object o, Object o1) {
        logger.trace(collectId + " - " + step + " - " + s, o, o1);
    }

    public void trace(String s, Object... objects) {
        logger.trace(collectId + " - " + step + " - " + s, objects);
    }

    public void debug(String s) {
        logger.debug(collectId + " - " + step + " - " + s);
    }

    public void debug(String s, Object o) {
        logger.debug(collectId + " - " + step + " - " + s, o);
    }

    public void debug(String s, Object o, Object o1) {
        logger.debug(collectId + " - " + step + " - " + s, o, o1);
    }

    public void debug(String s, Object... objects) {
        logger.debug(collectId + " - " + step + " - " + s, objects);
    }

    public void info(String s) {
        logger.info(collectId + " - " + step + " - " + s);
    }

    public void info(String s, Object o) {
        logger.info(collectId + " - " + step + " - " + s, o);
    }

    public void info(String s, Object o, Object o1) {
        logger.info(collectId + " - " + step + " - " + s, o, o1);
    }

    public void info(String s, Object... objects) {
        logger.info(collectId + " - " + step + " - " + s, objects);
    }

    public void warn(String s) {
        logger.warn(collectId + " - " + step + " - " + s);
    }

    public void warn(String s, Object o) {
        logger.warn(collectId + " - " + step + " - " + s, o);
    }

    public void warn(String s, Object... objects) {
        logger.warn(collectId + " - " + step + " - " + s, objects);
    }

    public void warn(String s, Object o, Object o1) {
        logger.warn(collectId + " - " + step + " - " + s, o, o1);
    }

}
