package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.Test;

class NettyByteBufferBodyDiffblueTest {
  /**
   * Method under test: {@link NettyByteBufferBody#byteBuf()}
   */
  @Test
  void testByteBuf() throws UnsupportedEncodingException {
    // Arrange
    ByteBuffer bb = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    bb.put("AXAXAXAX".getBytes("UTF-8"), 1, 1);

    // Act and Assert
    assertTrue((new NettyByteBufferBody(bb)).byteBuf().isContiguous());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NettyByteBufferBody#getContentLength()}
   *   <li>{@link NettyByteBufferBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
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
   * Method under test:
   * {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer)}
   */
  @Test
  void testNewNettyByteBufferBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody = new NettyByteBufferBody(
        ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertNull(actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }

  /**
   * Method under test:
   * {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer, CharSequence)}
   */
  @Test
  void testNewNettyByteBufferBody2() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody = new NettyByteBufferBody(
        ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")), AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }
}
