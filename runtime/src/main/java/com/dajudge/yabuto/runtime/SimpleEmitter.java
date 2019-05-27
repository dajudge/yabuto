package com.dajudge.yabuto.runtime;

import java.io.*;

public class SimpleEmitter implements YabutoRuntime.Emitter {
    private final File targetDir;

    public SimpleEmitter(final File targetDir) {
        this.targetDir = targetDir;
    }

    @Override
    public File emit(final String templateName, final String yaml) {
        if (yaml == null) {
            return null;
        }
        final String targetName = templateName + ".yml";
        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new RuntimeException("Failed to create target directory");
        }
        final File targetFile = new File(targetDir, targetName);
        try (
                final FileOutputStream fos = new FileOutputStream(targetFile);
                final OutputStreamWriter writer = new OutputStreamWriter(fos);
                final PrintWriter printer = new PrintWriter(writer)
        ) {
            printer.write(yaml);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to emit " + targetFile.getAbsolutePath(), e);
        }
        return targetFile;
    }
}
