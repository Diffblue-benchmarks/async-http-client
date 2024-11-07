package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.asynchttpclient.Realm.Builder;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RealmDiffblueTest {
  /**
   * Test Builder {@link Builder#Builder()}.
   * <p>
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
  @DisplayName("Test Builder new Builder()")
  void testBuilderNewBuilder() {
    // Arrange and Act
    Realm.Builder actualSetAlgorithmResult = (new Realm.Builder()).setAlgorithm("https://example.org/example");
    Realm.Builder actualSetCharsetResult = actualSetAlgorithmResult.setCharset(Charset.forName("UTF-8"));
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
   * Test Builder {@link Builder#parseProxyAuthenticateHeader(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseProxyAuthenticateHeader(String); when 'https://example.org/example'")
  void testBuilderParseProxyAuthenticateHeader_whenHttpsExampleOrgExample() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseProxyAuthenticateHeader("https://example.org/example"));
  }

  /**
   * Test Builder {@link Builder#parseProxyAuthenticateHeader(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link Builder#Builder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseProxyAuthenticateHeader(String); when 'null'; then return Builder()")
  void testBuilderParseProxyAuthenticateHeader_whenNull_thenReturnBuilder() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseProxyAuthenticateHeader(null));
  }

  /**
   * Test Builder {@link Builder#parseWWWAuthenticateHeader(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseWWWAuthenticateHeader(String); when 'https://example.org/example'")
  void testBuilderParseWWWAuthenticateHeader_whenHttpsExampleOrgExample() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseWWWAuthenticateHeader("https://example.org/example"));
  }

  /**
   * Test Builder {@link Builder#parseWWWAuthenticateHeader(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link Builder#Builder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseWWWAuthenticateHeader(String); when 'null'; then return Builder()")
  void testBuilderParseWWWAuthenticateHeader_whenNull_thenReturnBuilder() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.parseWWWAuthenticateHeader(null));
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when empty string")
  void testBuilderSetQop_whenEmptyString() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop(""));
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when 'https://example.org/example'")
  void testBuilderSetQop_whenHttpsExampleOrgExample() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop("https://example.org/example"));
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Realm.Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when 'null'")
  void testBuilderSetQop_whenNull() {
    // Arrange
    Realm.Builder builder = new Realm.Builder();

    // Act and Assert
    assertSame(builder, builder.setQop(null));
  }
}
