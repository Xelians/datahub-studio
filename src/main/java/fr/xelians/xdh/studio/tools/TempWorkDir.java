package fr.xelians.xdh.studio.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides access to the temporary working directory used by connectors.
 *
 * <p>
 * This directory must be used by collectors, transformers and senders
 * whenever they need to:</p>
 * <ul>
 *     <li>Write temporary files</li>
 *     <li>Unzip archives</li>
 *     <li>Generate intermediate artifacts</li>
 *     <li>Perform file-based processing</li>
 * </ul>
 *
 * <p>
 * ⚠ Connectors are responsible for deleting any files or subdirectories
 * they create once processing is complete.
 * </p>
 *
 * <p>
 * The application periodically purges this directory to prevent uncontrolled
 * disk usage growth.
 * </p>
 *
 * <p>
 * The directory is created automatically at application startup under:
 * {@code ${java.io.tmpdir}/xdh}
 * </p>
 */
public final class TempWorkDir {

    private static final Path DIR =
            Paths.get(System.getProperty("java.io.tmpdir"), "xdh");

    static {
        try {
            Files.createDirectories(DIR);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to create work directory " + DIR, e);
        }
    }

    /**
     * Returns the root temporary working directory for connectors.
     *
     * @return the temporary working directory path
     */
    public static Path dir() {
        return DIR;
    }
}
