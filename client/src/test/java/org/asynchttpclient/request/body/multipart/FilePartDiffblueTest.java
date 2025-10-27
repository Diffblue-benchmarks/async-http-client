package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class FilePartDiffblueTest {
  /**
   * Method under test: {@link FilePart#FilePart(String, File)}
   */
  @Test
  void testNewFilePart() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile()));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example"));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example", null));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null, null));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example", null,
            "https://example.org/example"));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null, null));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null,
            "https://example.org/example"));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null, null, null));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example", null,
            "https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null, null,
            "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null,
            "https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null, null, null,
            "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example", null,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null, null,
            "https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null, null,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example",
            Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null, null, null,
            "https://example.org/example", "https://example.org/example"));
  }
}
