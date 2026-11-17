package fr.xelians.xdh.studio.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinDefaultsTest {

    @TempDir
    Path inputDirectory;
    @TempDir
    Path outputDirectory;

    @Test
    void getReturnsTheNamedFile() throws IOException {
        Files.writeString(inputDirectory.resolve("a.xml"), "a");
        Files.writeString(inputDirectory.resolve("b.xml"), "bb");
        InputBin bin = new DirectoryBins.Input(inputDirectory);

        assertEquals(2, bin.get("b.xml").size());
        assertThrows(NoSuchFileException.class, () -> bin.get("c.xml"));
    }

    @Test
    void singleRequiresExactlyOneFile() throws IOException {
        InputBin bin = new DirectoryBins.Input(inputDirectory);
        assertTrue(bin.isEmpty());
        assertThrows(IllegalStateException.class, bin::single);

        Files.writeString(inputDirectory.resolve("a.xml"), "a");
        assertFalse(bin.isEmpty());
        assertEquals("a.xml", bin.single().name());

        Files.writeString(inputDirectory.resolve("b.xml"), "b");
        assertThrows(IllegalStateException.class, bin::single);
    }

    @Test
    void putCopiesAnInputFileUnderTheSameName() throws IOException {
        Files.writeString(inputDirectory.resolve("a.xml"), "content");
        OutputBin output = new DirectoryBins.Output(outputDirectory);

        output.put(new DirectoryBins.Input(inputDirectory).single());

        assertEquals("content", Files.readString(outputDirectory.resolve("a.xml")));
        assertEquals(List.of("a.xml"), output.published());
    }
}
