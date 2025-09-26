package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Realm.Builder;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RealmDiffblueTest {
  /**
   * Test Builder {@link Builder#Builder()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#Builder()}
   *   <li>{@link Builder#setAlgorithm(String)}
   *   <li>{@link Builder#setCharset(Charset)}
   *   <li>{@link Builder#setCustomLoginConfig(Map)}
   *   <li>{@link Builder#setLoginContextName(String)}
   *   <li>{@link Builder#setMethodName(String)}
   *   <li>{@link Builder#setNc(String)}
   *   <li>{@link Builder#setNonce(String)}
   *   <li>{@link Builder#setNtlmDomain(String)}
   *   <li>{@link Builder#setNtlmHost(String)}
   *   <li>{@link Builder#setOmitQuery(boolean)}
   *   <li>{@link Builder#setOpaque(String)}
   *   <li>{@link Builder#setRealmName(String)}
   *   <li>{@link Builder#setResponse(String)}
   *   <li>{@link Builder#setScheme(AuthScheme)}
   *   <li>{@link Builder#setServicePrincipalName(String)}
   *   <li>{@link Builder#setUri(Uri)}
   *   <li>{@link Builder#setUseAbsoluteURI(boolean)}
   *   <li>{@link Builder#setUseCanonicalHostname(boolean)}
   *   <li>{@link Builder#setUsePreemptiveAuth(boolean)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder new Builder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Realm Builder.build()",
    "Builder Builder.setAlgorithm(String)",
    "Builder Builder.setCharset(Charset)",
    "Builder Builder.setCustomLoginConfig(Map)",
    "Builder Builder.setLoginContextName(String)",
    "Builder Builder.setMethodName(String)",
    "Builder Builder.setNc(String)",
    "Builder Builder.setNonce(String)",
    "Builder Builder.setNtlmDomain(String)",
    "Builder Builder.setNtlmHost(String)",
    "Builder Builder.setOmitQuery(boolean)",
    "Builder Builder.setOpaque(String)",
    "Builder Builder.setRealmName(String)",
    "Builder Builder.setResponse(String)",
    "Builder Builder.setScheme(AuthScheme)",
    "Builder Builder.setServicePrincipalName(String)",
    "Builder Builder.setUri(Uri)",
    "Builder Builder.setUseAbsoluteURI(boolean)",
    "Builder Builder.setUseCanonicalHostname(boolean)",
    "Builder Builder.setUsePreemptiveAuth(boolean)"
  })
  void testBuilderNewBuilder() {
    // Arrange and Act
    Builder actualSetAlgorithmResult = new Builder().setAlgorithm("https://example.org/example");
    Builder actualSetCharsetResult = actualSetAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Builder actualSetServicePrincipalNameResult =
        actualSetCharsetResult
            .setCustomLoginConfig(new HashMap<>())
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
            .setScheme(AuthScheme.BASIC)
            .setServicePrincipalName("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Builder actualSetUseCanonicalHostnameResult =
        actualSetServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true);
    Builder actualSetUsePreemptiveAuthResult =
        actualSetUseCanonicalHostnameResult.setUsePreemptiveAuth(true);

    // Assert
    assertSame(actualSetUseCanonicalHostnameResult, actualSetUsePreemptiveAuthResult);
  }

  /**
   * Test Builder {@link Builder#parseProxyAuthenticateHeader(String)}.
   *
   * <p>Method under test: {@link Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseProxyAuthenticateHeader(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.parseProxyAuthenticateHeader(String)"})
  void testBuilderParseProxyAuthenticateHeader() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", "https://example.org/example");
    builder.setNonce("");

    // Act
    Builder actualParseProxyAuthenticateHeaderResult = builder.parseProxyAuthenticateHeader(null);

    // Assert
    assertSame(builder, actualParseProxyAuthenticateHeaderResult);
  }

  /**
   * Test Builder {@link Builder#parseProxyAuthenticateHeader(String)}.
   *
   * <ul>
   *   <li>Then return {@link Builder#Builder()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#parseProxyAuthenticateHeader(String)}
   */
  @Test
  @DisplayName("Test Builder parseProxyAuthenticateHeader(String); then return Builder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.parseProxyAuthenticateHeader(String)"})
  void testBuilderParseProxyAuthenticateHeader_thenReturnBuilder() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualParseProxyAuthenticateHeaderResult =
        builder.parseProxyAuthenticateHeader("https://example.org/example");

    // Assert
    assertSame(builder, actualParseProxyAuthenticateHeaderResult);
  }

  /**
   * Test Builder {@link Builder#parseWWWAuthenticateHeader(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  @DisplayName(
      "Test Builder parseWWWAuthenticateHeader(String); when 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.parseWWWAuthenticateHeader(String)"})
  void testBuilderParseWWWAuthenticateHeader_whenHttpsExampleOrgExample() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualParseWWWAuthenticateHeaderResult =
        builder.parseWWWAuthenticateHeader("https://example.org/example");

    // Assert
    assertSame(builder, actualParseWWWAuthenticateHeaderResult);
  }

  /**
   * Test Builder {@link Builder#parseWWWAuthenticateHeader(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Builder#Builder()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#parseWWWAuthenticateHeader(String)}
   */
  @Test
  @DisplayName(
      "Test Builder parseWWWAuthenticateHeader(String); when 'null'; then return Builder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.parseWWWAuthenticateHeader(String)"})
  void testBuilderParseWWWAuthenticateHeader_whenNull_thenReturnBuilder() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualParseWWWAuthenticateHeaderResult = builder.parseWWWAuthenticateHeader(null);

    // Assert
    assertSame(builder, actualParseWWWAuthenticateHeaderResult);
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setQop(String)"})
  void testBuilderSetQop_whenEmptyString() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualSetQopResult = builder.setQop("");

    // Assert
    assertSame(builder, actualSetQopResult);
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setQop(String)"})
  void testBuilderSetQop_whenHttpsExampleOrgExample() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualSetQopResult = builder.setQop("https://example.org/example");

    // Assert
    assertSame(builder, actualSetQopResult);
  }

  /**
   * Test Builder {@link Builder#setQop(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setQop(String)}
   */
  @Test
  @DisplayName("Test Builder setQop(String); when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setQop(String)"})
  void testBuilderSetQop_whenNull() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualSetQopResult = builder.setQop(null);

    // Assert
    assertSame(builder, actualSetQopResult);
  }
}
