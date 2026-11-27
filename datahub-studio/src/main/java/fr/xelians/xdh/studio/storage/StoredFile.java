package fr.xelians.xdh.studio.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.Instant;

/**
 * A file of an {@link InputBin}. A stored file is always complete: it is never visible while
 * being written.
 */
public interface StoredFile {

    /**
     * @return the file name, without any path
     */
    String name();

    /**
     * @return the size in bytes
     */
    long size();

    /**
     * @return the last modification time
     */
    Instant lastModified();

    /**
     * Opens the file for reading. The caller closes the stream.
     *
     * @return a stream on the file content
     * @throws IOException if the file cannot be read
     */
    InputStream open() throws IOException;

    /**
     * Returns the file as a local path, for libraries that cannot read from a stream. Depending
     * on the storage, the path is the file itself or a copy made in the temporary directory of
     * the execution. It must be treated as read-only.
     *
     * @return a local path to the file content
     * @throws IOException if the file cannot be made available locally
     */
    Path materialize() throws IOException;
}
