package fr.xelians.xdh.studio.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Adapts the bins to the directory-based worker methods. It is provided by the platform and used
 * by the default implementations of the bin-based methods; a worker never calls it.
 */
public interface LegacyBridge {

    /**
     * @param bin the bin to read
     * @return a local directory holding the files of the bin
     * @throws IOException if the files cannot be made available locally
     */
    Path expose(InputBin bin) throws IOException;

    /**
     * @param bin the bin to write
     * @return a local directory in which the worker writes the files to publish
     * @throws IOException if the directory cannot be prepared
     */
    Path stage(OutputBin bin) throws IOException;

    /**
     * Publishes the named files of a staging directory.
     *
     * @param bin       the bin to publish to
     * @param staged    the directory returned by {@link #stage(OutputBin)}
     * @param fileNames the names of the files to publish
     * @throws IOException if a file cannot be published
     */
    void publish(OutputBin bin, Path staged, List<String> fileNames) throws IOException;

    /**
     * Publishes every file of a staging directory.
     *
     * @param bin    the bin to publish to
     * @param staged the directory returned by {@link #stage(OutputBin)}
     * @throws IOException if a file cannot be published
     */
    void publishAll(OutputBin bin, Path staged) throws IOException;
}
