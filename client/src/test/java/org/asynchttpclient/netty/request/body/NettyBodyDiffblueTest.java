package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyBodyDiffblueTest {
  /**
   * Test {@link NettyBody#getContentTypeOverride()}.
   * <p>
   * Method under test: {@link NettyBody#getContentTypeOverride()}
   */
  @Test
  @DisplayName("Test getContentTypeOverride()")
  void testGetContentTypeOverride() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).getContentTypeOverride());
  }
}
