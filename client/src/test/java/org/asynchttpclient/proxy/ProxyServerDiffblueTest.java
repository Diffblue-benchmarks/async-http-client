package org.asynchttpclient.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.junit.jupiter.api.Test;

class ProxyServerDiffblueTest {
  /**
   * Method under test: {@link ProxyServer.Builder#setNonProxyHost(String)}
   */
  @Test
  void testBuilderSetNonProxyHost() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);

    // Act
    ProxyServer.Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(1, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Method under test: {@link ProxyServer.Builder#setNonProxyHost(String)}
   */
  @Test
  void testBuilderSetNonProxyHost2() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);
    builder.setNonProxyHost(null);

    // Act
    ProxyServer.Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(2, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(1));
    assertNull(nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Method under test: {@link ProxyServer.Builder#setNonProxyHost(String)}
   */
  @Test
  void testBuilderSetNonProxyHost3() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);
    builder.setRealm(mock(Realm.class));

    // Act
    ProxyServer.Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(1, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Method under test: {@link ProxyServer.Builder#setRealm(Realm.Builder)}
   */
  @Test
  void testBuilderSetRealm() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);

    Realm.Builder realm = new Realm.Builder();
    realm.setScheme(Realm.AuthScheme.BASIC);

    // Act and Assert
    assertSame(builder, builder.setRealm(realm));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ProxyServer#ProxyServer(String, int, int, Realm, List, ProxyType)}
   *   <li>{@link ProxyServer#getCustomHeaders()}
   *   <li>{@link ProxyServer#getHost()}
   *   <li>{@link ProxyServer#getNonProxyHosts()}
   *   <li>{@link ProxyServer#getPort()}
   *   <li>{@link ProxyServer#getProxyType()}
   *   <li>{@link ProxyServer#getRealm()}
   *   <li>{@link ProxyServer#getSecuredPort()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();

    // Act
    ProxyServer actualProxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, nonProxyHosts,
        ProxyType.HTTP);
    Function<Request, HttpHeaders> actualCustomHeaders = actualProxyServer.getCustomHeaders();
    String actualHost = actualProxyServer.getHost();
    List<String> actualNonProxyHosts = actualProxyServer.getNonProxyHosts();
    int actualPort = actualProxyServer.getPort();
    ProxyType actualProxyType = actualProxyServer.getProxyType();
    Realm actualRealm = actualProxyServer.getRealm();

    // Assert
    assertEquals("https://example.org/example", actualHost);
    assertNull(actualCustomHeaders);
    assertNull(actualRealm);
    assertEquals(8080, actualPort);
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyType);
    assertTrue(actualNonProxyHosts.isEmpty());
    assertSame(nonProxyHosts, actualNonProxyHosts);
  }

  /**
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  void testIsIgnoredForHost() {
    // Arrange
    Realm realm = mock(Realm.class);

    // Act and Assert
    assertFalse((new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP))
        .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  void testIsIgnoredForHost2() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("hostname");

    // Act and Assert
    assertFalse(
        (new ProxyServer("https://example.org/example", 8080, 8080, mock(Realm.class), nonProxyHosts, ProxyType.HTTP))
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  void testIsIgnoredForHost3() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("");

    // Act and Assert
    assertFalse(
        (new ProxyServer("https://example.org/example", 8080, 8080, mock(Realm.class), nonProxyHosts, ProxyType.HTTP))
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  void testIsIgnoredForHost4() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("hostname");

    // Act and Assert
    assertTrue(
        (new ProxyServer("https://example.org/example", 8080, 8080, mock(Realm.class), nonProxyHosts, ProxyType.HTTP))
            .isIgnoredForHost("hostname"));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link ProxyServer#ProxyServer(String, int, int, Realm, List, ProxyType, Function)}
   *   <li>{@link ProxyServer#getCustomHeaders()}
   *   <li>{@link ProxyServer#getHost()}
   *   <li>{@link ProxyServer#getNonProxyHosts()}
   *   <li>{@link ProxyServer#getPort()}
   *   <li>{@link ProxyServer#getProxyType()}
   *   <li>{@link ProxyServer#getRealm()}
   *   <li>{@link ProxyServer#getSecuredPort()}
   * </ul>
   */
  @Test
  void testGettersAndSetters2() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    Function<Request, HttpHeaders> customHeaders = mock(Function.class);

    // Act
    ProxyServer actualProxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, nonProxyHosts,
        ProxyType.HTTP, customHeaders);
    Function<Request, HttpHeaders> actualCustomHeaders = actualProxyServer.getCustomHeaders();
    String actualHost = actualProxyServer.getHost();
    List<String> actualNonProxyHosts = actualProxyServer.getNonProxyHosts();
    int actualPort = actualProxyServer.getPort();
    ProxyType actualProxyType = actualProxyServer.getProxyType();
    Realm actualRealm = actualProxyServer.getRealm();

    // Assert
    assertEquals("https://example.org/example", actualHost);
    assertNull(actualRealm);
    assertEquals(8080, actualPort);
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyType);
    assertTrue(actualNonProxyHosts.isEmpty());
    assertSame(nonProxyHosts, actualNonProxyHosts);
    assertSame(customHeaders, actualCustomHeaders);
  }
}
