package fr.xelians.xdh.studio.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Where a worker publishes the files it produces.
 *
 * <p>Publication is atomic: a file becomes visible to the platform and to the next step once it
 * is complete, never before. A file name is a plain name, without any path.</p>
 */
public interface OutputBin {

    /**
     * Opens a new file for writing. The file is published when the stream is closed; if the
     * worker fails before closing it, nothing is published.
     *
     * @param name the name of the file to publish
     * @return a stream to write the file content to
     * @throws IOException if the file cannot be created
     */
    OutputStream create(String name) throws IOException;

    /**
     * Publishes a file produced on the local disk, typically in
     * {@link fr.xelians.xdh.studio.WorkerContext#tempDir()}. The local file may be moved.
     *
     * @param name      the name of the file to publish
     * @param localFile the local file to publish
     * @throws IOException if the file cannot be published
     */
    void put(String name, Path localFile) throws IOException;

    /**
     * Publishes an input file unchanged, under the same name.
     *
     * @param file the file to copy
     * @throws IOException if the file cannot be published
     */
    default void put(StoredFile file) throws IOException {
        try (InputStream in = file.open(); OutputStream out = create(file.name())) {
            in.transferTo(out);
        }
    }

    /**
     * Derives a name that is not used in the bin yet, keeping the extension.
     *
     * @param desired the preferred file name
     * @return {@code desired} if it is free, otherwise a variant of it
     * @throws IOException if the bin cannot be read
     */
    String uniqueName(String desired) throws IOException;

    /**
     * @return the names of the files published during this execution, in publication order
     * @throws IOException if the bin cannot be read
     */
    List<String> published() throws IOException;
}
