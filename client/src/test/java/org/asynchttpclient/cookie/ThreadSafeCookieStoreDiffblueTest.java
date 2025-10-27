package org.asynchttpclient.cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.cookie.Cookie;
import java.util.List;
import java.util.function.Predicate;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ThreadSafeCookieStoreDiffblueTest {
  /**
   * Method under test: {@link ThreadSafeCookieStore#add(Uri, Cookie)}
   */
  @Test
  void testAdd() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(0L);

    // Act
    threadSafeCookieStore.add(uri, cookie);

    // Assert
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Method under test:
   * {@link ThreadSafeCookieStore.DomainUtils#getSubDomain(String)}
   */
  @Test
  void testDomainUtilsGetSubDomain() {
    // Arrange, Act and Assert
    assertEquals("org/example", ThreadSafeCookieStore.DomainUtils.getSubDomain("https://example.org/example"));
    assertNull(ThreadSafeCookieStore.DomainUtils.getSubDomain(null));
    assertNull(ThreadSafeCookieStore.DomainUtils.getSubDomain(""));
    assertNull(ThreadSafeCookieStore.DomainUtils.getSubDomain("Domain"));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertTrue(
        threadSafeCookieStore
            .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
            .isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet2() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act and Assert
    assertTrue(
        threadSafeCookieStore
            .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
            .isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet3() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, new DefaultCookie("Name", "https://example.org/example"));
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri2, new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act and Assert
    assertTrue(
        threadSafeCookieStore
            .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
            .isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet4() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertTrue(threadSafeCookieStore
        .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "", "https://example.org/example", "https://example.org/example"))
        .isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet5() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);

    // Act
    List<Cookie> actualGetResult = threadSafeCookieStore
        .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "", "https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, actualGetResult.size());
    assertSame(cookie, actualGetResult.get(0));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet6() {
    // Arrange
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(3L);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    threadSafeCookieStore
        .add(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"), cookie);

    // Act and Assert
    assertTrue(
        threadSafeCookieStore
            .get(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
            .isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  void testGet7() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);

    // Act
    List<Cookie> actualGetResult = threadSafeCookieStore.get(new Uri(Uri.HTTPS, "https://example.org/example",
        "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, actualGetResult.size());
    assertSame(cookie, actualGetResult.get(0));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  void testGetAll() {
    // Arrange, Act and Assert
    assertTrue((new ThreadSafeCookieStore()).getAll().isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  void testGetAll2() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(1, actualAll.size());
    assertSame(cookie, actualAll.get(0));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  void testGetAll3() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "localhost", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    DefaultCookie cookie2 = new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri2, cookie2);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(2, actualAll.size());
    assertSame(cookie, actualAll.get(0));
    assertSame(cookie2, actualAll.get(1));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  void testGetAll4() {
    // Arrange
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(1L);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    threadSafeCookieStore
        .add(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"), cookie);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(1, actualAll.size());
    assertSame(cookie, actualAll.get(0));
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  void testRemove() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertFalse(threadSafeCookieStore.remove(mock(Predicate.class)));
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  void testRemove2() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Predicate<Cookie> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Cookie>any())).thenReturn(true);

    // Act
    boolean actualRemoveResult = threadSafeCookieStore.remove(predicate);

    // Assert
    verify(predicate).test(isA(Cookie.class));
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
    assertTrue(actualRemoveResult);
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  void testRemove3() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri2, new DefaultCookie("Name", "https://example.org/example"));
    Predicate<Cookie> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Cookie>any())).thenReturn(true);

    // Act
    boolean actualRemoveResult = threadSafeCookieStore.remove(predicate);

    // Assert
    verify(predicate, atLeast(1)).test(Mockito.<Cookie>any());
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
    assertTrue(actualRemoveResult);
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#clear()}
   */
  @Test
  void testClear() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertFalse(threadSafeCookieStore.clear());
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#clear()}
   */
  @Test
  void testClear2() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act
    boolean actualClearResult = threadSafeCookieStore.clear();

    // Assert
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
    assertTrue(actualClearResult);
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#evictExpired()}
   */
  @Test
  void testEvictExpired() {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    threadSafeCookieStore.add(uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act
    threadSafeCookieStore.evictExpired();

    // Assert
    verify(uri).getHost();
    verify(uri, atLeast(1)).getPath();
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#evictExpired()}
   */
  @Test
  void testEvictExpired2() {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");

    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(1L);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    threadSafeCookieStore.add(uri, cookie);

    // Act
    threadSafeCookieStore.evictExpired();

    // Assert
    verify(uri).getHost();
    verify(uri, atLeast(1)).getPath();
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#incrementAndGet()}
   */
  @Test
  void testIncrementAndGet() {
    // Arrange, Act and Assert
    assertEquals(1, (new ThreadSafeCookieStore()).incrementAndGet());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#decrementAndGet()}
   */
  @Test
  void testDecrementAndGet() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ThreadSafeCookieStore()).decrementAndGet());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#count()}
   */
  @Test
  void testCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new ThreadSafeCookieStore()).count());
  }

  /**
   * Method under test: {@link ThreadSafeCookieStore#getUnderlying()}
   */
  @Test
  void testGetUnderlying() {
    // Arrange, Act and Assert
    assertTrue((new ThreadSafeCookieStore()).getUnderlying().isEmpty());
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ThreadSafeCookieStore}
   */
  @Test
  void testNewThreadSafeCookieStore() {
    // Arrange and Act
    ThreadSafeCookieStore actualThreadSafeCookieStore = new ThreadSafeCookieStore();

    // Assert
    assertTrue(actualThreadSafeCookieStore.getAll().isEmpty());
    assertTrue(actualThreadSafeCookieStore.getUnderlying().isEmpty());
  }
}
