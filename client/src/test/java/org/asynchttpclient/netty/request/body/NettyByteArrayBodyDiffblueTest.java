package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyByteArrayBodyDiffblueTest {
  /**
   * Test {@link NettyByteArrayBody#NettyByteArrayBody(byte[])}.
   * <p>
   * Method under test: {@link NettyByteArrayBody#NettyByteArrayBody(byte[])}
   */
  @Test
  @DisplayName("Test new NettyByteArrayBody(byte[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyByteArrayBody.<init>(byte[])"})
  void testNewNettyByteArrayBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteArrayBody actualNettyByteArrayBody = new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualNettyByteArrayBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteArrayBody.getContentLength());
  }

  /**
   * Test {@link NettyByteArrayBody#getContentLength()}.
   * <p>
   * Method under test: {@link NettyByteArrayBody#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long NettyByteArrayBody.getContentLength()"})
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, (new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }
}
