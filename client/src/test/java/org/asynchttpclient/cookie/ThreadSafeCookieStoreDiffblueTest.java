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
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.cookie.Cookie;
import java.util.List;
import java.util.function.Predicate;
import org.asynchttpclient.cookie.ThreadSafeCookieStore.DomainUtils;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ThreadSafeCookieStoreDiffblueTest {
  /**
   * Test {@link ThreadSafeCookieStore#add(Uri, Cookie)} with {@code uri}, {@code cookie}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then {@link ThreadSafeCookieStore} (default constructor) All Empty.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#add(Uri, Cookie)}
   */
  @Test
  @DisplayName(
      "Test add(Uri, Cookie) with 'uri', 'cookie'; given zero; then ThreadSafeCookieStore (default constructor) All Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ThreadSafeCookieStore.add(Uri, Cookie)"})
  void testAddWithUriCookie_givenZero_thenThreadSafeCookieStoreAllEmpty() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(0L);

    // Act
    threadSafeCookieStore.add(uri, cookie);

    // Assert that nothing has changed
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Test DomainUtils {@link DomainUtils#getSubDomain(String)}.
   *
   * <ul>
   *   <li>When {@code Domain}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DomainUtils#getSubDomain(String)}
   */
  @Test
  @DisplayName("Test DomainUtils getSubDomain(String); when 'Domain'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DomainUtils.getSubDomain(String)"})
  void testDomainUtilsGetSubDomain_whenDomain_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DomainUtils.getSubDomain("Domain"));
  }

  /**
   * Test DomainUtils {@link DomainUtils#getSubDomain(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DomainUtils#getSubDomain(String)}
   */
  @Test
  @DisplayName("Test DomainUtils getSubDomain(String); when empty string; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DomainUtils.getSubDomain(String)"})
  void testDomainUtilsGetSubDomain_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DomainUtils.getSubDomain(""));
  }

  /**
   * Test DomainUtils {@link DomainUtils#getSubDomain(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code org/example}.
   * </ul>
   *
   * <p>Method under test: {@link DomainUtils#getSubDomain(String)}
   */
  @Test
  @DisplayName(
      "Test DomainUtils getSubDomain(String); when 'https://example.org/example'; then return 'org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DomainUtils.getSubDomain(String)"})
  void testDomainUtilsGetSubDomain_whenHttpsExampleOrgExample_thenReturnOrgExample() {
    // Arrange, Act and Assert
    assertEquals("org/example", DomainUtils.getSubDomain("https://example.org/example"));
  }

  /**
   * Test DomainUtils {@link DomainUtils#getSubDomain(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DomainUtils#getSubDomain(String)}
   */
  @Test
  @DisplayName("Test DomainUtils getSubDomain(String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DomainUtils.getSubDomain(String)"})
  void testDomainUtilsGetSubDomain_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DomainUtils.getSubDomain(null));
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri2() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri2).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri3() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(uri, new DefaultCookie("Name", "https://example.org/example"));
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri2, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri3 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri3).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri4() {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(3L);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri2).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri5() {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setSecure(true);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri2).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <ul>
   *   <li>Given {@link ThreadSafeCookieStore} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'; given ThreadSafeCookieStore (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri_givenThreadSafeCookieStore() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertTrue(threadSafeCookieStore.get(uri).isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#get(Uri)} with {@code uri}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#get(Uri)}
   */
  @Test
  @DisplayName("Test get(Uri) with 'uri'; then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.get(Uri)"})
  void testGetWithUri_thenReturnSizeIsOne() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    List<Cookie> actualGetResult = threadSafeCookieStore.get(uri2);

    // Assert
    assertEquals(1, actualGetResult.size());
    Cookie getResult = actualGetResult.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    assertSame(cookie, getResult);
  }

  /**
   * Test {@link ThreadSafeCookieStore#getAll()}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.getAll()"})
  void testGetAll() {
    // Arrange
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookie.setMaxAge(1L);

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(1, actualAll.size());
    Cookie getResult = actualAll.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    assertSame(cookie, getResult);
  }

  /**
   * Test {@link ThreadSafeCookieStore#getAll()}.
   *
   * <ul>
   *   <li>Given {@link ThreadSafeCookieStore} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  @DisplayName(
      "Test getAll(); given ThreadSafeCookieStore (default constructor); then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.getAll()"})
  void testGetAll_givenThreadSafeCookieStore_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new ThreadSafeCookieStore().getAll().isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#getAll()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  @DisplayName("Test getAll(); then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.getAll()"})
  void testGetAll_thenReturnSizeIsOne() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri, cookie);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(1, actualAll.size());
    Cookie getResult = actualAll.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    assertSame(cookie, getResult);
  }

  /**
   * Test {@link ThreadSafeCookieStore#getAll()}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#getAll()}
   */
  @Test
  @DisplayName("Test getAll(); then return size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List ThreadSafeCookieStore.getAll()"})
  void testGetAll_thenReturnSizeIsTwo() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "localhost",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

    threadSafeCookieStore.add(uri2, cookie);

    // Act
    List<Cookie> actualAll = threadSafeCookieStore.getAll();

    // Assert
    assertEquals(2, actualAll.size());
    assertSame(cookie, actualAll.get(1));
  }

  /**
   * Test {@link ThreadSafeCookieStore#remove(Predicate)}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  @DisplayName("Test remove(Predicate)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ThreadSafeCookieStore.remove(Predicate)"})
  void testRemove() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
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
   * Test {@link ThreadSafeCookieStore#remove(Predicate)}.
   *
   * <ul>
   *   <li>Given {@link ThreadSafeCookieStore} (default constructor).
   *   <li>When {@link Predicate}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  @DisplayName(
      "Test remove(Predicate); given ThreadSafeCookieStore (default constructor); when Predicate; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ThreadSafeCookieStore.remove(Predicate)"})
  void testRemove_givenThreadSafeCookieStore_whenPredicate_thenReturnFalse() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertFalse(threadSafeCookieStore.remove(mock(Predicate.class)));
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#remove(Predicate)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link Predicate} {@link Predicate#test(Object)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#remove(Predicate)}
   */
  @Test
  @DisplayName(
      "Test remove(Predicate); given 'true'; when Predicate test(Object) return 'true'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ThreadSafeCookieStore.remove(Predicate)"})
  void testRemove_givenTrue_whenPredicateTestReturnTrue_thenReturnTrue() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));

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
   * Test {@link ThreadSafeCookieStore#clear()}.
   *
   * <ul>
   *   <li>Given {@link ThreadSafeCookieStore} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#clear()}
   */
  @Test
  @DisplayName(
      "Test clear(); given ThreadSafeCookieStore (default constructor); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ThreadSafeCookieStore.clear()"})
  void testClear_givenThreadSafeCookieStore_thenReturnFalse() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();

    // Act and Assert
    assertFalse(threadSafeCookieStore.clear());
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
  }

  /**
   * Test {@link ThreadSafeCookieStore#clear()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#clear()}
   */
  @Test
  @DisplayName("Test clear(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ThreadSafeCookieStore.clear()"})
  void testClear_thenReturnTrue() {
    // Arrange
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    threadSafeCookieStore.add(
        uri, new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act
    boolean actualClearResult = threadSafeCookieStore.clear();

    // Assert
    assertTrue(threadSafeCookieStore.getAll().isEmpty());
    assertTrue(threadSafeCookieStore.getUnderlying().isEmpty());
    assertTrue(actualClearResult);
  }

  /**
   * Test {@link ThreadSafeCookieStore#incrementAndGet()}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#incrementAndGet()}
   */
  @Test
  @DisplayName("Test incrementAndGet()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int ThreadSafeCookieStore.incrementAndGet()"})
  void testIncrementAndGet() {
    // Arrange, Act and Assert
    assertEquals(1, new ThreadSafeCookieStore().incrementAndGet());
  }

  /**
   * Test {@link ThreadSafeCookieStore#decrementAndGet()}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#decrementAndGet()}
   */
  @Test
  @DisplayName("Test decrementAndGet()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int ThreadSafeCookieStore.decrementAndGet()"})
  void testDecrementAndGet() {
    // Arrange, Act and Assert
    assertEquals(-1, new ThreadSafeCookieStore().decrementAndGet());
  }

  /**
   * Test {@link ThreadSafeCookieStore#count()}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#count()}
   */
  @Test
  @DisplayName("Test count()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int ThreadSafeCookieStore.count()"})
  void testCount() {
    // Arrange, Act and Assert
    assertEquals(0, new ThreadSafeCookieStore().count());
  }

  /**
   * Test {@link ThreadSafeCookieStore#getUnderlying()}.
   *
   * <p>Method under test: {@link ThreadSafeCookieStore#getUnderlying()}
   */
  @Test
  @DisplayName("Test getUnderlying()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ThreadSafeCookieStore.getUnderlying()"})
  void testGetUnderlying() {
    // Arrange, Act and Assert
    assertTrue(new ThreadSafeCookieStore().getUnderlying().isEmpty());
  }

  /**
   * Test new {@link ThreadSafeCookieStore} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ThreadSafeCookieStore}
   */
  @Test
  @DisplayName("Test new ThreadSafeCookieStore (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ThreadSafeCookieStore.<init>()"})
  void testNewThreadSafeCookieStore() {
    // Arrange and Act
    ThreadSafeCookieStore actualThreadSafeCookieStore = new ThreadSafeCookieStore();

    // Assert
    assertTrue(actualThreadSafeCookieStore.getAll().isEmpty());
    assertTrue(actualThreadSafeCookieStore.getUnderlying().isEmpty());
  }
}
