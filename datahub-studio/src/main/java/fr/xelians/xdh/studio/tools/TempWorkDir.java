package fr.xelians.xdh.studio.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides access to the temporary working directory used by workers.
 *
 * <p>
 * During an execution, {@link #dir()} returns the temporary directory of that execution, the
 * same as {@link fr.xelians.xdh.studio.WorkerContext#tempDir()}: the platform creates it before
 * the call and deletes it afterwards. Outside an execution it returns the shared directory
 * {@code ${java.io.tmpdir}/xdh}, which the platform purges periodically.
 * </p>
 *
 * @deprecated use {@link fr.xelians.xdh.studio.WorkerContext#tempDir()} instead
 */
@Deprecated(since = "4.3", forRemoval = true)
public final class TempWorkDir {

    private static final Path DIR =
            Paths.get(System.getProperty("java.io.tmpdir"), "xdh");

    private static final ThreadLocal<Path> EXECUTION_DIR = new ThreadLocal<>();

    static {
        try {
            Files.createDirectories(DIR);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to create work directory " + DIR, e);
        }
    }

    /**
     * Returns the temporary working directory.
     *
     * @return the temporary directory of the current execution, or the shared directory when
     *         called outside an execution
     */
    public static Path dir() {
        Path executionDir = EXECUTION_DIR.get();
        return executionDir != null ? executionDir : DIR;
    }

    /**
     * Binds the temporary directory of an execution to the current thread. Reserved for the
     * platform, which calls it around each worker execution.
     *
     * @param executionDir the temporary directory of the execution
     */
    public static void bind(Path executionDir) {
        EXECUTION_DIR.set(executionDir);
    }

    /**
     * Removes the binding set by {@link #bind(Path)}. Reserved for the platform.
     */
    public static void unbind() {
        EXECUTION_DIR.remove();
    }
}
