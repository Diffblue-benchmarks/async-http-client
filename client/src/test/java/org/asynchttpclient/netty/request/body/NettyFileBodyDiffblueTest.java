package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyFileBodyDiffblueTest {
  /**
   * Test {@link NettyFileBody#NettyFileBody(File, long, long, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyFileBody#NettyFileBody(File, long, long, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyFileBody(File, long, long, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyFileBody.<init>(File, long, long, AsyncHttpClientConfig)"})
  void testNewNettyFileBody() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new NettyFileBody(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), 1L, 3L,
            mock(AsyncHttpClientConfig.class)));

  }

  /**
   * Test {@link NettyFileBody#NettyFileBody(File, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyFileBody#NettyFileBody(File, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyFileBody(File, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyFileBody.<init>(File, AsyncHttpClientConfig)"})
  void testNewNettyFileBody2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new NettyFileBody(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            mock(AsyncHttpClientConfig.class)));

  }
}
