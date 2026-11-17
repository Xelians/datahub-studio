package fr.xelians.xdh.studio.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Directory-backed bins and bridge standing in for the platform in tests.
 */
public final class DirectoryBins {

    private DirectoryBins() {
    }

    public static final class Input implements InputBin {

        private final Path directory;

        public Input(Path directory) {
            this.directory = directory;
        }

        @Override
        public List<StoredFile> files() throws IOException {
            try (Stream<Path> paths = Files.list(directory)) {
                return paths.filter(Files::isRegularFile).sorted().<StoredFile>map(LocalFile::new).toList();
            }
        }
    }

    public static final class Output implements OutputBin {

        private final Path directory;
        private final List<String> published = new ArrayList<>();

        public Output(Path directory) {
            this.directory = directory;
        }

        @Override
        public OutputStream create(String name) throws IOException {
            published.add(name);
            return Files.newOutputStream(directory.resolve(name));
        }

        @Override
        public void put(String name, Path localFile) throws IOException {
            Files.copy(localFile, directory.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            published.add(name);
        }

        @Override
        public String uniqueName(String desired) {
            return Files.exists(directory.resolve(desired)) ? "1-" + desired : desired;
        }

        @Override
        public List<String> published() {
            return List.copyOf(published);
        }
    }

    /**
     * Exposes input bins in place and stages output bins in a separate directory, so that a
     * test can tell what was written from what was published.
     */
    public static final class Bridge implements LegacyBridge {

        private final Path staging;

        public Bridge(Path staging) {
            this.staging = staging;
        }

        @Override
        public Path expose(InputBin bin) {
            return ((Input) bin).directory;
        }

        @Override
        public Path stage(OutputBin bin) {
            return staging;
        }

        @Override
        public void publish(OutputBin bin, Path staged, List<String> fileNames) throws IOException {
            for (String fileName : fileNames) {
                bin.put(fileName, staged.resolve(fileName));
            }
        }

        @Override
        public void publishAll(OutputBin bin, Path staged) throws IOException {
            try (Stream<Path> paths = Files.list(staged)) {
                publish(bin, staged, paths.map(path -> path.getFileName().toString()).sorted().toList());
            }
        }
    }

    private record LocalFile(Path path) implements StoredFile {

        @Override
        public String name() {
            return path.getFileName().toString();
        }

        @Override
        public long size() {
            try {
                return Files.size(path);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public Instant lastModified() {
            try {
                return Files.getLastModifiedTime(path).toInstant();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public InputStream open() throws IOException {
            return Files.newInputStream(path);
        }

        @Override
        public Path materialize() {
            return path;
        }
    }
}
