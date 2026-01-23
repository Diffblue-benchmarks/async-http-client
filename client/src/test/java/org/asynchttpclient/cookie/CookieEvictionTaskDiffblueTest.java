package org.asynchttpclient.cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.netty.handler.codec.http.cookie.CookieHeaderNames.SameSite;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
  void testNewCookieEvictionTask_thenThreadSafeCookieStoreAllEmpty2() throws Exception {
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
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   *
   * <ul>
   *   <li>When {@link Long#MIN_VALUE}.
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when MIN_VALUE; then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenMin_value_thenThreadSafeCookieStoreAllEmpty()
      throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();

    // Act
    CookieEvictionTask actualCookieEvictionTask =
        new CookieEvictionTask(Long.MIN_VALUE, cookieStore);
    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));
    actualCookieEvictionTask.run(timeout);

    // Assert that nothing has changed
    verify(timeout).timer();
    assertTrue(cookieStore.getAll().isEmpty());
    assertTrue(cookieStore.getUnderlying().isEmpty());
  }

  /**
   * Test {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when zero; then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenZero_thenThreadSafeCookieStoreAllEmpty() throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    cookieStore.incrementAndGet();

    // Act
    CookieEvictionTask actualCookieEvictionTask = new CookieEvictionTask(0L, cookieStore);
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
    DefaultCookie cookie = mock(DefaultCookie.class);
    when(cookie.maxAge()).thenReturn(1L);
    when(cookie.domain()).thenReturn("https://example.org/example");
    when(cookie.name()).thenReturn("https://example.org/example");
    when(cookie.path()).thenReturn("https://example.org/example");
    doNothing().when(cookie).setSameSite(Mockito.<SameSite>any());
    cookie.setSameSite(SameSite.Lax);

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
        uri,
        new io.netty.handler.codec.http.DefaultCookie(
            "https://example.org/example", "https://example.org/example"));
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "");

    cookieStore.add(uri2, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(cookie).domain();
    verify(cookie, atLeast(1)).maxAge();
    verify(cookie).name();
    verify(cookie).path();
    verify(cookie).setSameSite(SameSite.Lax);
    verify(timeout).timer();
  }
}
