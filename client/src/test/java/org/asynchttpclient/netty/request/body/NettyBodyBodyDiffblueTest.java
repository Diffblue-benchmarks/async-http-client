package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.request.body.Body;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyBodyBodyDiffblueTest {
  /**
   * Test {@link NettyBodyBody#getContentLength()}.
   *
   * <p>Method under test: {@link NettyBodyBody#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long NettyBodyBody.getContentLength()"})
  void testGetContentLength() {
    // Arrange
    Body body = mock(Body.class);
    when(body.getContentLength()).thenReturn(3L);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(body, mock(AsyncHttpClientConfig.class));

    // Act
    long actualContentLength = nettyBodyBody.getContentLength();

    // Assert
    verify(body).getContentLength();
    assertEquals(3L, actualContentLength);
  }
}
