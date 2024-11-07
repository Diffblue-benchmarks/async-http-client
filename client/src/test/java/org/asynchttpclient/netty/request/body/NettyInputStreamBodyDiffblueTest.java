package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyInputStreamBodyDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return ContentLength is minus one.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyInputStreamBody#NettyInputStreamBody(InputStream)}
   *   <li>{@link NettyInputStreamBody#getContentLength()}
   *   <li>{@link NettyInputStreamBody#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return ContentLength is minus one")
  void testGettersAndSetters_thenReturnContentLengthIsMinusOne() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    NettyInputStreamBody actualNettyInputStreamBody = new NettyInputStreamBody(inputStream);
    long actualContentLength = actualNettyInputStreamBody.getContentLength();
    InputStream actualInputStream = actualNettyInputStreamBody.getInputStream();

    // Assert
    assertEquals(-1L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return ContentLength is three.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyInputStreamBody#NettyInputStreamBody(InputStream, long)}
   *   <li>{@link NettyInputStreamBody#getContentLength()}
   *   <li>{@link NettyInputStreamBody#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when three; then return ContentLength is three")
  void testGettersAndSetters_whenThree_thenReturnContentLengthIsThree() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    NettyInputStreamBody actualNettyInputStreamBody = new NettyInputStreamBody(inputStream, 3L);
    long actualContentLength = actualNettyInputStreamBody.getContentLength();
    InputStream actualInputStream = actualNettyInputStreamBody.getInputStream();

    // Assert
    assertEquals(3L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }
}
