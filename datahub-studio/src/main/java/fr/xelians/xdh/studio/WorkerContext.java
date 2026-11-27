package fr.xelians.xdh.studio;

import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.storage.LegacyBridge;

import java.nio.file.Path;

/**
 * Services and information available to a worker during one execution.
 */
public interface WorkerContext {

    /**
     * @return the logger of the execution; the only logger a worker should use
     */
    XDHProcessLogger logger();

    /**
     * @return the key-value store of the channel for this worker
     */
    ChannelStore store();

    /**
     * @return the metrics repository of the channel
     */
    ChannelMetricsRepository metrics();

    /**
     * Returns the temporary directory of the execution, the only place where a worker writes on
     * the local disk. The platform creates it before the call and deletes it afterwards, whether
     * the execution succeeds or fails.
     *
     * @return the temporary directory of the execution
     */
    Path tempDir();

    /**
     * @return the identifier of the channel
     */
    String channelId();

    /**
     * @return the identifier of the collect being processed, or {@code null} during a collect
     */
    String collectId();

    /**
     * @return the body of the webhook that triggered the collect, or {@code null}
     */
    String webhook();

    /**
     * @return the bridge used by the default implementations of the bin-based methods
     */
    LegacyBridge legacyBridge();
}
