package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyByteBufferBodyDiffblueTest {
  /**
   * Test
   * {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer, CharSequence)}.
   * <p>
   * Method under test:
   * {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer, CharSequence)}
   */
  @Test
  @DisplayName("Test new NettyByteBufferBody(ByteBuffer, CharSequence)")
  void testNewNettyByteBufferBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody = new NettyByteBufferBody(
        ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")), AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }

  /**
   * Test {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer)}.
   * <ul>
   *   <li>Then return ContentTypeOverride is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer)}
   */
  @Test
  @DisplayName("Test new NettyByteBufferBody(ByteBuffer); then return ContentTypeOverride is 'null'")
  void testNewNettyByteBufferBody_thenReturnContentTypeOverrideIsNull() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody = new NettyByteBufferBody(
        ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertNull(actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyByteBufferBody#getContentLength()}
   *   <li>{@link NettyByteBufferBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    NettyByteBufferBody nettyByteBufferBody = new NettyByteBufferBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Act
    long actualContentLength = nettyByteBufferBody.getContentLength();

    // Assert
    assertNull(nettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualContentLength);
  }

  /**
   * Test {@link NettyByteBufferBody#byteBuf()}.
   * <ul>
   *   <li>Given {@link ByteBuffer#put(byte[], int, int)} with {@code AXAXAXAX}
   * Bytes is {@code UTF-8} and one and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyByteBufferBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); given put(byte[], int, int) with 'AXAXAXAX' Bytes is 'UTF-8' and one and one")
  void testByteBuf_givenPutWithAxaxaxaxBytesIsUtf8AndOneAndOne() throws UnsupportedEncodingException {
    // Arrange
    ByteBuffer bb = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    bb.put("AXAXAXAX".getBytes("UTF-8"), 1, 1);

    // Act and Assert
    assertTrue((new NettyByteBufferBody(bb)).byteBuf().isContiguous());
  }
}
