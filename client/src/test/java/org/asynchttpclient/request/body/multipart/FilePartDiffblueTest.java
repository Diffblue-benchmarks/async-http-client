package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FilePartDiffblueTest {
  /**
   * Test {@link FilePart#FilePart(String, File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilePart#FilePart(String, File)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File); when Property is 'java.io.tmpdir' is 'foo' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile()));

    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(), null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'foo' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile2() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8")));

  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile3() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8"), null));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile4() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), null, "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile5() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), null, "https://example.org/example", "https://example.org/example"));

  }

  /**
   * Test {@link FilePart#FilePart(String, File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilePart#FilePart(String, File)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "https://example.org/example"));
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile2() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file,
        "https://example.org/example", Charset.forName("UTF-8")));

  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile3() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8")));

  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile4() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file,
        "https://example.org/example", Charset.forName("UTF-8"), "https://example.org/example"));

  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile5() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8"), null));

  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile6() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile7() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, "https://example.org/example", Charset.forName("UTF-8"),
            "https://example.org/example", "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile8() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), null, "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile9() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), "https://example.org/example", "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile10() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, "https://example.org/example", Charset.forName("UTF-8"),
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile11() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FilePart("https://example.org/example", file, null,
        Charset.forName("UTF-8"), null, "https://example.org/example", "https://example.org/example"));

  }

  /**
   * Test
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile12() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8"),
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));

  }
}
