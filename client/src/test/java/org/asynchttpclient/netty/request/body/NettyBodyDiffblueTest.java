package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyBodyDiffblueTest {
  /**
   * Test {@link NettyBody#getContentTypeOverride()}.
   *
   * <p>Method under test: {@link NettyBody#getContentTypeOverride()}
   */
  @Test
  @DisplayName("Test getContentTypeOverride()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.CharSequence NettyBody.getContentTypeOverride()"})
  void testGetContentTypeOverride() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull(new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8")).getContentTypeOverride());
  }
}
