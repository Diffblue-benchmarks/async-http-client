package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileBodyGeneratorDiffblueTest {
  /**
   * Test {@link FileBodyGenerator#FileBodyGenerator(File)}.
   * <p>
   * Method under test: {@link FileBodyGenerator#FileBodyGenerator(File)}
   */
  @Test
  @DisplayName("Test new FileBodyGenerator(File)")
  void testNewFileBodyGenerator() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    FileBodyGenerator actualFileBodyGenerator = new FileBodyGenerator(file);

    // Assert
    assertEquals(0L, actualFileBodyGenerator.getRegionLength());
    assertEquals(0L, actualFileBodyGenerator.getRegionSeek());
    assertSame(file, actualFileBodyGenerator.getFile());
  }

  /**
   * Test {@link FileBodyGenerator#FileBodyGenerator(File, long, long)}.
   * <p>
   * Method under test:
   * {@link FileBodyGenerator#FileBodyGenerator(File, long, long)}
   */
  @Test
  @DisplayName("Test new FileBodyGenerator(File, long, long)")
  void testNewFileBodyGenerator2() {
    // Arrange
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    FileBodyGenerator actualFileBodyGenerator = new FileBodyGenerator(file, 1L, 3L);

    // Assert
    assertEquals(1L, actualFileBodyGenerator.getRegionSeek());
    assertEquals(3L, actualFileBodyGenerator.getRegionLength());
    assertSame(file, actualFileBodyGenerator.getFile());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileBodyGenerator#getFile()}
   *   <li>{@link FileBodyGenerator#getRegionLength()}
   *   <li>{@link FileBodyGenerator#getRegionSeek()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    FileBodyGenerator fileBodyGenerator = new FileBodyGenerator(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Act
    File actualFile = fileBodyGenerator.getFile();
    long actualRegionLength = fileBodyGenerator.getRegionLength();
    long actualRegionSeek = fileBodyGenerator.getRegionSeek();

    // Assert
    assertEquals("test.txt", actualFile.getName());
    assertEquals(0L, actualRegionLength);
    assertEquals(0L, actualRegionSeek);
    assertTrue(actualFile.isAbsolute());
  }

  /**
   * Test {@link FileBodyGenerator#createBody()}.
   * <p>
   * Method under test: {@link FileBodyGenerator#createBody()}
   */
  @Test
  @DisplayName("Test createBody()")
  void testCreateBody() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new FileBodyGenerator(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
            .createBody());
  }
}
