package org.asynchttpclient.cookie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CookieEvictionTaskDiffblueTest {
  /**
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   * <ul>
   *   <li>When {@link ThreadSafeCookieStore} (default constructor).</li>
   *   <li>Then calls {@link Timeout#timer()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName("Test new CookieEvictionTask(long, CookieStore); when ThreadSafeCookieStore (default constructor); then calls timer()")
  void testNewCookieEvictionTask_whenThreadSafeCookieStore_thenCallsTimer() throws Exception {
    // Arrange and Act
    CookieEvictionTask actualCookieEvictionTask = new CookieEvictionTask(1L, new ThreadSafeCookieStore());
    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());
    actualCookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   * <ul>
   *   <li>Then calls {@link Timeout#timer()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout); then calls timer()")
  void testRun_thenCallsTimer() throws Exception {
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
