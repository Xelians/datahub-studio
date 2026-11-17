package fr.xelians.xdh.studio.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SuppressWarnings("removal")
class TempWorkDirTest {

    @TempDir
    Path executionDirectory;

    @Test
    void dirReturnsTheBoundDirectoryOnTheBindingThreadOnly() throws Exception {
        Path shared = TempWorkDir.dir();

        TempWorkDir.bind(executionDirectory);
        try {
            assertEquals(executionDirectory, TempWorkDir.dir());
            assertEquals(shared, CompletableFuture.supplyAsync(TempWorkDir::dir).get());
        } finally {
            TempWorkDir.unbind();
        }

        assertNotEquals(executionDirectory, TempWorkDir.dir());
        assertEquals(shared, TempWorkDir.dir());
    }
}
