package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class RealmDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Realm.Builder#Builder()}
   *   <li>{@link Realm.Builder#setAlgorithm(String)}
   *   <li>{@link Realm.Builder#setCharset(Charset)}
   *   <li>{@link Realm.Builder#setCustomLoginConfig(Map)}
   *   <li>{@link Realm.Builder#setLoginContextName(String)}
   *   <li>{@link Realm.Builder#setMethodName(String)}
   *   <li>{@link Realm.Builder#setNc(String)}
   *   <li>{@link Realm.Builder#setNonce(String)}
   *   <li>{@link Realm.Builder#setNtlmDomain(String)}
   *   <li>{@link Realm.Builder#setNtlmHost(String)}
   *   <li>{@link Realm.Builder#setOmitQuery(boolean)}
   *   <li>{@link Realm.Builder#setOpaque(String)}
   *   <li>{@link Realm.Builder#setRealmName(String)}
   *   <li>{@link Realm.Builder#setResponse(String)}
   *   <li>{@link Realm.Builder#setScheme(Realm.AuthScheme)}
   *   <li>{@link Realm.Builder#setServicePrincipalName(String)}
   *   <li>{@link Realm.Builder#setUri(Uri)}
   *   <li>{@link Realm.Builder#setUseAbsoluteURI(boolean)}
   *   <li>{@link Realm.Builder#setUseCanonicalHostname(boolean)}
   *   <li>{@link Realm.Builder#setUsePreemptiveAuth(boolean)}
   * </ul>
   */
  @Test
  void testBuilderNewBuilder() {
    // Arrange and Act
    Realm.Builder actualSetCharsetResult = (new Realm.Builder()).setAlgorithm("https://example.org/example")
        .setCharset(null);
    Realm.Builder actualSetServicePrincipalNameResult = actualSetCharsetResult.setCustomLoginConfig(new HashMap<>())
        .setLoginContextName("https://example.org/example")
        .setMethodName("https://example.org/example")
        .setNc("https://example.org/example")
        .setNonce("https://example.org/example")
        .setNtlmDomain("https://example.org/example")
        .setNtlmHost("https://example.org/example")
        .setOmitQuery(true)
        .setOpaque("https://example.org/example")
        .setQop("https://example.org/example")
        .setRealmName("https://example.org/example")
        .setResponse("https://example.org/example")
        .setScheme(Realm.AuthScheme.BASIC)
        .setServicePrincipalName("https://example.org/example");
    Realm.Builder actualSetUseCanonicalHostnameResult = actualSetServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true);

    // Assert
    assertSame(actualSetUseCanonicalHostnameResult, actualSetUseCanonicalHostnameResult.setUsePreemptiveAuth(true));
  }

  /**
   * Method under test: {@link Realm.Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  void testBuilderParseProxyAuthenticateHeader() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseProxyAuthenticateHeader("https://example.org/example"));
  }

  /**
   * Method under test: {@link Realm.Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  void testBuilderParseProxyAuthenticateHeader2() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseProxyAuthenticateHeader(null));
  }

  /**
   * Method under test: {@link Realm.Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  void testBuilderParseWWWAuthenticateHeader() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseWWWAuthenticateHeader("https://example.org/example"));
  }

  /**
   * Method under test: {@link Realm.Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  void testBuilderParseWWWAuthenticateHeader2() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseWWWAuthenticateHeader(null));
  }

  /**
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  void testBuilderSetQop() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop("https://example.org/example"));
  }

  /**
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  void testBuilderSetQop2() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop(null));
  }

  /**
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  void testBuilderSetQop3() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop(""));
  }
}
