package org.asynchttpclient.cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CookieEvictionTaskDiffblueTest {
  /**
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   *
   * <ul>
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>When {@link ThreadSafeCookieStore} (default constructor).
   *   <li>Then calls {@link Timeout#timer()}.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when ThreadSafeCookieStore (default constructor); then calls timer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
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

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun2() throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    cookieStore.incrementAndGet();
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(Long.MAX_VALUE, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun3() throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    cookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    cookieStore.incrementAndGet();
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(0L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun4() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setCommentUrl("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    cookieStore.incrementAndGet();
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName("Test run(Timeout)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun5() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setCommentUrl("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            Uri.HTTPS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    cookieStore.incrementAndGet();
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <ul>
   *   <li>Given {@link HashedWheelTimer#HashedWheelTimer(long, TimeUnit)} with tickDuration is one
   *       and unit is {@code NANOSECONDS}.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName(
      "Test run(Timeout); given HashedWheelTimer(long, TimeUnit) with tickDuration is one and unit is 'NANOSECONDS'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun_givenHashedWheelTimerWithTickDurationIsOneAndUnitIsNanoseconds() throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    cookieStore.incrementAndGet();
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }
}
