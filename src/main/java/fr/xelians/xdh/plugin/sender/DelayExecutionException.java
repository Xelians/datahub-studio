package fr.xelians.xdh.plugin.sender;

/**
 * Throw this exception to delay the transfer execution in Sender. Then you can control the frequency of messages to be sent.
 * You can combine this exception with a lock on the Sender to serialize the transfer.
 */
public class DelayExecutionException extends Exception{

    /**
     * Delay for next retry in second
     */
    private int delay;

    public DelayExecutionException(int delay) {
        this.delay = delay;
    }
}
