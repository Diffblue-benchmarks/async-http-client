package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FileBodyGeneratorDiffblueTest {
  /**
   * Test {@link FileBodyGenerator#FileBodyGenerator(File)}.
   *
   * <p>Method under test: {@link FileBodyGenerator#FileBodyGenerator(File)}
   */
  @Test
  @DisplayName("Test new FileBodyGenerator(File)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileBodyGenerator.<init>(File)"})
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
   *
   * <p>Method under test: {@link FileBodyGenerator#FileBodyGenerator(File, long, long)}
   */
  @Test
  @DisplayName("Test new FileBodyGenerator(File, long, long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileBodyGenerator.<init>(File, long, long)"})
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
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FileBodyGenerator#getFile()}
   *   <li>{@link FileBodyGenerator#getRegionLength()}
   *   <li>{@link FileBodyGenerator#getRegionSeek()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "File FileBodyGenerator.getFile()",
    "long FileBodyGenerator.getRegionLength()",
    "long FileBodyGenerator.getRegionSeek()"
  })
  void testGettersAndSetters() {
    // Arrange
    FileBodyGenerator fileBodyGenerator =
        new FileBodyGenerator(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Act
    File actualFile = fileBodyGenerator.getFile();
    long actualRegionLength = fileBodyGenerator.getRegionLength();

    // Assert
    assertEquals("test.txt", actualFile.getName());
    assertEquals(0L, actualRegionLength);
    assertEquals(0L, fileBodyGenerator.getRegionSeek());
    assertTrue(actualFile.isAbsolute());
  }

  /**
   * Test {@link FileBodyGenerator#createBody()}.
   *
   * <p>Method under test: {@link FileBodyGenerator#createBody()}
   */
  @Test
  @DisplayName("Test createBody()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.request.body.RandomAccessBody FileBodyGenerator.createBody()"
  })
  void testCreateBody() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            new FileBodyGenerator(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())
                .createBody());
  }
}
