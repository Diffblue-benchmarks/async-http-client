package org.asynchttpclient.netty.request.body;

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

class NettyInputStreamBodyDiffblueTest {
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
   *   <li>{@link NettyInputStreamBody#NettyInputStreamBody(InputStream)}
   *   <li>{@link NettyInputStreamBody#getContentLength()}
   *   <li>{@link NettyInputStreamBody#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return ContentLength is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyInputStreamBody.<init>(InputStream)",
    "void NettyInputStreamBody.<init>(InputStream, long)",
    "long NettyInputStreamBody.getContentLength()",
    "InputStream NettyInputStreamBody.getInputStream()"
  })
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
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return ContentLength is three.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NettyInputStreamBody#NettyInputStreamBody(InputStream, long)}
   *   <li>{@link NettyInputStreamBody#getContentLength()}
   *   <li>{@link NettyInputStreamBody#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when three; then return ContentLength is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyInputStreamBody.<init>(InputStream)",
    "void NettyInputStreamBody.<init>(InputStream, long)",
    "long NettyInputStreamBody.getContentLength()",
    "InputStream NettyInputStreamBody.getInputStream()"
  })
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
