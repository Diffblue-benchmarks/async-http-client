package org.asynchttpclient.cookie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.junit.jupiter.api.Test;

class CookieEvictionTaskDiffblueTest {
  /**
   * Method under test:
   * {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  void testNewCookieEvictionTask() throws Exception {
    // Arrange and Act
    CookieEvictionTask actualCookieEvictionTask = new CookieEvictionTask(1L, new ThreadSafeCookieStore());
    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());
    actualCookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  void testRun() throws Exception {
    // Arrange
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, new ThreadSafeCookieStore());
    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }
}
