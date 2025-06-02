package org.asynchttpclient.cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CookieEvictionTaskDiffblueTest {
  /**
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   * <ul>
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName("Test new CookieEvictionTask(long, CookieStore); then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_thenThreadSafeCookieStoreAllEmpty() {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();

    // Act
    new CookieEvictionTask(1L, cookieStore);

    // Assert that nothing has changed
    assertTrue(cookieStore.getAll().isEmpty());
    assertTrue(cookieStore.getUnderlying().isEmpty());
  }

  /**
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   * <ul>
   *   <li>When {@link ThreadSafeCookieStore} (default constructor).</li>
   *   <li>Then calls {@link Timeout#timer()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName("Test new CookieEvictionTask(long, CookieStore); when ThreadSafeCookieStore (default constructor); then calls timer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenThreadSafeCookieStore_thenCallsTimer() throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();

    // Act
    CookieEvictionTask actualCookieEvictionTask = new CookieEvictionTask(1L, cookieStore);
    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());
    actualCookieEvictionTask.run(timeout);

    // Assert that nothing has changed
    verify(timeout).timer();
    assertTrue(cookieStore.getAll().isEmpty());
    assertTrue(cookieStore.getUnderlying().isEmpty());
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
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
