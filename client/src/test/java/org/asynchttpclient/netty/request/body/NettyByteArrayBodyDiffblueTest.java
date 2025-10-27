package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class NettyByteArrayBodyDiffblueTest {
  /**
   * Method under test: {@link NettyByteArrayBody#getContentLength()}
   */
  @Test
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, (new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link NettyByteArrayBody#NettyByteArrayBody(byte[])}
   */
  @Test
  void testNewNettyByteArrayBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteArrayBody actualNettyByteArrayBody = new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualNettyByteArrayBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteArrayBody.getContentLength());
  }
}
