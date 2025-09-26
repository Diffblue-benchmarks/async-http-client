package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InputStreamBodyGeneratorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return ContentLength is minus one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link InputStreamBodyGenerator#InputStreamBodyGenerator(InputStream)}
   *   <li>{@link InputStreamBodyGenerator#getContentLength()}
   *   <li>{@link InputStreamBodyGenerator#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return ContentLength is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InputStreamBodyGenerator.<init>(InputStream)",
    "void InputStreamBodyGenerator.<init>(InputStream, long)",
    "long InputStreamBodyGenerator.getContentLength()",
    "InputStream InputStreamBodyGenerator.getInputStream()"
  })
  void testGettersAndSetters_thenReturnContentLengthIsMinusOne() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamBodyGenerator actualInputStreamBodyGenerator =
        new InputStreamBodyGenerator(inputStream);
    long actualContentLength = actualInputStreamBodyGenerator.getContentLength();
    InputStream actualInputStream = actualInputStreamBodyGenerator.getInputStream();

    // Assert
    assertEquals(-1L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return ContentLength is three.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link InputStreamBodyGenerator#InputStreamBodyGenerator(InputStream, long)}
   *   <li>{@link InputStreamBodyGenerator#getContentLength()}
   *   <li>{@link InputStreamBodyGenerator#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when three; then return ContentLength is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InputStreamBodyGenerator.<init>(InputStream)",
    "void InputStreamBodyGenerator.<init>(InputStream, long)",
    "long InputStreamBodyGenerator.getContentLength()",
    "InputStream InputStreamBodyGenerator.getInputStream()"
  })
  void testGettersAndSetters_whenThree_thenReturnContentLengthIsThree() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamBodyGenerator actualInputStreamBodyGenerator =
        new InputStreamBodyGenerator(inputStream, 3L);
    long actualContentLength = actualInputStreamBodyGenerator.getContentLength();
    InputStream actualInputStream = actualInputStreamBodyGenerator.getInputStream();

    // Assert
    assertEquals(3L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }
}
