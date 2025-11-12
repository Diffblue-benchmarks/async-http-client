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
   *   <li>When {@link Long#MAX_VALUE}.
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when MAX_VALUE; then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenMax_value_thenThreadSafeCookieStoreAllEmpty()
      throws Exception {
    // Arrange
    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    cookieStore.incrementAndGet();

    // Act
    CookieEvictionTask actualCookieEvictionTask =
        new CookieEvictionTask(Long.MAX_VALUE, cookieStore);
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
   *   <li>When {@link ThreadSafeCookieStore} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when ThreadSafeCookieStore (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenThreadSafeCookieStore() {
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
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#CookieEvictionTask(long, CookieStore)}
   */
  @Test
  @DisplayName(
      "Test new CookieEvictionTask(long, CookieStore); when ThreadSafeCookieStore (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.<init>(long, CookieStore)"})
  void testNewCookieEvictionTask_whenThreadSafeCookieStore2() throws Exception {
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
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setDomain("https://example.org/example");

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
  void testRun2() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

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
  void testRun3() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
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
  void testRun4() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(1L);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
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
    cookie.setMaxAge(1L);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));

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
  void testRun6() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(Long.MIN_VALUE, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));

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
  void testRun7() throws Exception {
    // Arrange
    io.netty.handler.codec.http.cookie.DefaultCookie cookie =
        new io.netty.handler.codec.http.cookie.DefaultCookie(
            "https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));

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
  void testRun8() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun9() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setSecure(true);
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun10() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun11() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setSecure(true);
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1999082385L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun12() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(0L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun13() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setMaxAge(0L);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(0L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun14() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setMaxAge(Long.MIN_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(0L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun15() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setComment("https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setMaxAge(Long.MIN_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(0L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1));

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
  void testRun16() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            Integer.MIN_VALUE,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
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
  void testRun17() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setPartitioned(true);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            Integer.MIN_VALUE,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
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
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(Long.MAX_VALUE);
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer(1L, TimeUnit.NANOSECONDS));

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }

  /**
   * Test {@link CookieEvictionTask#run(Timeout)}.
   *
   * <ul>
   *   <li>Given {@link HashedWheelTimer#HashedWheelTimer()}.
   *   <li>When {@link Timeout} {@link Timeout#timer()} return {@link
   *       HashedWheelTimer#HashedWheelTimer()}.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName(
      "Test run(Timeout); given HashedWheelTimer(); when Timeout timer() return HashedWheelTimer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun_givenHashedWheelTimer_whenTimeoutTimerReturnHashedWheelTimer() throws Exception {
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
   * <ul>
   *   <li>Given {@link HashedWheelTimer#HashedWheelTimer()}.
   *   <li>When {@link Timeout} {@link Timeout#timer()} return {@link
   *       HashedWheelTimer#HashedWheelTimer()}.
   * </ul>
   *
   * <p>Method under test: {@link CookieEvictionTask#run(Timeout)}
   */
  @Test
  @DisplayName(
      "Test run(Timeout); given HashedWheelTimer(); when Timeout timer() return HashedWheelTimer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CookieEvictionTask.run(Timeout)"})
  void testRun_givenHashedWheelTimer_whenTimeoutTimerReturnHashedWheelTimer2() throws Exception {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setWrap(true);
    cookie.setDomain("https://example.org/example");

    ThreadSafeCookieStore cookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "42",
            Uri.HTTP,
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    cookieStore.add(uri, cookie);
    CookieEvictionTask cookieEvictionTask = new CookieEvictionTask(1L, cookieStore);

    Timeout timeout = mock(Timeout.class);
    when(timeout.timer()).thenReturn(new HashedWheelTimer());

    // Act
    cookieEvictionTask.run(timeout);

    // Assert
    verify(timeout).timer();
  }
}
