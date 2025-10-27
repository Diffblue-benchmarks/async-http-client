package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

class InputStreamBodyGeneratorDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link InputStreamBodyGenerator#InputStreamBodyGenerator(InputStream)}
   *   <li>{@link InputStreamBodyGenerator#getContentLength()}
   *   <li>{@link InputStreamBodyGenerator#getInputStream()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamBodyGenerator actualInputStreamBodyGenerator = new InputStreamBodyGenerator(inputStream);
    long actualContentLength = actualInputStreamBodyGenerator.getContentLength();
    InputStream actualInputStream = actualInputStreamBodyGenerator.getInputStream();

    // Assert
    assertEquals(-1L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link InputStreamBodyGenerator#InputStreamBodyGenerator(InputStream, long)}
   *   <li>{@link InputStreamBodyGenerator#getContentLength()}
   *   <li>{@link InputStreamBodyGenerator#getInputStream()}
   * </ul>
   */
  @Test
  void testGettersAndSetters2() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamBodyGenerator actualInputStreamBodyGenerator = new InputStreamBodyGenerator(inputStream, 3L);
    long actualContentLength = actualInputStreamBodyGenerator.getContentLength();
    InputStream actualInputStream = actualInputStreamBodyGenerator.getInputStream();

    // Assert
    assertEquals(3L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }
}
