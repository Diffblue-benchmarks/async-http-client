package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FilePartDiffblueTest {
  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String); when '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String)"})
  void testNewFilePart_whenDot() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8"), "."));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String); when '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String)"})
  void testNewFilePart_whenDot2() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                ".",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String,
   * String)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File, String, Charset, String, String, String); when '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String, String)"})
  void testNewFilePart_whenDot3() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                ".",
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File)}
   */
  @Test
  @DisplayName("Test new FilePart(String, File); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile()));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile(),
                null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile3() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile4() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example", file, null, Charset.forName("UTF-8"), null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile5() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                null,
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'foo' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsFooToFile6() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                null,
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile3() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
                null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile4() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                "https://example.org/example",
                Charset.forName("UTF-8")));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile5() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new FilePart("https://example.org/example", file, null, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile6() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                "https://example.org/example",
                Charset.forName("UTF-8"),
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile7() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example", file, null, Charset.forName("UTF-8"), null));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile8() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile9() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                "https://example.org/example",
                Charset.forName("UTF-8"),
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile10() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                null,
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile11() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile12() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                "https://example.org/example",
                Charset.forName("UTF-8"),
                "https://example.org/example",
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile13() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                null,
                "https://example.org/example",
                "https://example.org/example"));
  }

  /**
   * Test {@link FilePart#FilePart(String, File, String, Charset, String, String, String)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link FilePart#FilePart(String, File, String, Charset, String, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new FilePart(String, File, String, Charset, String, String, String); when Property is 'java.io.tmpdir' is 'test.txt' toFile")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilePart.<init>(String, File, String, Charset, String, String, String)"})
  void testNewFilePart_whenPropertyIsJavaIoTmpdirIsTestTxtToFile14() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new FilePart(
                "https://example.org/example",
                file,
                null,
                Charset.forName("UTF-8"),
                "https://example.org/example",
                "https://example.org/example",
                "https://example.org/example"));
  }
}
