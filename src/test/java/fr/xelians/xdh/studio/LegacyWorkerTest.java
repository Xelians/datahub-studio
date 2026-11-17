package fr.xelians.xdh.studio;

import fr.xelians.xdh.studio.collector.Collector;
import fr.xelians.xdh.studio.logging.Step;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.sender.DelayExecutionException;
import fr.xelians.xdh.studio.sender.Sender;
import fr.xelians.xdh.studio.sender.SenderResult;
import fr.xelians.xdh.studio.storage.DirectoryBins;
import fr.xelians.xdh.studio.storage.InputBin;
import fr.xelians.xdh.studio.storage.LegacyBridge;
import fr.xelians.xdh.studio.storage.OutputBin;
import fr.xelians.xdh.studio.transformer.TransformResult;
import fr.xelians.xdh.studio.transformer.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.helpers.NOPLogger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Workers written against the directory-based methods, called through the bin-based ones.
 */
class LegacyWorkerTest {

    @TempDir
    Path inputDirectory;
    @TempDir
    Path stagingDirectory;
    @TempDir
    Path outputDirectory;

    private InputBin input;
    private OutputBin output;
    private WorkerContext context;

    @BeforeEach
    void setUp() {
        input = new DirectoryBins.Input(inputDirectory);
        output = new DirectoryBins.Output(outputDirectory);
        context = new TestContext(new DirectoryBins.Bridge(stagingDirectory), "webhook body");
    }

    @Test
    void collectPublishesTheFilesTheCollectorReturns() throws Exception {
        Collector collector = (targetDirectory, webhook, logger) -> {
            Files.writeString(targetDirectory.resolve("collected.xml"), webhook);
            Files.writeString(targetDirectory.resolve("left-by-another-collect.xml"), "other");
            return List.of("collected.xml");
        };

        StepResult result = collector.collect(output, context);

        assertEquals(List.of("collected.xml"), output.published());
        assertEquals("webhook body", Files.readString(outputDirectory.resolve("collected.xml")));
        assertFalse(result.hasWarning());
    }

    @Test
    void collectPublishesNothingWhenTheCollectorReturnsNoFile() throws Exception {
        Collector collector = (targetDirectory, webhook, logger) -> null;

        collector.collect(output, context);

        assertEquals(List.of(), output.published());
    }

    @Test
    void transformPassesTheInputNamesAndPublishesTheProducedFile() throws Exception {
        Files.writeString(inputDirectory.resolve("a.xml"), "a");
        Files.writeString(inputDirectory.resolve("b.xml"), "b");
        TransformResult expected = new TransformResult("merged.xml", "merged with a warning", true);
        Transformer transformer = (fileNames, sourceDirectory, targetDirectory, logger) -> {
            StringBuilder merged = new StringBuilder();
            for (String fileName : fileNames) {
                merged.append(Files.readString(sourceDirectory.resolve(fileName)));
            }
            Files.writeString(targetDirectory.resolve("merged.xml"), merged);
            return expected;
        };

        StepResult result = transformer.transform(input, output, context);

        assertSame(expected, result);
        assertEquals(List.of("merged.xml"), output.published());
        assertEquals("ab", Files.readString(outputDirectory.resolve("merged.xml")));
    }

    @Test
    void transformPublishesNothingWhenTheTransformerProducesNoFile() throws Exception {
        Files.writeString(inputDirectory.resolve("a.xml"), "a");
        Transformer transformer = (fileNames, sourceDirectory, targetDirectory, logger) -> new TransformResult(null);

        transformer.transform(input, output, context);

        assertEquals(List.of(), output.published());
    }

    @Test
    void sendPassesTheSingleFileAndPublishesEveryResultFile() throws Exception {
        Files.writeString(inputDirectory.resolve("sip.zip"), "sip");
        Sender sender = (fileName, sourceDirectory, resultDirectory, logger) -> {
            Files.writeString(resultDirectory.resolve("atr.xml"), Files.readString(sourceDirectory.resolve(fileName)));
            Files.writeString(resultDirectory.resolve("receipt.txt"), "ok");
            return new SenderResult("sent");
        };

        SenderResult result = sender.send(input, output, context);

        assertEquals("sent", result.getMessage());
        assertEquals(List.of("atr.xml", "receipt.txt"), output.published());
        assertEquals("sip", Files.readString(outputDirectory.resolve("atr.xml")));
    }

    @Test
    void sendPublishesNothingWhenTheSenderPostponesTheExecution() throws Exception {
        Files.writeString(inputDirectory.resolve("sip.zip"), "sip");
        Sender sender = (fileName, sourceDirectory, resultDirectory, logger) -> {
            Files.writeString(resultDirectory.resolve("partial.xml"), "partial");
            throw new DelayExecutionException(60);
        };

        assertThrows(DelayExecutionException.class, () -> sender.send(input, output, context));
        assertEquals(List.of(), output.published());
    }

    @Test
    void sendRejectsABinThatDoesNotHoldExactlyOneFile() {
        Sender sender = (fileName, sourceDirectory, resultDirectory, logger) -> new SenderResult("sent");

        assertThrows(IllegalStateException.class, () -> sender.send(input, output, context));
    }

    @Test
    void aBinBasedWorkerIsNotRoutedThroughTheDirectoryBasedMethod() throws Exception {
        Collector collector = new Collector() {
            @Override
            public StepResult collect(OutputBin collected, WorkerContext context) throws IOException {
                try (OutputStream out = collected.create("native.xml")) {
                    out.write("native".getBytes(StandardCharsets.UTF_8));
                }
                return new StepResult("1 file collected");
            }

            @Override
            public List<String> collect(Path targetDirectory, String webhook, XDHProcessLogger logger) {
                throw new UnsupportedOperationException();
            }
        };

        StepResult result = collector.collect(output, context);

        assertEquals("1 file collected", result.getMessage());
        assertEquals(List.of("native.xml"), output.published());
    }

    private record TestContext(LegacyBridge legacyBridge, String webhook) implements WorkerContext {

        @Override
        public XDHProcessLogger logger() {
            return new XDHProcessLogger(null, Step.COLLECT, NOPLogger.NOP_LOGGER);
        }

        @Override
        public ChannelStore store() {
            return null;
        }

        @Override
        public ChannelMetricsRepository metrics() {
            return null;
        }

        @Override
        public Path tempDir() {
            return null;
        }

        @Override
        public String channelId() {
            return "channel";
        }

        @Override
        public String collectId() {
            return null;
        }
    }
}
