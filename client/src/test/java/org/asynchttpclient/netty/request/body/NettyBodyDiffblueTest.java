package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class NettyBodyDiffblueTest {
  /**
   * Method under test: {@link NettyBody#getContentTypeOverride()}
   */
  @Test
  void testGetContentTypeOverride() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).getContentTypeOverride());
  }
}
