package com.dajudge.ymlgen.api.util;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class StreamUtil {
    private StreamUtil() {
    }

    public static String loadUtf8FromStream(final String streamName, final StreamFactory streamFactory) {
        return new String(loadBytes(streamName, streamFactory), UTF_8);
    }

    public static byte[] loadBytes(final String streamName, final StreamFactory streamFactory) {
        try (final InputStream is = streamFactory.open()) {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) > 0) {
                bos.write(buffer, 0, read);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + streamName, e);
        }
    }

    public static String loadUtf8FromFile(final File file) {
        return loadUtf8FromStream(file.getAbsolutePath(), () -> new FileInputStream(file));
    }

    public static String loadUtf8FromResource(final String resource, final ClassLoader cl) {
        return loadUtf8FromStream("classpath:" + resource, () -> resource(resource, cl));
    }

    private static InputStream resource(final String resource, final ClassLoader cl) throws FileNotFoundException {
        final InputStream stream = cl.getResourceAsStream(resource);
        if (stream == null) {
            throw new FileNotFoundException("Could not find resource " + resource + " in classpath.");
        }
        return stream;
    }

    public interface StreamFactory {
        InputStream open() throws IOException;
    }
}
