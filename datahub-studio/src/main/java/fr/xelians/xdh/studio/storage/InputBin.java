package fr.xelians.xdh.studio.storage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

/**
 * The files a worker has to process in one execution, read-only.
 */
public interface InputBin {

    /**
     * @return the files of the bin, sorted by name
     * @throws IOException if the bin cannot be read
     */
    List<StoredFile> files() throws IOException;

    /**
     * @param name the file name, without any path
     * @return the file of that name
     * @throws NoSuchFileException if the bin holds no such file
     * @throws IOException         if the bin cannot be read
     */
    default StoredFile get(String name) throws IOException {
        for (StoredFile file : files()) {
            if (file.name().equals(name)) {
                return file;
            }
        }
        throw new NoSuchFileException(name);
    }

    /**
     * Returns the only file of the bin. A sender always receives exactly one file.
     *
     * @return the single file of the bin
     * @throws IllegalStateException if the bin holds no file or more than one
     * @throws IOException           if the bin cannot be read
     */
    default StoredFile single() throws IOException {
        List<StoredFile> files = files();
        if (files.size() != 1) {
            throw new IllegalStateException("Expected exactly one file, found " + files.size());
        }
        return files.get(0);
    }

    /**
     * @return {@code true} if the bin holds no file
     * @throws IOException if the bin cannot be read
     */
    default boolean isEmpty() throws IOException {
        return files().isEmpty();
    }
}
