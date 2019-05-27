package com.dajudge.yabuto.maven;

import com.dajudge.yabuto.runtime.ScriptClasspath;
import com.dajudge.yabuto.runtime.SimpleEmitter;
import com.dajudge.yabuto.runtime.YabutoRuntime;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import static org.apache.maven.plugins.annotations.ResolutionScope.COMPILE;

@Mojo(
        name = "generate",
        requiresDependencyResolution = COMPILE,
        requiresDependencyCollection = COMPILE
)
public class GenerateMojo extends AbstractMojo {
    private static final String EXT = ".groovy";

    @Component
    MavenProject mavenProject;
    @Parameter(required = true)
    File templatesDir;
    @Parameter(required = true)
    File targetDir;
    @Parameter
    Map<String, String> parameters;

    public void execute() throws MojoExecutionException {
        final ScriptClasspath classpath = createScriptClasspath();
        final YabutoRuntime runtime = new YabutoRuntime(
                classpath,
                templatesDir,
                getRootDir(),
                new SimpleEmitter(targetDir)
        );
        if (!templatesDir.isDirectory()) {
            throw new RuntimeException("Templates directory does not exist: " + templatesDir.getAbsolutePath());
        }
        final List<File> files = Stream.of(getTemplateFiles())
                .filter(File::isFile)
                .filter(it -> it.getName().endsWith(EXT))
                .collect(toList());
        final Map<String, String> runtimeParameters = parameters == null ? emptyMap() : new HashMap<>(parameters);
        for (final File file : files) {
            try {
                runtime.evaluate(file, runtimeParameters);
            } catch (final Exception e) {
                throw new MojoExecutionException("Failed to evaluate template.", e);
            }
        }
    }

    private File[] getTemplateFiles() {
        final File[] files = templatesDir.listFiles();
        if (files == null) {
            return new File[]{};
        }
        return files;
    }

    private ScriptClasspath createScriptClasspath() throws MojoExecutionException {
        try {
            final List<String> compileClasspathElements = mavenProject.getCompileClasspathElements();
            final List<URL> urls = compileClasspathElements.stream()
                    .map(this::toURL)
                    .collect(toList());
            final URLClassLoader classLoader = new URLClassLoader(
                    urls.toArray(new URL[urls.size()]),
                    getClass().getClassLoader()
            );
            return new ScriptClasspath(compileClasspathElements, classLoader);
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException("Failed to build script classpath.", e);
        }
    }


    private URL toURL(final String path) {
        try {
            return new File(path).toURI().toURL();
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Failed to convert classpath entry to URL.", e);
        }
    }

    private File getRootDir() {
        MavenProject rootProject = mavenProject;
        while (hasParentOnDisk(rootProject)) {
            rootProject = rootProject.getParent();
        }
        return rootProject.getFile().getParentFile();
    }

    private boolean hasParentOnDisk(MavenProject rootProject) {
        if (rootProject.getParentFile() != null) {
            return true;
        }
        if (rootProject.getParent() != null && rootProject.getParent().getFile() != null) {
            return true;
        }
        return false;
    }
}
